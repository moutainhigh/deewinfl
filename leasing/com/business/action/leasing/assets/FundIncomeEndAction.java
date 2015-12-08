package com.business.action.leasing.assets;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.business.action.JbpmBaseAction;
import com.business.entity.DictionaryData;
import com.business.entity.JBPMWorkflowHistoryInfo;
import com.business.entity.User;
import com.business.entity.asset.FundEbankData;
import com.business.entity.asset.FundEbankProcess;
import com.business.entity.base.WorkFlowFlag;
import com.business.entity.contract.ContractInfo;
import com.business.entity.contract.reckon.fund.ContractFundRentInCome;
import com.business.entity.contract.reckon.fund.ContractFundRentPlan;
import com.business.entity.contract.rent.ContractCardRentIncome;
import com.business.entity.util.OwnAccount;
import com.business.exception.BusinessException;
import com.business.service.TableService;
import com.business.service.vouchersFactory.ContractOnHireService;
import com.business.service.vouchersFactory.FundIncomeService;
import com.business.util.WorkflowUtil;
import com.ctc.wstx.util.DataUtil;
import com.kernal.annotation.WorkflowAction;
import com.kernal.utils.DateUtil;
import com.kernal.utils.SecurityUtil;

/**
 * @author Toybaby
 * 
 *         2012-12-26下午01:17:43 email toybaby@mail2.tenwa.com.cn function:
 * 
 */
