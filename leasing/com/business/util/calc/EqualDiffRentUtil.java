package com.business.util.calc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.business.model.ConditionBean;
import com.business.model.RentInfoBox;
import com.business.util.EqDiffRentCalc;
import com.business.util.RentCaleCommonTools;

public class EqualDiffRentUtil {

	/**
	 * 
	 * @param conditionBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static RentInfoBox getRentInfoBox(ConditionBean conditionBean) {
		RentInfoBox rentInfoBox = new RentInfoBox();
		String contract_id = conditionBean.getContract_id();
		String lease_interval = conditionBean.getIncome_number_year();// 租金间隔
		String assets_value = conditionBean.getAssets_value();// 资产余值
		String caution_money = conditionBean.getCaution_money();// 保证金
		String Other_expenditure =
		 conditionBean.getOther_expenditure();//其它支出
//		String Other_expenditure = conditionBean.getNominalprice();// 残值收入
		String rentScale = "2";// 租金精确度
		String type = conditionBean.getPeriod_type();//
		String firstMoney = String
				.valueOf("-" + conditionBean.getActual_fund());
		String manu_caution_money = conditionBean.getSupplier_caution_money();
		// 现金流部分调用
		// 装租金测算条件 9个
		EqDiffRentCalc calc = new EqDiffRentCalc();
		calc.setYear_rate(conditionBean.getYear_rate()); // 年利率
		calc.setIncome_number(conditionBean.getIncome_number());// 期数
		calc.setLease_money(conditionBean.getLease_money());// 租赁本金 （租赁本金 = 设备金额
		// - 首付款）
		calc.setFuture_money(conditionBean.getAssets_value());// 留购价
		calc.setPeriod_type(conditionBean.getPeriod_type());// 1,期初 0,期未的值
		calc.setIrr_type("2");// 1,为按月份的处; 2,为按租金间隔的处理 暂时是2
		calc.setScale("2");// irr的小数位数 暂时就是8位
		calc.setLease_interval(conditionBean.getIncome_number_year());// 租金间隔
		// (付租方式)
		calc.setPlan_date(conditionBean.getStart_date());// 每月偿付日 替换 起租日的具体日期
		calc.setRatio_param(conditionBean.getRatio_param());// 本金公比
		if ("".equals(contract_id) || contract_id == null) {
			calc.setContract_id(conditionBean.getProj_id());// 计算具体项目现金流的KEY
		} else {
			calc.setContract_id(conditionBean.getContract_id());// 计算具体项目现金流的KEY
		}
		calc.setRentScale("4");// 圆整到

		List l_plan_date = new ArrayList();
		List l_rent = new ArrayList();
		List l_corpus = new ArrayList();
		List l_interest = new ArrayList();
		List l_corpus_overage = new ArrayList();
		Hashtable ht_plan = new Hashtable();
		try {

			ht_plan = calc.getRentPlan(conditionBean, rentScale);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 取值
		l_plan_date = (List) ht_plan.get("plan_date");// 租金偿还日期
		l_rent = (List) ht_plan.get("rent");// 租金
		l_corpus = (List) ht_plan.get("corpus");// 本金
		l_interest = (List) ht_plan.get("interest");// 利息
		l_corpus_overage = (List) ht_plan.get("corpus_overage");// 剩余本金

		for (int i = 0; i < l_rent.size(); i++) {
			System.out.println(l_plan_date.get(i) + "rent=" + l_rent.get(i)
					+ "  corpus= " + l_corpus.get(i) + "  " + "inter="
					+ l_interest.get(i) + "   cor_overge="
					+ l_corpus_overage.get(i));

		}

		/*
		 * ///////////////// 现金流及IRR测算
		 */// //////////////
		RentCaleCommonTools calcTools = new RentCaleCommonTools();
		List l_RentDetails = new ArrayList();
		String caution_oper_way = conditionBean.getCaution_oper_way();//保证金返回方式
		String caution_sfjx = conditionBean.getCaution_sfjx();//保证金是否计息
		String lease_term = conditionBean.getLease_term();
		String irr = calcTools.getIrr(firstMoney, l_rent, caution_money, assets_value, 
				Other_expenditure, lease_interval, type,caution_oper_way,caution_sfjx,manu_caution_money);
		// 得到保证金抵扣租金List rent_list 租金List,caut_money 保证金
		 l_RentDetails = calcTools.getRentDetails(firstMoney,l_rent,l_plan_date,conditionBean);
		 
		// RentInshareCal rentInshareCal = new RentInshareCal();
//		 List l_cash_rent  = calcTools.getRentCautNew(firstMoney, rent_list, caution_money,
//				 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx);
		 //2012-08-27 新增内容，融资收益分摊表
		// List l_cash_rent = rentInshareCal.getInshareCashRent(lease_money,handling_charge, l_rent, caution_money,
		//		 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx,
		//		 lease_term);
		// String inshareIrr = rentInshareCal.getInshareIrr(lease_interval, l_cash_rent);
		// List<RentInshareBean> l_rentInshareBean = new ArrayList<RentInshareBean>();
		// l_rentInshareBean = rentInshareCal.RentInshare(inshareIrr, conditionBean, l_cash_rent, l_plan_date);
		
		/*
		 * 
		 * RentInfoBox 封装测算List
		 * 
		 */
		rentInfoBox.setPlanDate(l_plan_date);
		rentInfoBox.setRent(l_rent);
		rentInfoBox.setCorpus(l_corpus);
		rentInfoBox.setInterest(l_interest);
		rentInfoBox.setCorpusOverge(l_corpus_overage);
		rentInfoBox.setRentDetails(l_RentDetails);
		rentInfoBox.setIrr(irr);//irr未乘以100
		//rentInfoBox.setL_RentInshare(l_rentInshareBean);

		return rentInfoBox;

	}
}	