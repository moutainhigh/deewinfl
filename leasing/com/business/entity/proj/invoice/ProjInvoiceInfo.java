package com.business.entity.proj.invoice;

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

import com.business.entity.DictionaryData;
import com.business.entity.User;
import com.business.entity.proj.ProjInfo;
import com.kernal.annotation.FieldName;
 
@Entity
@FieldName(name = "项目开票信息")
@Table(name="PROJ_INVOICE_INFO")
public class ProjInvoiceInfo {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="项目编号")
	@OneToOne(targetEntity=ProjInfo.class)
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name="是否开票")
	@Column(name="IS_KFP")
	private String iskfp;
	
	@FieldName(name="纳税人识别号")
	@Column(name="TAXNUMBER")
	private String taxNumber;
	
	@FieldName(name="开户账户")
	@Column(name="INVOICENUMBER")
	private String invoiceNumber;
	
	@FieldName(name="注册地址")
	@Column(name="INVOICE_ADDRESS")
	private String invoiceAddress;

	@FieldName(name="电话")
	@Column(name="INVOICE_TEL",length=100)
	private String invoiceTel;
		
	@FieldName(name="开户行")
	@Column(name="INVOICE_OPENBANK")
	private String invoiceOpenBank;
	
	
	@FieldName(name = "纳税人类别[是否一般纳税人（是/否）]")
	@JoinColumn(name="TAX_REG_Type")
	@ManyToOne(fetch = FetchType.LAZY)
	private DictionaryData taxRegType;
	
	@FieldName(name="发票种类")
	@JoinColumn(name="INVOICE_TYPE")
	@ManyToOne(fetch = FetchType.LAZY)
	private DictionaryData invoiceType;
	
	@FieldName(name="开票说明")
	@Column(name="INVOICE_MEMO", length=2000)	
	private String invoicememo;
	
	@FieldName(name="开票备注")
	@Column(name="INVOICE_REMARKS", length=2000)	
	private String invoiceRemarks;
	
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

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public String getInvoiceTel() {
		return invoiceTel;
	}

	public void setInvoiceTel(String invoiceTel) {
		this.invoiceTel = invoiceTel;
	}

	public String getInvoiceOpenBank() {
		return invoiceOpenBank;
	}

	public void setInvoiceOpenBank(String invoiceOpenBank) {
		this.invoiceOpenBank = invoiceOpenBank;
	}

	public DictionaryData getTaxRegType() {
		return taxRegType;
	}

	public void setTaxRegType(DictionaryData taxRegType) {
		this.taxRegType = taxRegType;
	}

	public DictionaryData getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(DictionaryData invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoicememo() {
		return invoicememo;
	}

	public void setInvoicememo(String invoicememo) {
		this.invoicememo = invoicememo;
	}

	public String getInvoiceRemarks() {
		return invoiceRemarks;
	}

	public void setInvoiceRemarks(String invoiceRemarks) {
		this.invoiceRemarks = invoiceRemarks;
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

	public String getIskfp() {
		return iskfp;
	}

	public void setIskfp(String iskfp) {
		this.iskfp = iskfp;
	}
 
	
}  