@WorkflowAction(name = "fundIncomeEndAction", description = "流程结束动作", workflowName = "租金回笼流程")
@Component(value = "fundIncomeEndAction")
public class FundIncomeEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	@Resource(name="fundIncomeService")
	private FundIncomeService fundIncomeService;
	

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		/*String  contractid=variablesMap.get("contract_info.contractid");
	    DictionaryData dicdpayType;
	    String paytype="pay_type_in";
		String HQL=" from ContractInfo as c where c.contractId=?";
		ContractInfo contractinfo;            
		contractinfo=(ContractInfo)this.tableService.findResultsByHSQL(HQL, contractid).get(0);
		HQL=" from DictionaryData AS DD where DD.code=? ";
		dicdpayType=(DictionaryData)this.tableService.findResultsByHSQL(HQL,paytype).get(0);
		*/
		

		/** 流程冲突第三步-结束 */
		if (StringUtils.isNotEmpty(variablesMap.get("workFlowFlag"))) {
			WorkFlowFlag w = this.tableService.findEntityByID(WorkFlowFlag.class, variablesMap.get("workFlowFlag"));
			this.tableService.removeEntity(w);
		}
		/** 流程冲突第三步-结束 */
		
		String contract_id = variablesMap.get("contract_id");
	
		BigDecimal sum = BigDecimal.ZERO;
		BigDecimal originalMoney = null;
		//计算本次租金回笼的利息，传入生成租金利息的凭证
		BigDecimal interest = BigDecimal.ZERO;
		
		ContractInfo contractInfo = (ContractInfo) this.tableService.findEntityByID(ContractInfo.class, contract_id);
		
		Map<String,String> propertiesMap = new HashMap<String,String>();
		propertiesMap.put("DictionaryData", "code");
		propertiesMap.put("FundEbankData", "ebdataId");
		propertiesMap.put("ContractFundRentPlan", "id");
		// 租金实收
		String jsonFundString = variablesMap.get("current_fund_income_detial");//proj_info.projEquips
		JSONArray jsonArray = new JSONArray(jsonFundString);
		List<String> jsonFundList=new ArrayList<String>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.remove("id");
			jsonFundList.add(jsonObj.toString());
			
			sum=sum.add(new BigDecimal(jsonObj.getDouble("receivermoney")));
			//统计租金利息
			interest=interest.add(new BigDecimal(jsonObj.getDouble("interest")));
			
			String pid = jsonObj.optString("pid");
			String rollback = jsonObj.optString("rollback");
			
			if(pid != null && rollback != null && rollback.equals("1")){//如果是红冲，则将对应收款设置成rollback
				ContractFundRentInCome c = (ContractFundRentInCome) this.tableService.findEntityByID(ContractFundRentInCome.class, pid);
				
				//设置状态为红冲,rollbac设置为1
				DictionaryData payFund9Dict = (DictionaryData) this.tableService.findEntityByID(DictionaryData.class, "PayFund9");
				c.setRollBack("1");
				c.setBalanceMode(payFund9Dict);
				
				this.tableService.updateEntity(c);
				jsonObj.remove("pid");
				
				//如果是卡扣租金，则需要插一笔虚拟网银信息
				if(c.getUpId()!=null){
					FundEbankData  fd=new FundEbankData();
					//获取网银编号
					String ebdataId = WorkflowUtil.getEbankNoInfoSerialNumber(variablesMap,
							this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService
							.getBussinessDao().getJdbcTemplate());
					fd.setEbdataId(ebdataId);
					//本方银行信息
					String HQLacc=" from OwnAccount AS acc where acc.accNumber=? ";
					List<OwnAccount> ownacclist=this.tableService.findResultsByHSQL(HQLacc,c.getOwnNumber());
					if(ownacclist.size()>0){
						OwnAccount ownacc=ownacclist.get(0);
					    fd.setOwnBank(ownacc.getAccBank());
					    fd.setOwnAccount(ownacc.getAccName());
					    fd.setOwnAccNumber(ownacc.getAccNumber());
					}
					//对方银行信息
					//插入租金计划表
					String HQL="from ContractCardRentIncome as ccr where ccr.id=? ";
					List<ContractCardRentIncome> crentlist=this.tableService.findResultsByHSQL(HQL,c.getPID());
					if(crentlist.size()>0){
						ContractCardRentIncome ccri=crentlist.get(0);
						fd.setClientName(ccri.getCilentname());
						fd.setClientAccNumber(ccri.getClientAccount());
						fd.setSummary(ccri.getTradePurpose()+"红冲网银");
					}
					//虚拟网银SN
					fd.setSn(c.getPID());
					fd.setMoneyType("电汇");
					//红冲总金额的绝对值
					fd.setFactMoney(new BigDecimal(jsonObj.getDouble("receivermoney")).abs());
					fd.setNoWithMoney(BigDecimal.ZERO);
					fd.setFactDate(c.getHireDate());
					fd.setEnabled(0);
					fd.setInuseflag("0");
					fd.setCreator(SecurityUtil.getPrincipal());
					fd.setCreateDate(DateUtil.getSystemDate());
					this.tableService.saveOrUpdateEntity(fd);
				}
			}
		}
		this.tableService.updateOneToManyCollectionsNoRemoved(contractInfo, "contractFundRentIncomes", ContractFundRentInCome.class, "contractId", jsonFundList.toString(), propertiesMap);
		
		originalMoney = new BigDecimal(sum.toString());
		
		if("red".equals(variablesMap.get("feetype"))){
			System.out.println("红冲不需要网银信息");
		}else{
		   //网银检查
		   FundEbankProcess fundEbankProcess = (FundEbankProcess) this.tableService.findEntityByID(FundEbankProcess.class, variablesMap.get("fund_ebank_process"));
		   FundEbankData fundEbankData = (FundEbankData) this.tableService.findEntityByID(FundEbankData.class, variablesMap.get("ebank_id"));
		   fundEbankData.setInuseflag("0");
		   fundEbankData.initFundEbank();
		   //去除当前网银中间表数据；
		   sum = sum.subtract(fundEbankProcess.getProcessAmount());
		   if(fundEbankData.getMayOpeMoney().subtract(sum.setScale(2,BigDecimal.ROUND_HALF_UP)).compareTo(BigDecimal.ZERO) == -1){
			  throw new BusinessException("当前核销金额有误,请检查");
		   } 
		   fundEbankProcess.setWork_flag("1");
		   this.tableService.updateEntity(fundEbankProcess);
		}
		
		/** 凭证生成-开始*/
		//
		/*如果该网银属于经销商，则生成凭证： 核销+客户+租金
		 传入参数，para1：合同信息，para2：本次回笼租金
		 * */
		FundEbankData fed=null;
		if(variablesMap.get("ebank_id")!=null){
			 fed = (FundEbankData) this.tableService.findEntityByID(FundEbankData.class, variablesMap.get("ebank_id"));
		}
		
		//每一笔都要生成凭证
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			BigDecimal rent=new BigDecimal(jsonObj.getDouble("rent"));
			if(rent.compareTo(BigDecimal.ZERO)!=0){
				fundIncomeService.createVoucher(contractInfo,jsonObj,fed);
			}
			//存在罚息时，生成确认+客户+延期利息 和  核销+客户+延期利息
			BigDecimal penalty =new BigDecimal(jsonObj.optString("penalty")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			if(penalty.compareTo(BigDecimal.ZERO)!=0){
				String workFlowName = "租金回笼";
				//  确认+客户+延期利息
				fundIncomeService.createVoucherForPenalty(contractInfo,jsonObj,workFlowName);
			    //  核销+客户+延期利息
			    fundIncomeService.createVoucherForPenaltyhx(contractInfo,jsonObj,fed);
			}
		}
		/** 凭证生成-结束*/
		/*如果该网银属于经销商，则生成凭证： 收到+客户+租金
		 传入参数，para1：合同信息，para2：本次回笼租金,para3:网银信息
		 * 
		
		if("CUST_INFO_DEALER".equals(fundEbankData.getCustId().getCustClass().getId()))
		{
		   fundIncomeService.createVoucherReceiveRent(contractInfo, sum, fundEbankData);
		}*/
		
		
		
		/*
		//插入网银在进行流程信息
		BigDecimal sum = BigDecimal.ZERO;
		JSONArray jsonArr = new JSONArray(variablesMap.get("current_fund_income_detial"));
		for(int i=0;i<jsonArr.length();i++){
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			sum=sum.add(new BigDecimal(jsonObj.getDouble("rent")));
		}
		FundEbankProcess fundebankprocess = new FundEbankProcess();
		FundEbankData fundebankdata= (FundEbankData)this.tableService.findEntityByID(FundEbankData.class, variablesMap.get("fund_ebank_data.id"));
		fundebankprocess.setEbdataId(fundebankdata);
		fundebankprocess.setContractId(variablesMap.get("contract_id"));
		fundebankprocess.setDealerID(variablesMap.get("fund_ebank_data.ebdataid"));
		fundebankprocess.setKeyOne(jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"");
		fundebankprocess.setProcessAmount(sum);
		fundebankprocess.setProcessName(jbpmWorkflowHistoryInfo.getWorkflowName());
		fundebankprocess.setStartDate(DateUtil.getSystemDate());
		fundebankprocess.setWork_flag("0");
		this.tableService.saveEntity(fundebankprocess);
		
		//更新网银表
		HQL = " update FUND_EBANK_DATA set fact_money=? where id = ?";
		this.tableService.updateBySql(HQL, (fundebankdata.getFactMoney().subtract(sum)),variablesMap.get("fund_ebank_data.id"));
		*/
	}
    
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}

	
	 /**
	 * (non-Javadoc)
	 * @see com.business.action.JbpmBaseAction#back(javax.servlet.http.HttpServletRequest, java.util.Map)
	 **/
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}
}