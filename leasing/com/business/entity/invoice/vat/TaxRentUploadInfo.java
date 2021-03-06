package com.business.entity.invoice.vat;


import java.math.BigDecimal;

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
import com.business.entity.base.BaseFile;
import com.kernal.annotation.FieldName;

/**
 * @author Toybaby
 *	2013-10-11
 *  资计发票信息
 *  
 */
@Entity
@FieldName(name = "租金发票导入数据存储")
@Table(name="TAX_RENT_UPLOAD_INFO")
public class TaxRentUploadInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	
	@FieldName(name="合同编号")
	@Column(name="CONTRACT_ID", length=50)
	private String contractId;
	
	@FieldName(name="客户名称")
	@Column(name="CUST_NAME", length=200)
	private String custName;
	
	@FieldName(name="导出流水编号")
	@Column(name="OUT_NO", length=50)
	private String outNo;
	
	@FieldName(name="期次")
	@Column(name="rent_list")
	private Integer rentList;
	
	@FieldName(name="收款日期")
	@Column(name="INCOME_DATE", length=200)
	private String incomeDate;
	
	@FieldName(name="开票金额")
	@Column(name="INVOICE_MONEY", precision=22,scale=2)
	private BigDecimal invoiceMoney;
	
	@FieldName(name="销项税金")
	@Column(name="TAX_MONEY",precision=22,scale=2)
	private BigDecimal taxMoney;
	
	@FieldName(name="金额类型")
	@Column(name="RENT_TYPE", length=50)
	private String rentType;
	

	@FieldName(name="开票类类型")
	@Column(name="INVOICE_TYPE", length=50)
	private String invoiceType;
	
	@FieldName(name="发票号")
	@Column(name="INVOICE_NO", length=50)
	private String invoiceNo;
	
	@FieldName(name="开票日期")
	@Column(name="INVOICE_DATE", length=200)
	private String invoiceDate;
	
	
	@FieldName(name="快递单号")
	@Column(name="EMS_NO", length=50)
	private String emsNo;
	
	@FieldName(name="寄送时间")
	@Column(name="SEND_DATE", length=20)
	private String sendDate;
	
	@FieldName(name="备注")
	@Column(name="MEMO", length=500)
	private String memo;
	
	@FieldName(name="开户行")
	@Column(name="TAX_BANK", length=100)
	private String taxBank;
	
	
	@FieldName(name="开户帐号")
	@Column(name="TAX_ACC", length=50)
	private String taxAcc;
	
	@FieldName(name="电话")
	@Column(name="TAX_TEL", length=50)
	private String taxTel;
	
	@FieldName(name="纳税人识别号")
	@Column(name="TAX_REG_CODE", length=50)
	private String taxRegCode;
	
	@FieldName(name="地址")
	@Column(name="TAX_ADDR", length=50)
	private String taxAddr;
	
	
	
	@FieldName(name="导出人")
	@Column(name="EXP_USER", length=50)
	private String exportUser;
	
	@FieldName(name="导出时间")
	@Column(name="EXPORT_DATE", length=50)
	private String exportDate;
	
	@FieldName(name="红冲状态，红冲状态1 代表‘已红冲’ 2代表‘红冲发票’")
	@Column(name="HC_STATUS", length=20)
	private String hcStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="上传文件名")
	@JoinColumn(name="UPLOAD_ID")
	private BaseFile upLoadId ;

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

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

	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}



	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}


	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}



	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	
	
	public String getOutNo() {
		return outNo;
	}

	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}

	public String getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}


	public BigDecimal getInvoiceMoney() {
		return invoiceMoney;
	}

	public void setInvoiceMoney(BigDecimal invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getTaxBank() {
		return taxBank;
	}

	public void setTaxBank(String taxBank) {
		this.taxBank = taxBank;
	}

	public String getTaxAcc() {
		return taxAcc;
	}

	public void setTaxAcc(String taxAcc) {
		this.taxAcc = taxAcc;
	}

	public String getTaxTel() {
		return taxTel;
	}

	public void setTaxTel(String taxTel) {
		this.taxTel = taxTel;
	}

	public String getTaxRegCode() {
		return taxRegCode;
	}

	public void setTaxRegCode(String taxRegCode) {
		this.taxRegCode = taxRegCode;
	}

	public String getTaxAddr() {
		return taxAddr;
	}

	public void setTaxAddr(String taxAddr) {
		this.taxAddr = taxAddr;
	}

	public String getEmsNo() {
		return emsNo;
	}

	public void setEmsNo(String emsNo) {
		this.emsNo = emsNo;
	}

	public String getExportUser() {
		return exportUser;
	}

	public void setExportUser(String exportUser) {
		this.exportUser = exportUser;
	}

	public String getExportDate() {
		return exportDate;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}

	public String getHcStatus() {
		return hcStatus;
	}

	public void setHcStatus(String hcStatus) {
		this.hcStatus = hcStatus;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public Integer getRentList() {
		return rentList;
	}

	public void setRentList(Integer rentList) {
		this.rentList = rentList;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BigDecimal getTaxMoney() {
		return taxMoney;
	}
	
}
