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
 * @date 2013-8-5下午07:30:02
 * @info 合同风险处置
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同风险处置(德银)")
@Table(name = "CONTRACT_RISK_PROCESS1")
public class ContractRiskProcess1 {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@ManyToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@FieldName(name="合同号")
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="被告人")
	@Column(name="CUST_ID", length=20)
	private String custId;
		
	@FieldName(name="处理方式")
	@Column(name="LAW_TYPE", length=50)
	private String lawType;
	
	
	
	@FieldName(name="处理时间")
	@Column(name="LAW_DATE", length=20)
	private String lawDate;
	
	@FieldName(name="采取措施详情")
	@Column(name="LAW_MEMO", length=2000)
	private String lawMemo;

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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getLawType() {
		return lawType;
	}

	public void setLawType(String lawType) {
		this.lawType = lawType;
	}

	public String getLawDate() {
		return lawDate;
	}

	public void setLawDate(String lawDate) {
		this.lawDate = lawDate;
	}

	public String getLawMemo() {
		return lawMemo;
	}

	public void setLawMemo(String lawMemo) {
		this.lawMemo = lawMemo;
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
