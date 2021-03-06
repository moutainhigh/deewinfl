package com.business.action.leasing.project.approval;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.action.JbpmBaseAction;
import com.business.entity.Dictionary;
import com.business.entity.DictionaryData;
import com.business.entity.JBPMWorkflowHistoryInfo;
import com.business.entity.User;
import com.business.entity.cust.CustInfo;
import com.business.entity.cust.CustInfoCompany;
import com.business.entity.cust.CustInfoPerson;
import com.business.entity.proj.ProjInfo;
import com.business.exception.BusinessException;
import com.business.service.TableService;
import com.business.util.WorkflowUtil;
import com.kernal.annotation.WorkflowAction;

/**
 * 
 * @author 蔡雅超
 * @date 2013-7-11
 * @info项目立项的Action
 * @Copyright 
 * Tenwa
 */
@WorkflowAction(name = "projApprovalStartAction", description = "流程开始动作", workflowName = "项目立项流程")
@Component(value = "projApprovalStartAction")
public class ProjApprovalStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	@ResponseBody
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		try {
			Logger logger = Logger.getLogger(ProjApprovalStartAction.class
					.getName());
			String cust_id = variablesMap.get("cust_id");
			String typecode = variablesMap.get("typecode");
			String modecode = variablesMap.get("modecode");
			String twolevelstatus = variablesMap.get("twolevelstatus");
			String dealer = variablesMap.get("dealer");
			String deptid = variablesMap.get("deptid");
			System.out.println("二级经销商状态:twolevelstatus-->"+twolevelstatus);
			variablesMap.put("proj_info.twolevelstatus", twolevelstatus);
			variablesMap.put("cust_info.dealername", dealer);
			variablesMap.put("proj_info.deptid", deptid);
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			variablesMap.put("proj_info.loginusername", u.getUsername());
			
			CustInfo custInfo = (CustInfo) this.tableService.findEntityByID(
					CustInfo.class, cust_id);
			String proj_id = WorkflowUtil.getProjInfoSerialNumber(variablesMap,
					this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService
					.getBussinessDao().getJdbcTemplate());
			//查询项目表中是否有该客户的交易记录
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("custId.id", cust_id);
			//propertiesMap.put("projStatus", 11);//信审通过的项目
			List<ProjInfo> listporj=this.tableService.findEntityByProperties(ProjInfo.class, propertiesMap);
			if(listporj.size()>0){
				variablesMap.put("proj_info.prohistory","是");
			}else{
				variablesMap.put("proj_info.prohistory","否");
			}
			
			variablesMap.put("proj_info.projid", proj_id);
			variablesMap.put("proj_info.custid", cust_id);
			variablesMap.put("proj_info.businesstype", typecode);
			variablesMap.put("proj_info.businessmode", modecode);
			variablesMap.put("proj_info.custname", custInfo == null ? ""
					: custInfo.getCustName());
			variablesMap.put("proj_info.custnumber", custInfo == null ? ""
					: custInfo.getCustNumber());
			variablesMap.put("proj_info.cust_type", custInfo.getCustClass()
					.getCode() == null ? "" : custInfo.getCustClass().getCode()
					.equals("CUST_INFO_COMPANY") ? "法人" : "自然人");
			variablesMap.put("cust_info.custclass",
					custInfo.getCustClass() == null ? "" : custInfo
							.getCustClass().getName());
			variablesMap.put("proj_info.business_type", variablesMap
					.get("business_type"));
			/** 判断是法人 还是 自然人 */
			if (custInfo.getCustClass().getCode() != null) {
				if (custInfo.getCustClass().getCode().toUpperCase().equals(
						"CUST_INFO_COMPANY")) {// 法人
					CustInfoCompany company = custInfo.getCustInfoCompany();
					if (company != null) {
						variablesMap
								.put(
										"cust_info_company.legalrepresentative",
										company.getLegalRepresentative() != null ? company
												.getLegalRepresentative()
												: "");
						variablesMap.put("cust_info_company.taxregadd", company
								.getTaxRegAdd() != null ? company
								.getTaxRegAdd() : "");
						variablesMap.put("cust_info_company.ownership",
								(company.getOwnership() == null) ? "" : company
										.getOwnership().getName());
						variablesMap.put("cust_info_company.custscale",
								(company.getCustScale() == null) ? "" : company
										.getCustScale().getName());
						// 插入经销商
						variablesMap.put("proj_info.custdealer", (company
								.getDealerOrCompany() == null ? "" : company
								.getDealerOrCompany()));
					} else {
						// logger.debug("法人对象不存在");
						logger.info("company对象为空");
					}
				}
				if (custInfo.getCustClass().getCode().toUpperCase().equals(
						"CUST_INFO_PERSON")) {// 自然人
					CustInfoPerson person = custInfo.getCustInfoPerson();
					if (person != null) {
						variablesMap.put("cust_info_person.idcardno", person
								.getIdCardNo() == null ? "" : person
								.getIdCardNo());
						variablesMap.put("cust_info_person.homeadd", person
								.getHomeAdd() == null ? "" : person
								.getHomeAdd());
						variablesMap.put("cust_info_person.mobilenumber",
								person.getMobileNumber() == null ? "" : person
										.getMobileNumber());
						variablesMap.put("cust_info_person.unitname", person
								.getUnitName() == null ? "" : person
								.getUnitName());
						variablesMap.put("cust_info_person.custethnic", (person
								.getCustEthnic() == null) ? "" : person
								.getCustEthnic().getName());
						variablesMap.put("cust_info_person.driverlicensedate",
								person.getDriverLicenseDate() == null ? ""
										: person.getDriverLicenseDate());
						variablesMap.put("cust_info_person.spousename", person
								.getSpouseName() == null ? "" : person
								.getSpouseName());
						// 插入经销商
						variablesMap.put("proj_info.custdealer", (person
								.getDealerOrCompany() == null ? "" : person
								.getDealerOrCompany()));
					} else {
						logger.info("person对象为空");
					}
				}
				//获取负责该经销商的项目经理
				if(variablesMap.get("proj_info.custdealer")!=null){
				    Map<String,String> queryMap = new HashMap<String,String>();
				    queryMap.put("dealer_id", variablesMap.get("proj_info.custdealer"));
					JSONArray jsonArray = new JSONArray(this.tableService.getJsonArrayData("eleasing/workflow/public/creator_project_manager_new.xml", queryMap).toString());
					if(jsonArray.length()>0){
						JSONObject jsonObj = jsonArray.getJSONObject(0);
						variablesMap.put("proj_info.projmanage", jsonObj.get("id").toString());
					}
				}
				// 存入租金测算数据
				variablesMap.put("framework_condition.custname", custInfo
						.getCustName());
				variablesMap.put("framework_condition.custid", cust_id);
				variablesMap.put("framework_condition.projid", proj_id);
				variablesMap.put("framework_condition.docid",
						jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl()
								.getDbid()
								+ "");
				variablesMap.put("framework_condition.process", "proj_process");
				String mian = "mianfx01";
				DictionaryData fx = this.tableService.findEntityByID(DictionaryData.class,mian);
				if ( fx == null ) throw new BusinessException("请在数据字典中维护免罚息天数");
				variablesMap.put("framework_condition.freedefainterday",fx.getDescription());
				//带出项目立项流程上传的附件
				String processDefinitionId = "项目立项流程-1";
				this.tableService.getAttachment(variablesMap, proj_id,processDefinitionId);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}
}
