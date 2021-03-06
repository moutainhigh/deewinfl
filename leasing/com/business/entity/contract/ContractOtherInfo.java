package com.business.entity.contract;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.business.entity.User;
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 孙传良
 * @date 2013-3-4下午04:31:01
 * @info 合同其他基本信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同其他信息表")
@Table(name="CONTRACT_OTHER_INFO")
public class ContractOtherInfo {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同号")
	@Index(name="INX_CONTRACT_OTHER_INFO_ID")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="风险防范措施")
	@Column(name="RISK_PREVENTION_MEASURES")
	private String riskPreventionMeasures;
	
	@FieldName(name="其他商务条件")
	@Column(name="OTHER_CONDTION")
	private String otherCondtion;
	
	@FieldName(name="合同签约地点")
	@Column(name="SIGN_PLACE")
	private String signPlace;
	
	@FieldName(name="合同签约日期")
	@Column(name="SIGN_DATE", length=20)
	private String signDate;
	
	@FieldName(name="合同签约人")
	@Column(name="SIGN_PERSON", length=200)
	private String signPerson;

	@FieldName(name="建造开始日期")
	@Column(name="TERMINALBEGINDATE", length=20)
	private String terminalBeginDate;
	
	@FieldName(name="建造结束日期")
	@Column(name="TERMINALENDDATE", length=20)
	private String terminalEndDate;
	
	@FieldName(name="使用开始日期")
	@Column(name="TERMINALUSEBEGINTIME", length=20)
	private String terminalUseBeginTime;
	
	@FieldName(name="使用结束日期")
	@Column(name="TERMINALUSEENDTIME", length=20)
	private String terminalUseEndTime;

	@FieldName(name="是否提前放款备注(德银)")
	@Column(name="IS_ADVANCE_MEMO", length=2000)
	private String isAdvanceMemo;
	
	@FieldName(name = "标的物详情(德银)")
	@Column(name="EQUIP_INFO", length=2000)
	private String equipInfo;
	
	@FieldName(name="合同变更日期")
	@Column(name="CONTRACT_CHANGE_DATE", length=20)
	private String contractChangeDate;
	
	@FieldName(name="合同变更前内容")
	@Column(name="CONTRACT_CHANGE_BEFORE_MEMO", length=2000)
	private String contractChangeBeforMemo ;
	
	@FieldName(name="合同变更后内容")
	@Column(name="CONTRACT_CHANGE_AFTER_MEMO", length=2000)
	private String contractChangeAfterMemo;
	
	@FieldName(name="合同变更内容")
	@Column(name="CONTRACT_CHANGE_MEMO", length=2000)
	private String contractChangeMemo;
	
	public String getContractChangeDate() {
		return contractChangeDate;
	}

	public void setContractChangeDate(String contractChangeDate) {
		this.contractChangeDate = contractChangeDate;
	}

	public String getContractChangeBeforMemo() {
		return contractChangeBeforMemo;
	}

	public void setContractChangeBeforMemo(String contractChangeBeforMemo) {
		this.contractChangeBeforMemo = contractChangeBeforMemo;
	}

	public String getContractChangeAfterMemo() {
		return contractChangeAfterMemo;
	}

	public void setContractChangeAfterMemo(String contractChangeAfterMemo) {
		this.contractChangeAfterMemo = contractChangeAfterMemo;
	}

	public String getContractChangeMemo() {
		return contractChangeMemo;
	}

	public void setContractChangeMemo(String contractChangeMemo) {
		this.contractChangeMemo = contractChangeMemo;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
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

	public String getRiskPreventionMeasures() {
		return riskPreventionMeasures;
	}

	public void setRiskPreventionMeasures(String riskPreventionMeasures) {
		this.riskPreventionMeasures = riskPreventionMeasures;
	}

	public String getOtherCondtion() {
		return otherCondtion;
	}

	public void setOtherCondtion(String otherCondtion) {
		this.otherCondtion = otherCondtion;
	}

	public String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSignPerson() {
		return signPerson;
	}

	public void setSignPerson(String signPerson) {
		this.signPerson = signPerson;
	}

	public String getTerminalBeginDate() {
		return terminalBeginDate;
	}

	public void setTerminalBeginDate(String terminalBeginDate) {
		this.terminalBeginDate = terminalBeginDate;
	}

	public String getTerminalEndDate() {
		return terminalEndDate;
	}

	public void setTerminalEndDate(String terminalEndDate) {
		this.terminalEndDate = terminalEndDate;
	}

	public String getTerminalUseBeginTime() {
		return terminalUseBeginTime;
	}

	public void setTerminalUseBeginTime(String terminalUseBeginTime) {
		this.terminalUseBeginTime = terminalUseBeginTime;
	}

	public String getTerminalUseEndTime() {
		return terminalUseEndTime;
	}

	public void setTerminalUseEndTime(String terminalUseEndTime) {
		this.terminalUseEndTime = terminalUseEndTime;
	}

	public String getIsAdvanceMemo() {
		return isAdvanceMemo;
	}

	public void setIsAdvanceMemo(String isAdvanceMemo) {
		this.isAdvanceMemo = isAdvanceMemo;
	}

	public String getEquipInfo() {
		return equipInfo;
	}

	public void setEquipInfo(String equipInfo) {
		this.equipInfo = equipInfo;
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
