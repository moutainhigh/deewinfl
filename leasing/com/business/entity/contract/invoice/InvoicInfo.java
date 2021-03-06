package com.business.entity.contract.invoice;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.business.entity.User;
import com.business.entity.contract.ContractInfo;
import com.business.entity.contract.equip.ContractEquip;
import com.business.entity.cust.CustInfo;
import com.business.entity.proj.equip.ProjEquip;
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 周丽梅
 * @date 2014-2-17
 * @info开票信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "开票信息")
@Table(name="INVOICE_APPLY_INFO")
public class InvoicInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@FieldName(name="租赁物ID")
	@JoinColumn(name="equip_id")
	private ContractEquip contractequip;
	
	@FieldName(name="购货单位（人）")
	@Column(name="PURCHASE_UNIT")
	private String purchaseUnit;
	
	@FieldName(name="身份证号码/组织机构代码")
	@Column(name="ID_CARD")
	private String idCard;
	
	@FieldName(name="机动车辆生产企业名称")
	@Column(name="CAR_NAME")
	private String carName;
	
	
	
	@FieldName(name="厂牌型号")
	@Column(name="BRAND_MODEL")
	private String brandModel;
	
	@FieldName(name="合格证号")
	@Column(name="CERTIFICATE")
	private String certificate;
	
	@FieldName(name="进口证明书号")
	@Column(name="INLET_CERTIFICATE")
	private String inletCertificate;
	
	@FieldName(name="发动机号码")
	@Column(name="MOTOR_NUMBER")
	private String motorNumber;
	
	
	@FieldName(name="产地")
	@Column(name="LOCALITY")
	private String locality;
	
	@FieldName(name="商检单号")
	@Column(name="INSPECTION")
	private String inspection;
	
	@FieldName(name="价税合计")
	@Column(name="PRICE_TOTAL")
	private BigDecimal priceTotal;
	
	@FieldName(name="价税大写")
	@Column(name="PRICE_TOTAL_UPPER")
	private String priceTotalUpper;
	
	@FieldName(name="吨位")
	@Column(name="TONNAGE")
	private int tonnage;
	
	@FieldName(name="限乘人数")
	@Column(name="CONFINED_NUM")
	private String confinedNum;
	
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

	public String getPriceTotalUpper() {
		return priceTotalUpper;
	}

	public void setPriceTotalUpper(String priceTotalUpper) {
		this.priceTotalUpper = priceTotalUpper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	

	public BigDecimal getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(BigDecimal priceTotal) {
		this.priceTotal = priceTotal;
	}



	public int getTonnage() {
		return tonnage;
	}

	public void setTonnage(int tonnage) {
		this.tonnage = tonnage;
	}

	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	

	public String getBrandModel() {
		return brandModel;
	}

	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getInletCertificate() {
		return inletCertificate;
	}

	public void setInletCertificate(String inletCertificate) {
		this.inletCertificate = inletCertificate;
	}

	public String getMotorNumber() {
		return motorNumber;
	}

	public void setMotorNumber(String motorNumber) {
		this.motorNumber = motorNumber;
	}


	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getInspection() {
		return inspection;
	}

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	

	public String getConfinedNum() {
		return confinedNum;
	}

	public void setConfinedNum(String confinedNum) {
		this.confinedNum = confinedNum;
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

	public ContractEquip getContractequip() {
		return contractequip;
	}

	public void setContractequip(ContractEquip contractequip) {
		this.contractequip = contractequip;
	}
	
	
	

}
