package com.business.entity.contract.doc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.business.entity.DictionaryData;
import com.business.entity.User;
import com.business.entity.contract.ContractInfo;
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 孙传良
 * @date 2013-8-5下午05:17:22
 * @info 合同档案寄送管理
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同档案寄送管理")
@Table(name="CONTRACT_DOC_SEND_INFO")
public class ContractDocSendInfo {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractID;
	
	@FieldName(name="合同档案名称[D]")
	@Column(name="DOC_NAME")
	private String docName;
	
	@FieldName(name = "合同对方")
	@Column(name="CONTRACT_PERSON", length=100)
	private String contractPerson;
	
	@FieldName(name = "标的物详情")
	@Column(name="EQUIP_INFO", length=2000)
	private String equipInfo;
	
	@FieldName(name = "合同份数")
	@Column(name="DOC_PART")
	private Integer docPart;
	
	@FieldName(name = "我方签约人")
	@Column(name="CONTRACT_A",length=50)
	private String contractA;
	
	@FieldName(name = "对方签约人")
	@Column(name="CONTRACT_B",length=50)
	private String contractB;
	
	@FieldName(name = "合同签订日期")
	@Column(name="CONTRACT_DATE",length=20)
	private String contractDate;
	
	@FieldName(name = "寄送份数")
	@Column(name="SEND_PART")
	private Integer sendPart;
	
	@FieldName(name = "快递单号")
	@Column(name="SEND_NO",length=20)
	private String sendNo;
	
	@FieldName(name = "主车台数")
	@Column(name="MAINCOUNT",length=20)
	private String maincount;
	
	@FieldName(name = "挂车台数")
	@Column(name="GCOUNT",length=20)
	private String gcount;
	
	@FieldName(name = "是否抵押")
	@Column(name="SDY",length=20)
	private String sdy;
	
	@FieldName(name = "归档号")
	@Column(name="DOC_NUMBER",length=20)
	private String docNumber;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE")
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContractInfo getContractID() {
		return contractID;
	}

	public void setContractID(ContractInfo contractID) {
		this.contractID = contractID;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getContractPerson() {
		return contractPerson;
	}

	public void setContractPerson(String contractPerson) {
		this.contractPerson = contractPerson;
	}

	public String getEquipInfo() {
		return equipInfo;
	}

	public void setEquipInfo(String equipInfo) {
		this.equipInfo = equipInfo;
	}

	public Integer getDocPart() {
		return docPart;
	}

	public void setDocPart(Integer docPart) {
		this.docPart = docPart;
	}

	public String getContractA() {
		return contractA;
	}

	public void setContractA(String contractA) {
		this.contractA = contractA;
	}

	public String getContractB() {
		return contractB;
	}

	public void setContractB(String contractB) {
		this.contractB = contractB;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public Integer getSendPart() {
		return sendPart;
	}

	public void setSendPart(Integer sendPart) {
		this.sendPart = sendPart;
	}

	public String getSendNo() {
		return sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
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

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getMaincount() {
		return maincount;
	}

	public void setMaincount(String maincount) {
		this.maincount = maincount;
	}

	public String getGcount() {
		return gcount;
	}

	public void setGcount(String gcount) {
		this.gcount = gcount;
	}

	public String getSdy() {
		return sdy;
	}

	public void setSdy(String sdy) {
		this.sdy = sdy;
	}

}
