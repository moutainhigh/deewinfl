package com.business.entity.contract.equip.gps;

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
 * 
 * @author 孙传良
 * @date 2013-8-6下午02:22:43
 * @info GPS接口信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "GPS接口信息(德银)")
@Table(name="GPS_INTERFACES_INFO")
public class GPSInterfacesInfo {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="用户名称")
	@Column(name="GPS_USER",length=100)
	private String gpsUser;
	
	@FieldName(name="车牌号")
	@Column(name="CAR_NO",length=100)
	private String carNo;
	
	@FieldName(name="VIN码（车架号")
	@Column(name="CHASSIS_NUM",length=100)
	private String chassisNum;
	
	@FieldName(name="终端号（SM卡号")
	@Column(name="SIM_NO",length=100)
	private String simNo;
	
	@FieldName(name="车辆状态")
	@Column(name="CAR_STATUS",length=10)
	private String carStatus;

	@FieldName(name="地理位置")
	@Column(name="CAR_ADDR",length=100)
	private String carAddr;
	
	@FieldName(name="车速（KM/h）")
	@Column(name="CAR_SPEED",length=100)
	private String carSpeed;
	
	@FieldName(name="转速（转")
	@Column(name="CAR_PINWHEEL",length=100)
	private String carPinwheel;
	
	@FieldName(name="里程（km")
	@Column(name="CAR_MILEAGE",length=100)
	private String carMileage;
	
	@FieldName(name="定位时间")
	@Column(name="CAR_DATE",length=20)
	private String carDate;
	
	@FieldName(name="方向")
	@Column(name="CAR_DIRECTION",length=100)
	private String carDirection;
	
	@FieldName(name="开关量")
	@Column(name="OFF_OR_ON",length=100)
	private String offOrOn;

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

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
	@FieldName(name="上传文件名")
	@JoinColumn(name="UPLOAD_ID")
	private BaseFile upLoadId ;
	
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

	public String getGpsUser() {
		return gpsUser;
	}

	public void setGpsUser(String gpsUser) {
		this.gpsUser = gpsUser;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getChassisNum() {
		return chassisNum;
	}

	public void setChassisNum(String chassisNum) {
		this.chassisNum = chassisNum;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public String getCarAddr() {
		return carAddr;
	}

	public void setCarAddr(String carAddr) {
		this.carAddr = carAddr;
	}

	public String getCarSpeed() {
		return carSpeed;
	}

	public void setCarSpeed(String carSpeed) {
		this.carSpeed = carSpeed;
	}

	public String getCarPinwheel() {
		return carPinwheel;
	}

	public void setCarPinwheel(String carPinwheel) {
		this.carPinwheel = carPinwheel;
	}

	public String getCarMileage() {
		return carMileage;
	}

	public void setCarMileage(String carMileage) {
		this.carMileage = carMileage;
	}

	public String getCarDate() {
		return carDate;
	}

	public void setCarDate(String carDate) {
		this.carDate = carDate;
	}

	public String getCarDirection() {
		return carDirection;
	}

	public void setCarDirection(String carDirection) {
		this.carDirection = carDirection;
	}

	public String getOffOrOn() {
		return offOrOn;
	}

	public void setOffOrOn(String offOrOn) {
		this.offOrOn = offOrOn;
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
