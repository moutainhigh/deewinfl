package com.business.entity.modelmanager;

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

public@Entity
@FieldName(name = "模块管理")
@Table(name="MODEL_FIELD_MANAGER")
class ModelFieldManager {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="主模块")
	@ManyToOne(targetEntity=ModelManager.class,fetch=FetchType.LAZY)
	@JoinColumn(name="MODEL_MANAGER")
	private ModelManager modelmanager;
	
	@FieldName(name="标题")
	@Column(name="TITLE")
	private String title;
	@FieldName(name="域名")
	@Column(name="FIELD_NAME")
	private String fieldname;
	@FieldName(name="数据类型")
	@Column(name="DATA_TYPE")
	private String datatype;
	@FieldName(name="校验类型")
	@Column(name="DATA_CHECK")
	private String datacheck;
	@FieldName(name="是否换行")
	@Column(name="CHANGE_ROW")
	private String changerow;
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

	public ModelManager getModelmanager() {
		return modelmanager;
	}

	public String getTitle() {
		return title;
	}

	public String getFieldname() {
		return fieldname;
	}

	public String getDatatype() {
		return datatype;
	}

	public String getDatacheck() {
		return datacheck;
	}

	public String getChangerow() {
		return changerow;
	}

	public User getCreator() {
		return creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setModelmanager(ModelManager modelmanager) {
		this.modelmanager = modelmanager;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public void setDatacheck(String datacheck) {
		this.datacheck = datacheck;
	}

	public void setChangerow(String changerow) {
		this.changerow = changerow;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


}
