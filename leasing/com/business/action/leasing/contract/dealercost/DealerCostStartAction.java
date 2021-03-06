package com.business.action.leasing.contract.dealercost;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.business.action.JbpmBaseAction;
import com.business.entity.DictionaryData;
import com.business.entity.JBPMWorkflowHistoryInfo;
import com.business.entity.contract.ContractInfo;
import com.business.entity.contract.equip.ContractEquip;
import com.business.entity.contract.reckon.condition.ContractCondition;
import com.business.entity.cust.CustInfo;
import com.business.entity.proj.ProjInfo;
import com.business.service.TableService;
import com.kernal.annotation.WorkflowAction;

 
@WorkflowAction(name = "dealerCostStartAction", description = "流程开始动作", workflowName = "GPS费用核对")
@Component(value = "dealerCostStartAction")
public class DealerCostStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_id = variablesMap.get("contract_id");//合同的主键
		String dealername = variablesMap.get("dealername");//经销商的名称

		
		variablesMap.put("contract_info.dealername", dealername);
		ContractInfo contractInfo = this.tableService.findEntityByID(ContractInfo.class,contract_id);
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		propertiesMap.put("contractId.id",contractInfo.getId());
		List<ContractCondition> listCon = this.tableService.findEntityByProperties(ContractCondition.class, propertiesMap);
		List<ContractEquip> listEq = this.tableService.findEntityByProperties(ContractEquip.class, propertiesMap);
		int num = 0;
		if(listEq.size()>0){
			for (int i=0;i<listEq.size();i++){
				ContractEquip contractEquip = listEq.get(i);
				System.out.println("eqCode:"+contractEquip.getEquipClass().getCode());
				if(!contractEquip.getEquipClass().getCode().equals("equip_class_2")){
					num++;
				}
			}
		}
		if(num==0){
			num=1;
		}
		variablesMap.put("contract_info.num",String.valueOf(num));
		if ( listCon.size()>0 ){
			ContractCondition condition = listCon.get(0);
			Integer leaseTerm = condition.getLeaseTerm();//租赁期限
			BigDecimal gpsMoney = condition.getGpsMoney(); //服务费
			variablesMap.put("contract_info.gpsmoney",String.valueOf(gpsMoney));
			if(leaseTerm/12 ==  1 ){//一年
				DictionaryData dictData1 = this.tableService.findEntityByID(DictionaryData.class,"service_cost.201501");
				variablesMap.put("contract_info.conservicemoney","一年"+Integer.parseInt(dictData1.getDescription())*num+"元");
			}else if (leaseTerm/12 ==  2 ){
				DictionaryData dictData2 = this.tableService.findEntityByID(DictionaryData.class,"service_cost.201502");
				variablesMap.put("contract_info.conservicemoney","二年"+Integer.parseInt(dictData2.getDescription())*num+"元");
			}else if (leaseTerm/12 ==  3 ){
				DictionaryData dictData3 = this.tableService.findEntityByID(DictionaryData.class,"service_cost.201503");
				variablesMap.put("contract_info.conservicemoney","三年"+Integer.parseInt(dictData3.getDescription())*num+"元");
			}
		}
		//初始化合同基本信息
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractInfo, null, "contract_info"));
		CustInfo customerInfo = contractInfo.getCustId();
		String custClass= customerInfo.getCustClass().getName();
		variablesMap.put("proj_info.cust_type", custClass);//客户类别[法律性质的个人/法人/经销商(德银)][D]"
		ProjInfo projInfo = contractInfo.getProjId();
		variablesMap.put("contract_info.custname", customerInfo.getCustName());
		variablesMap.put("contract_info.custnumber", customerInfo.getCustNumber());
		variablesMap.put("contract_info.custid", customerInfo.getId());
		variablesMap.put("contract_info.projid", projInfo.getProjId());
		
		HashMap<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contract_id);
		if (!contractInfo.getContractEquips().isEmpty()) {// 租赁物明细
			variablesMap.put("json_contract_equip_str", this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_equip.xml", queryMainObjectMap).toString());
		}
		if (!contractInfo.getId().isEmpty()) {//                  D:\MyJava\workspace\Byeclipse\tracywindy\WebContent\WEB-INF\tablesXml\eleasing\jsp\other\insurance_manage\list1.xml  
			variablesMap.put("json_proj_gps_str", this.tableService.getJsonArrayData("eleasing/jsp/other/insurance_manage/list1.xml", queryMainObjectMap).toString());
		}
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
 
}
