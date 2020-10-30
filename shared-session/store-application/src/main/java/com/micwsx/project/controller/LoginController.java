package com.micwsx.project.controller;

import com.micwsx.project.filter.AuthenticationFilter;
import model.CookieConstant;
import model.MyHttpServletRequest;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import view.model.LoginViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Michael
 * @create 10/30/2020 1:55 PM
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(MyHttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/index");
        LoginViewModel loginViewModel = new LoginViewModel("michael", "michael");
        String returnUrl = request.getParameter("returnUrl");
        loginViewModel.setReturnUrl(returnUrl);
        modelAndView.addObject(loginViewModel);
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView login(LoginViewModel loginViewModel, MyHttpServletRequest request, HttpServletResponse response) throws IOException {
        if (loginViewModel != null) {
            if (loginViewModel.getPassword().equals(loginViewModel.getUsername())) {
                // 登录成功,保持本地session中
                request.getSession().setAttribute(AuthenticationFilter.USER, new User(loginViewModel.getUsername(), loginViewModel.getPassword()));
                // 将uuid保存到顶级域名下
                CookieConstant.setGlobalSessionId(request,response);
                if (StringUtils.isEmpty(loginViewModel.getReturnUrl())) {
                    // 没有，则指定主页面
                    response.sendRedirect("/store/home");
                } else {
                    // 有，则指定请求页面
                    response.sendRedirect(loginViewModel.getReturnUrl());
                }
                // 开始保存到session到redis中。
                request.commitSession();
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/index");
        loginViewModel.setMessage("登录失败，用户名或密码错误");
        return modelAndView;
    }
}
