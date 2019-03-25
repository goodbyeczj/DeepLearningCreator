package com.rkb.controller;

import com.rkb.exception.UserDaoException;
import com.rkb.pojo.User;
import com.rkb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
@SessionAttributes("id")
public class UserController {
    //注入Service
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ModelAndView register(User user, ModelAndView modelAndView) {
        User user1 = userService.findUserByName(user.getName());
//        System.out.println("用户注册："+user.getName()+user.getPassword());
        if (user1==null&&userService.assertCore(user.getCore())) {
            userService.regist(user);
            modelAndView.addObject("re_msg", "注册成功");
            modelAndView.setViewName("redirect:/submit");
        }
        else if (userService.findUserByName(user.getName())!=null){
            modelAndView.addObject("msg", user.getName()+"已被注册!");
            modelAndView.setViewName("forward:/register");
        }
        else {
            modelAndView.addObject("msg", "注册失败,请输入正确的邀请码");
            modelAndView.setViewName("forward:/register");
        }
        //注册成功后跳转success.jsp页面
        return modelAndView;
    }

    @RequestMapping("/login")
    public String login(String name, String password, Model model) {
//        System.out.println("用户登录："+name+password);

		/*Map<String, String> map=new LinkedHashMap<String,String>();

		map.put("name", user.getName());
		map.put("password", user.getPassword());*/

        User user = userService.login(name, password);
        if (user != null) {
            System.out.println(user.getId());
            model.addAttribute("id", user.getId());
            return "forward:/model/manage";
        }
//            modelAndView.getModel().put("id",user.getId());
        else {
            model.addAttribute("msg", "账号或密码错误,请重新输入!");
            return "forward:/submit";
        }
    }

    //    注销方法
    @RequestMapping("/outLogin")
    public ModelAndView outLogin(ModelAndView modelAndView,HttpSession session) {
        session.invalidate();
        modelAndView.setViewName("redirect:/submit.jsp");
        return modelAndView;
    }
    @RequestMapping("/forgotPw")
    public ModelAndView forgotPw(ModelAndView modelAndView,User user){
        if (userService.forgotPw(user)){
            userService.forgotPw(user);
            modelAndView.setViewName("redirect:/submit.jsp");
            modelAndView.addObject("msg","修改成功");
            return modelAndView;
        }
        else{
            modelAndView.setViewName("forward:/forgot.jsp");
            modelAndView.addObject("msg","账号或邀请码错误，请重试");
            return modelAndView;
        }
    }
    @ExceptionHandler(UserDaoException.class)
    public ModelAndView HandleUserDaoException(ModelAndView modelAndView,UserDaoException e){
        modelAndView.setViewName("failure");
        return modelAndView;
    }
    @RequestMapping("/test")
    public ModelAndView test(ModelAndView modelAndView){
        System.out.println(123);
        modelAndView.setViewName("notFound");
        return modelAndView;
    }
}

