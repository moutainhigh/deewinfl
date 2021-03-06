package com.business.entity.contract.reckon.fund;

import java.math.BigDecimal;
import java.util.HashMap;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;

import com.business.entity.DictionaryData;
import com.business.entity.User;
import com.business.entity.contract.ContractInfo;
import com.business.util.PenaltyUtil;
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 孙传良
 * @date 2013-3-6下午05:45:11
 * @info 合同租金计划
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同租金计划")
@Table(name="CONTRACT_FUND_RENT_PLAN")
public class ContractFundRentPlan {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name="合同编号")
	@JoinColumn(name="CONTRACT_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private ContractInfo contractId;
	
	@FieldName(name="报价编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="客户初始报价测算编号")
	@Column(name="QUOT_ID")
	@Deprecated
	private String quotId;
	
	@FieldName(name="多次起租编号")
	@Column(name="ONHIRE_ID")
	@Deprecated
	private String onhireId;
	
	@FieldName(name="期项")
	@Column(name="RENT_LIST")
	private Integer rentList;
	
	@FieldName(name="承付日期")
	@Column(name="PLAN_DATE",length=20)
	private String   planDate;
	
	@FieldName(name="租金")
	@Column(name="RENT",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal rent;

	@FieldName(name="租金调整值")
	@Column(name="RENT_ADJUST",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal rentAdjust;

	@FieldName(name="财务本金")
	@Column(name="CORPUS",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal corpus;
	
	@FieldName(name="业务本金")
	@Column(name="CORPUS_BUSINESS",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal corpusBusiness;

	@FieldName(name="年利率")
	@Column(name="YEAR_RATE",precision=22,scale=6, columnDefinition="NUMBER(22,6) DEFAULT 0")
	private BigDecimal yearRate;

	@FieldName(name="租息")
	@Column(name="INTEREST",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal interest;
	
	@FieldName(name="业务租息")
	@Column(name="INTEREST_BUSINESS",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal interestBusiness;

	@FieldName(name="财务租金余额")
	@Column(name="RENT_OVERAGE",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal rentOverage;

	@FieldName(name="财务本金余额")
	@Column(name="CORPUS_OVERAGE",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal corpusOverage;
	
	@FieldName(name="财务租息余额")
	@Column(name="INTEREST_OVERAGE",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal interestOverage;
	
	@Transient
	@FieldName(name="当期租金余额")
	private BigDecimal curRentOverage; 
	@Transient    
	@FieldName(name="当期本金余额")
	private BigDecimal curCorpusOverage;
	@Transient
	@FieldName(name="当期租息余额")
	private BigDecimal curInterestOverage;
	
	@Transient
	@FieldName(name="当期实收本金")
	private BigDecimal curCorpusIncome;
	
	@Transient
	@FieldName(name="当期实收租息")
	private BigDecimal curinterestIncome;
	
	@Transient
	@FieldName(name="当期实收罚息")
	private BigDecimal curpenaltyIncome;
	
	@Transient
	@FieldName(name="当期租息调整")
	private BigDecimal curinterestAdjustIncome;
	
	@Transient
	@FieldName(name="当期罚息调整")
	private BigDecimal curpenaltyAdjustIncome;
	
	@Transient
	@FieldName(name="当期状态")
	private DictionaryData planStatus;
	        
	@Transient
	@FieldName(name="回笼次数据")
	private Integer hireList;
	
 
	@FieldName(name="罚息余额")
	@Column(name="PENALTY_OVERAGE",precision=22,scale=2,columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal penaltyOverage;

	@FieldName(name="罚息")
	@Column(name="PENALTY",precision=22,scale=2, columnDefinition="NUMBER(22,2) DEFAULT 0")
	private BigDecimal penalty;
	
	@FieldName(name="状态")
	@JoinColumn(name="STATUS_")
	@ManyToOne(fetch = FetchType.LAZY)
	private DictionaryData status;
	
	@FieldName(name="卡扣导出状态,默认为0")
	@Column(name="CARD_EXPORT_STATUS", columnDefinition="VARCHAR2(32 CHAR) DEFAULT '0'")
	private String cardExportStatus;
	
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
	
	@FieldName(name="租金实收表")
	@OneToMany(mappedBy="planId",fetch=FetchType.LAZY) 
	@OrderBy(clause="HIRE_DATE asc")
	private Set<ContractFundRentInCome> contractFundRentInComes = new HashSet<ContractFundRentInCome>();
	
	@FieldName(name="流程状态")
	@Column(name="WORK_FLAG", length=2, columnDefinition="INT DEFAULT 0")
	private Integer workFlag;

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

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getQuotId() {
		return quotId;
	}

	public void setQuotId(String quotId) {
		this.quotId = quotId;
	}

	public String getOnhireId() {
		return onhireId;
	}

	public void setOnhireId(String onhireId) {
		this.onhireId = onhireId;
	}

	public Integer getRentList() {
		return rentList;
	}

	public void setRentList(Integer rentList) {
		this.rentList = rentList;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getRentAdjust() {
		return rentAdjust;
	}

	public void setRentAdjust(BigDecimal rentAdjust) {
		this.rentAdjust = rentAdjust;
	}

	public BigDecimal getCorpus() {
		return corpus;
	}

	public void setCorpus(BigDecimal corpus) {
		this.corpus = corpus;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getRentOverage() {
		return rentOverage;
	}

	public void setRentOverage(BigDecimal rentOverage) {
		this.rentOverage = rentOverage;
	}

	public BigDecimal getCorpusOverage() {
		return corpusOverage;
	}

	public void setCorpusOverage(BigDecimal corpusOverage) {
		this.corpusOverage = corpusOverage;
	}

	public BigDecimal getInterestOverage() {
		return interestOverage;
	}

	public void setInterestOverage(BigDecimal interestOverage) {
		this.interestOverage = interestOverage;
	}

	public BigDecimal getPenaltyOverage() {
		return penaltyOverage;
	}

	public void setPenaltyOverage(BigDecimal penaltyOverage) {
		this.penaltyOverage = penaltyOverage;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public DictionaryData getStatus() {
		return status;
	}

	public void setStatus(DictionaryData status) {
		this.status = status;
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

	public BigDecimal getCorpusBusiness() {
		return corpusBusiness;
	}

	public void setCorpusBusiness(BigDecimal corpusBusiness) {
		this.corpusBusiness = corpusBusiness;
	}

	public BigDecimal getInterestBusiness() {
		return interestBusiness;
	}

	public void setInterestBusiness(BigDecimal interestBusiness) {
		this.interestBusiness = interestBusiness;
	}

	public Set<ContractFundRentInCome> getContractFundRentInComes() {
		return contractFundRentInComes;
	}

	public void setContractFundRentInComes(Set<ContractFundRentInCome> contractFundRentInComes) {
		this.contractFundRentInComes = contractFundRentInComes;
	}
	
/**
 * 
 * @date 2013-6-20
 * xuyunlong
 * @param palnSMap 租金状态
 * @param computer_date  计算日期
 * @param rate 罚息利率
 * @param freeDefaInterDay 免罚息天数
 * 初始化租金罚息和余额
 */
	public void initContractFundRentPlan(HashMap<String,DictionaryData> palnSMap,String computer_date,BigDecimal rate,int freeDefaInterDay){
		
		BigDecimal rentincome=BigDecimal.ZERO;
		BigDecimal corpusincome=BigDecimal.ZERO;
		BigDecimal interestincome=BigDecimal.ZERO;
		BigDecimal penaltyincome=BigDecimal.ZERO;
		BigDecimal interestAdjust=BigDecimal.ZERO;
		BigDecimal penaltyAdjust=BigDecimal.ZERO;
	    Integer hireNumber=0;
		String max_hiredate = "";
	    
	    if(this.getContractFundRentInComes().size()>0){
	    	hireNumber=this.getContractFundRentInComes().size();
	       for(ContractFundRentInCome ci:this.getContractFundRentInComes()){
	    	   rentincome=rentincome.add(ci.getRent());
	    	   corpusincome=corpusincome.add(ci.getCorpus());
	    	   interestincome=interestincome.add(ci.getInterest()).add(ci.getInterestAdjust());
	    	   penaltyincome=penaltyincome.add(ci.getPenalty()).add(ci.getPenaltyAdjust());
	    	   interestAdjust=interestAdjust.add(ci.getInterestAdjust());
	    	   penaltyAdjust=penaltyAdjust.add(ci.getPenaltyAdjust());
	    	   max_hiredate = ci.getHireDate();
	       }
	    }
	    this.setCurCorpusIncome(corpusincome);
	    this.setCurinterestIncome(interestincome);
	    this.setCurpenaltyIncome(penaltyincome);
	    this.setCurinterestAdjustIncome(interestAdjust);
	    this.setCurpenaltyAdjustIncome(penaltyAdjust);
	    this.setCurRentOverage(this.getRent().subtract(rentincome));   
	    this.setCurCorpusOverage(this.getCorpus().subtract(corpusincome));
	    this.setCurInterestOverage(this.getInterest().subtract(interestincome));
	    this.setHireList(hireNumber);
	    
	    //PLANMANYSTATUSOVER	超额回笼
	    //PLANMANYSTATUSNO		未收款
	    //PLANMANYSTATUSPART	部份收款
	    //PLANMANYSTATUSALL		已结清

	    if(rentincome.compareTo(BigDecimal.ZERO)==0){
	    	this.setPlanStatus(palnSMap.get("PLANMANYSTATUSNO"));
	    }else if(rentincome.equals(this.getRent())&&(this.getPenaltyOverage() == null || BigDecimal.ZERO.compareTo(this.getPenaltyOverage())==0)){
	    	this.setPlanStatus(palnSMap.get("PLANMANYSTATUSALL"));
	    	computer_date = max_hiredate;
	    }else {
	    	this.setPlanStatus(palnSMap.get("PLANMANYSTATUSPART"));
	    }
	    if(rentincome.compareTo(this.rent)>0){
	    	computer_date = max_hiredate;
	    	this.setPlanStatus(palnSMap.get("PLANMANYSTATUSOVER"));
	    }
	    
	    
	    
	    //计划租金罚息
	    //德银罚息小数进位
	    this.setPenaltyOverage(PenaltyUtil.getRentPenalty(this, computer_date, rate,freeDefaInterDay,true).get(0).setScale(0,BigDecimal.ROUND_HALF_UP));
	    this.setPenalty(this.getPenaltyOverage().add(penaltyincome).setScale(0,BigDecimal.ROUND_HALF_UP));   
	}
	public DictionaryData getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(DictionaryData planStatus) {
		this.planStatus = planStatus;
	}

	public BigDecimal getCurRentOverage() {
		return curRentOverage;
	}

	public BigDecimal getCurCorpusOverage() {
		return curCorpusOverage;
	}

	public BigDecimal getCurInterestOverage() {
		return curInterestOverage;
	}

	public void setCurRentOverage(BigDecimal curRentOverage) {
		this.curRentOverage = curRentOverage;
	}

	public void setCurCorpusOverage(BigDecimal curCorpusOverage) {
		this.curCorpusOverage = curCorpusOverage;
	}

	public void setCurInterestOverage(BigDecimal curInterestOverage) {
		this.curInterestOverage = curInterestOverage;
	}

	public Integer getHireList() {
		return hireList;
	}

	public void setHireList(Integer hireList) {
		this.hireList = hireList;
	}

	public BigDecimal getCurCorpusIncome() {
		return curCorpusIncome;
	}

	public BigDecimal getCurinterestIncome() {
		return curinterestIncome;
	}

	public BigDecimal getCurpenaltyIncome() {
		return curpenaltyIncome;
	}

	public BigDecimal getCurinterestAdjustIncome() {
		return curinterestAdjustIncome;
	}

	public BigDecimal getCurpenaltyAdjustIncome() {
		return curpenaltyAdjustIncome;
	}

	public void setCurCorpusIncome(BigDecimal curCorpusIncome) {
		this.curCorpusIncome = curCorpusIncome;
	}

	public void setCurinterestIncome(BigDecimal curinterestIncome) {
		this.curinterestIncome = curinterestIncome;
	}

	public void setCurpenaltyIncome(BigDecimal curpenaltyIncome) {
		this.curpenaltyIncome = curpenaltyIncome;
	}

	public void setCurinterestAdjustIncome(BigDecimal curinterestAdjustIncome) {
		this.curinterestAdjustIncome = curinterestAdjustIncome;
	}

	public void setCurpenaltyAdjustIncome(BigDecimal curpenaltyAdjustIncome) {
		this.curpenaltyAdjustIncome = curpenaltyAdjustIncome;
	}

	public Integer getWorkFlag() {
		return workFlag;
	}

	public void setWorkFlag(Integer workFlag) {
		this.workFlag = workFlag;
	}

	public String getCardExportStatus() {
		return cardExportStatus;
	}

	public void setCardExportStatus(String cardExportStatus) {
		this.cardExportStatus = cardExportStatus;
	}
	
	
   
}
