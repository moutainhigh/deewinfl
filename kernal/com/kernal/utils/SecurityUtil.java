
 /**
 * 项目名称：    系统名称
 * 包名：              com.kernal.utils
 * 文件名：         SecurityUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-9-上午12:07:31
 * Copyright：2013XX公司-版权所有
 **/

package com.kernal.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.business.entity.User;


 /**
 * 类名称：     SecurityUtil
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-9 上午12:07:31
 * 修改备注：
 * @version 1.0.0
 **/

public class SecurityUtil {
    public static User getPrincipal(){
    	//Object targetObj = (User)SecurityUtils.getSubject().getPrincipal();    
    	User loginedUser = null;
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(null != auth){
        	loginedUser = (User)auth.getPrincipal();
    	}
        return loginedUser;
    }
}
