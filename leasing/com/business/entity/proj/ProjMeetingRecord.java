package com.business.entity.proj;

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
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 徐云龙
 * @date 2013-3-5上午10:17:01
 * @info 项目信审会议记录实体类
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "项目流程会议记录")
@Table(name="PROJ_MEET_RECORD")
public class ProjMeetingRecord {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="项目编号")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name="上会时间")
	@Column(name="PROJ_MEET_TIME")
	 private String projMeetTime;
	
	@FieldName(name="上会地点")
	@Column(name="PROJ_MEET_PLACE")
	 private String projMeetPlace;
	
	@FieldName(name="主持人")
	@JoinColumn(name="MEETTING_COMPERE_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private User  meettingCompereId;
	
	@FieldName(name="会议方式")
	@JoinColumn(name="MEET_TYPE")
	@ManyToOne(fetch = FetchType.LAZY)
	private DictionaryData meetType;

	@FieldName(name="审批内容")
	@Column(name="CREDIT_COMMENT")
	private String creditComment;
	
	@FieldName(name="参加审批人员")
	@Column(name="CREDIT_PERSONS")
	private String creditPersons;
	
	@FieldName(name="列席会议人员")
	@Column(name="MEET_PERSON")
	private String meetPerson;
	
	@FieldName(name=" 缺席会议人员")
	@Column(name="meet_no_Person")
	private String meetNoPerson ;
	
	@FieldName(name="会议记录")
	@Column(name="MEET_RECORD")
	private String meetRecord;
	
	@FieldName(name="会议结论")
	@Column(name="MEET_RESULT")
	private String meetResult;
	
	@FieldName(name="流程名称")
	@Column(name="FLOW_NAME")
	private String flowName;
	
	@FieldName(name="会议类型初审/审批")
	@Column(name="MEET_RECORD_TYPE")
	private String meetRecordType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}
	

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getProjMeetTime() {
		return projMeetTime;
	}

	public String getCreditComment() {
		return creditComment;
	}

	public String getCreditPersons() {
		return creditPersons;
	}

	public String getMeetPerson() {
		return meetPerson;
	}

	public String getMeetNoPerson() {
		return meetNoPerson;
	}

	public String getMeetRecord() {
		return meetRecord;
	}

	

	public void setProjMeetTime(String projMeetTime) {
		this.projMeetTime = projMeetTime;
	}
	

	public void setCreditComment(String creditComment) {
		this.creditComment = creditComment;
	}

	public void setCreditPersons(String creditPersons) {
		this.creditPersons = creditPersons;
	}

	public void setMeetPerson(String meetPerson) {
		this.meetPerson = meetPerson;
	}

	public void setMeetNoPerson(String meetNoPerson) {
		this.meetNoPerson = meetNoPerson;
	}

	public void setMeetRecord(String meetRecord) {
		this.meetRecord = meetRecord;
	}

	 
	public String getMeetRecordType() {
		return meetRecordType;
	}

	 

	public void setMeetRecordType(String meetRecordType) {
		this.meetRecordType = meetRecordType;
	}

	public String getProjMeetPlace() {
		return projMeetPlace;
	}

	public void setProjMeetPlace(String projMeetPlace) {
		this.projMeetPlace = projMeetPlace;
	}

	public DictionaryData getMeetType() {
		return meetType;
	}

	public void setMeetType(DictionaryData meetType) {
		this.meetType = meetType;
	}
	
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getMeetResult() {
		return meetResult;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setMeetResult(String meetResult) {
		this.meetResult = meetResult;
	}
	public User getMeettingCompereId() {
		return meettingCompereId;
	}
	public void setMeettingCompereId(User meettingCompereId) {
		this.meettingCompereId = meettingCompereId;
	}

	
}
