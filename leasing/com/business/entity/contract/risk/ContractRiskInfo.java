package com.business.entity.contract.risk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.business.entity.User;
import com.business.entity.contract.ContractInfo;
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 孙传良
 * @date 2013-8-5下午06:23:49
 * @info 合同风险预警
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同风险预警(德银)")
@Table(name = "CONTRACT_RISK_INFO")
public class ContractRiskInfo {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@ManyToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@FieldName(name="合同号")
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="风险因由")
	@Column(name="RISK_REASON", length=2000)
	private String riskReason;
	
	@FieldName(name="发现时间")
	@Column(name="find_Date", length=20)
	private String findDate;
	
	@FieldName(name="形成时间")
	@Column(name="BECOMING_DATE", length=20)
	private String becomingDate;
	
	@FieldName(name="监控方式")
	@Column(name="INSPECT_TYPE", length=50)
	private String inspectType;
	
	@FieldName(name="风险因由简述")
	@Column(name="REASON_INFO", length=2000)
	private String reasonInfo;
	
	@FieldName(name="风险破坏与损失程度")
	@Column(name="RISK_LEVEL", length=2000)
	private String riskLevel;
	
	@FieldName(name="风险恶性发展可能性")
	@Column(name="RISK_PROSPECT", length=2000)
	private String riskProspect;
	
	@FieldName(name="采取的措施和紧急处置意见")
	@Column(name="RISK_MEASURE", length=2000)
	private String riskMeasure;
	
	@FieldName(name="处置意见")
	@Column(name="RISK_IDEA", length=2000)
	private String riskIdea;
	
	@FieldName(name="风险等级定型结论")
	@Column(name="LEVEL_REASON", length=2000)
	private String levelReason;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="预警报告人")
	@JoinColumn(name="RISK_PERSON")
	private User riskPerson;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getRiskReason() {
		return riskReason;
	}

	public void setRiskReason(String riskReason) {
		this.riskReason = riskReason;
	}

	public String getFindDate() {
		return findDate;
	}

	public void setFindDate(String findDate) {
		this.findDate = findDate;
	}

	public String getBecomingDate() {
		return becomingDate;
	}

	public void setBecomingDate(String becomingDate) {
		this.becomingDate = becomingDate;
	}

	public String getInspectType() {
		return inspectType;
	}

	public void setInspectType(String inspectType) {
		this.inspectType = inspectType;
	}

	public String getReasonInfo() {
		return reasonInfo;
	}

	public void setReasonInfo(String reasonInfo) {
		this.reasonInfo = reasonInfo;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRiskProspect() {
		return riskProspect;
	}

	public void setRiskProspect(String riskProspect) {
		this.riskProspect = riskProspect;
	}

	public String getRiskMeasure() {
		return riskMeasure;
	}

	public void setRiskMeasure(String riskMeasure) {
		this.riskMeasure = riskMeasure;
	}

	public String getRiskIdea() {
		return riskIdea;
	}

	public void setRiskIdea(String riskIdea) {
		this.riskIdea = riskIdea;
	}

	public String getLevelReason() {
		return levelReason;
	}

	public void setLevelReason(String levelReason) {
		this.levelReason = levelReason;
	}

	public User getRiskPerson() {
		return riskPerson;
	}

	public void setRiskPerson(User riskPerson) {
		this.riskPerson = riskPerson;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}
