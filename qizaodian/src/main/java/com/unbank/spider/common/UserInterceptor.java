package com.unbank.spider.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {

	/**
	 * 当前请求完成操作 完全处理完请求后被调用,可用于清理资源等 Title: afterCompletion Description:()
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	/**
	 * 请求之前结果返回true时请求 在业务处理器处理请求执行完成后,生成视图之前执行 Title: postHandle Description:()
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	/**
	 * 请求之前操作 在业务处理器处理请求之前被调用 Title: preHandle Description:()
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//LoginUser user = (LoginUser) request.getSession().getAttribute("user");
		StringBuffer url = request.getRequestURL();
		System.out.println(request.getContextPath());
		// 实现灵活配置处理业务请求跳转过滤;请求路径规范统一，方便处理:以下是业务不需要校验
		int dl = url.indexOf("http://qizaodian");
		if(dl>-1){
			response.setStatus(301);
			response.setHeader("Location","http://qizaodian.com");
			//---分割线---
			response.sendRedirect("http://www.qizaodian.com");
			return false;
		}

		return true;
	}
}