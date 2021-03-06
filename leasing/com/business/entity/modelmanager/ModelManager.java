package com.business.entity.modelmanager;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.business.entity.User;
import com.kernal.annotation.FieldName;

@Entity
@FieldName(name = "模块管理")
@Table(name="MODEL_MANAGER")
public class ModelManager {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	@FieldName(name="模块名称")
	@Column(name="MODEL_NAME")
	private String modelName;
	@FieldName(name="类名")
	@Column(name="JAVA_CLASS_NAME")
	private String javaClassName;
	@FieldName(name="jsp路径")
	@Column(name="JSP_PATH")
     private String jspPath;
	@FieldName(name="JSP名称")
	@Column(name="JSP_NAME")
	private String jspName;
	@FieldName(name="增加权限")
	@Column(name="ADD_RIGHT")
	private String addRight;
	@FieldName(name="删除权限")
	@Column(name="DELETE_RIGHT")
	private String deleteRight;
	@FieldName(name="修改权限")
	@Column(name="MODEL_RIGTH")
	private String modeRight;
	@FieldName(name="其他权限")
	@Column(name="OTHER_RIGHT")
	private String otherRight;
	@FieldName(name="备注")
	@Column(name="MEMO")
	private String memeo;
	
	@FieldName(name="字段配置数据")
	@OneToMany(mappedBy="modelmanager",fetch=FetchType.LAZY) 
	private Set<ModelFieldManager> baseModelFields = new HashSet<ModelFieldManager>();
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

	public String getModelName() {
		return modelName;
	}

	public String getJavaClassName() {
		return javaClassName;
	}

	public String getJspPath() {
		return jspPath;
	}

	public String getJspName() {
		return jspName;
	}

	public String getAddRight() {
		return addRight;
	}

	public String getDeleteRight() {
		return deleteRight;
	}

	public String getModeRight() {
		return modeRight;
	}

	public String getOtherRight() {
		return otherRight;
	}

	public String getMemeo() {
		return memeo;
	}

	public Set<ModelFieldManager> getBaseModelFields() {
		return baseModelFields;
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

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}

	public void setJspName(String jspName) {
		this.jspName = jspName;
	}

	public void setAddRight(String addRight) {
		this.addRight = addRight;
	}

	public void setDeleteRight(String deleteRight) {
		this.deleteRight = deleteRight;
	}

	public void setModeRight(String modeRight) {
		this.modeRight = modeRight;
	}

	public void setOtherRight(String otherRight) {
		this.otherRight = otherRight;
	}

	public void setMemeo(String memeo) {
		this.memeo = memeo;
	}

	public void setBaseModelFields(Set<ModelFieldManager> baseModelFields) {
		this.baseModelFields = baseModelFields;
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
