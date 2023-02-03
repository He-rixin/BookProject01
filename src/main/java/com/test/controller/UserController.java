package com.test.controller;

import com.test.model.User;
import com.test.service.IUserService;
import com.test.util.JsonResponseBody;
import com.test.util.JsonResponseStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author herixin
 * @create 2022-12-15 9:51
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping("/toLogin01")
    public String toLogin01() {
        return "login";
    }

    @RequestMapping("/doLogin01")
    public String login01(User user, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            session.setAttribute("message", "密码错误");
            return "login";
        } catch (UnknownAccountException e) {
            session.setAttribute("message", "账号不存在");
        }
        session.setAttribute("username", user.getUsername());
        return "bookcrud/bookMain";
    }

    @RequestMapping("/adminLogin")
    @ResponseBody
    public JsonResponseBody login(User user) {
        User u = iUserService.login(user);
        if (u == null) {
            return new JsonResponseBody(JsonResponseStatus.NULLACCOUNT.getCode(), JsonResponseStatus.NULLACCOUNT.getMsg());
        }
        JsonResponseBody jrb = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            jrb = new JsonResponseBody(JsonResponseStatus.PWDERROR.getCode(), JsonResponseStatus.PWDERROR.getMsg());
            return jrb;
            //            session.setAttribute("message", "密码错误");
        } catch (UnknownAccountException e) {
//            session.setAttribute("message", "账号不存在");
        }
        jrb = new JsonResponseBody(JsonResponseStatus.SUCCESS.getCode(), JsonResponseStatus.SUCCESS.getMsg());
        return jrb;
    }

    @RequestMapping("/toRegister01")
    public String toRegister() {
        return "userRegister01";
    }

    @RequestMapping("/userRegister01")
    public String userRegister01(User user, HttpSession session) {
        String s = iUserService.userRegister(user);
        if (!s.equals("注册成功")) {
            session.setAttribute("regMessage", s);
            return "userRegister01";
        }
        return "login";
    }

    @RequestMapping("/adminRegister")
    @ResponseBody
    public JsonResponseBody adminRegister(User user) {
        String s = iUserService.userRegister(user);
        if (s.equals("注册成功")) {
//            session.setAttribute("regMessage", s);
            return new JsonResponseBody(JsonResponseStatus.SUCCESS.getCode(), JsonResponseStatus.SUCCESS.getMsg());
        }
        return new JsonResponseBody(JsonResponseStatus.ERROR.getCode(), JsonResponseStatus.ERROR.getMsg());

    }
}
