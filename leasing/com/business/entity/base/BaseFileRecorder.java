package com.business.entity.base;

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
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 徐云龙
 * @date 2013-8-6下午14:18:45
 * @info
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "上传下载文件记录")
@Table(name="B_FILE_RECORDER")
public class BaseFileRecorder {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name="文件")
	@ManyToOne(targetEntity=BaseFile.class,fetch=FetchType.LAZY)
	@JoinColumn(name="BASE_FILE")
    private BaseFile baseFile;
    
	
	@FieldName(name="操作类型  上传，下载")
	@Column(name="OPERATOR_TYPE")
	private String operatorType;
	
	
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
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public BaseFile getBaseFile() {
		return baseFile;
	}

	public void setBaseFile(BaseFile baseFile) {
		this.baseFile = baseFile;
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
