package com.business.entity.interest;

import java.math.BigDecimal;
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
import com.business.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.kernal.annotation.FieldName;

/**
 * 
 * @author 孙传良
 * @date 2013-3-7上午10:45:07
 * @info 央行基准利率调整记录
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "央行基准利率调整记录")
@Table(name="FUND_STANDARD_INTEREST")
public class FundStandardInterest {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="利率开始执行日期")
	@Column(name="START_DATE_",length=20)
	private String   startDate;

	@FieldName(name="利息调整幅度_六月")
	@Column(name="RATE_HALF",precision=22,scale=6)
	private BigDecimal rateHalf;
	
	@FieldName(name="利息调整幅度_1年")
	@Column(name="RATE_ONE",precision=22,scale=6)
	private BigDecimal rateOne;

	@FieldName(name="利息调整幅度_3年")
	@Column(name="RATE_THREE",precision=22,scale=6)
	private BigDecimal rateThree;

	@FieldName(name="利息调整幅度_5年")
	@Column(name="RATE_FIVE",precision=22,scale=6)
	private BigDecimal rateFive;

	@FieldName(name="利息调整幅度_5年以上")
	@Column(name="RATE_ABOVEFIVE",precision=22,scale=6)
	private BigDecimal rateAbovefive;

	@FieldName(name="利息央行基准_六月")
	@Column(name="BASE_RATE_HALF",precision=22,scale=6)
	private BigDecimal baseRateHalf;

	@FieldName(name="利息央行基准_1年")
	@Column(name="BASE_RATE_ONE",precision=22,scale=6)
	private BigDecimal baseRateOne;

	@FieldName(name="利息央行基准_3年")
	@Column(name="BASE_RATE_THREE",precision=22,scale=6)
	private BigDecimal baseRateThree;

	@FieldName(name="利息央行基准_5年")
	@Column(name="BASE_RATE_FIVE",precision=22,scale=6)
	private BigDecimal baseRateFive;

	@FieldName(name="利息央行基准_5年以上")
	@Column(name="BASE_RATE_ABOVEFIVE",precision=22,scale=6)
	private BigDecimal baseRateAbovefive;
	
	@FieldName(name="是否调息结束")
	@Column(name="IS_CLOSE",length=2)
	private String isClose;
	
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
	
	public Set<FundAdjustInterestContractTemp> getFundAdjustInterestTemp() {
		return fundAdjustInterestTemp;
	}

	public void setFundAdjustInterestTemp(
			Set<FundAdjustInterestContractTemp> fundAdjustInterestTemp) {
		this.fundAdjustInterestTemp = fundAdjustInterestTemp;
	}

	@FieldName(name="调息记录ID")
	@OneToMany(mappedBy="adjustId", fetch=FetchType.LAZY)
	private Set<FundAdjustInterestContractTemp>  fundAdjustInterestTemp=new HashSet<FundAdjustInterestContractTemp>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getRateHalf() {
		return rateHalf;
	}

	public void setRateHalf(BigDecimal rateHalf) {
		this.rateHalf = rateHalf;
	}

	public BigDecimal getRateOne() {
		return rateOne;
	}

	public void setRateOne(BigDecimal rateOne) {
		this.rateOne = rateOne;
	}

	public BigDecimal getRateThree() {
		return rateThree;
	}

	public void setRateThree(BigDecimal rateThree) {
		this.rateThree = rateThree;
	}

	public BigDecimal getRateFive() {
		return rateFive;
	}

	public void setRateFive(BigDecimal rateFive) {
		this.rateFive = rateFive;
	}

	public BigDecimal getRateAbovefive() {
		return rateAbovefive;
	}

	public void setRateAbovefive(BigDecimal rateAbovefive) {
		this.rateAbovefive = rateAbovefive;
	}

	public BigDecimal getBaseRateHalf() {
		return baseRateHalf;
	}

	public void setBaseRateHalf(BigDecimal baseRateHalf) {
		this.baseRateHalf = baseRateHalf;
	}

	public BigDecimal getBaseRateOne() {
		return baseRateOne;
	}

	public void setBaseRateOne(BigDecimal baseRateOne) {
		this.baseRateOne = baseRateOne;
	}

	public BigDecimal getBaseRateThree() {
		return baseRateThree;
	}

	public void setBaseRateThree(BigDecimal baseRateThree) {
		this.baseRateThree = baseRateThree;
	}

	public BigDecimal getBaseRateFive() {
		return baseRateFive;
	}

	public void setBaseRateFive(BigDecimal baseRateFive) {
		this.baseRateFive = baseRateFive;
	}

	public BigDecimal getBaseRateAbovefive() {
		return baseRateAbovefive;
	}

	public void setBaseRateAbovefive(BigDecimal baseRateAbovefive) {
		this.baseRateAbovefive = baseRateAbovefive;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
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
