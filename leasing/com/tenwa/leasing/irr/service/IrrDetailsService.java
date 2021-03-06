package com.tenwa.leasing.irr.service;

import java.util.List;

import com.tenwa.leasing.bean.CashDetailsBean;
import com.tenwa.leasing.bean.FundRentPlanBean;
import com.tenwa.leasing.bean.TabCalBean;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc TODO (todo-list 现金流处理业务接口)
 */
public interface IrrDetailsService {

	/**
	 * 
	 * TODO (todo-list todo-list 通过配置信息读取现金流明细)
	 * 
	 * @param tcb
	 *            表信息
	 * @param occu_type
	 *            合同，会计
	 * @return
	 * @throws Exception
	 */
	public List<CashDetailsBean> getCashDetailByCfg(TabCalBean tcb,
			String occu_type) throws Exception;


	/**
	 * 
	 * TODO (todo-list todo-list 租金计划读取现金流明细)
	 * 
	 * @param frpb
	 * @param cdbList
	 * @return
	 * @throws Exception
	 */
	public List<CashDetailsBean> getCashDetailByRentPlan(FundRentPlanBean frpb,
			List<CashDetailsBean> cdbList,int startList) throws Exception;

}
