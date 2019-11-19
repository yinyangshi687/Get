package com.oracle.hrb.err.incentepert;

import com.oracle.hrb.err.UserEum.UserEnum;
import com.oracle.hrb.err.bean.User;
import com.oracle.hrb.err.dao.UserDao;
import com.oracle.hrb.err.intercepetr.Result;
import com.oracle.hrb.err.serverlt.UserServiset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/hehe")
public class Userincetepert {
    @Autowired
    private UserDao dao;
    @Autowired
    private UserServiset serviset;
  @RequestMapping("/login.do")
    public Object login(User user,HttpSession session){
     Result result=new Result();
    UserEnum anEnum = serviset.one(user);
     switch (anEnum){
      case USERNAME_NULL:
          result.setSuccess(false);
          result.setMsg("用户名为空");
          break;
      case PASSWORD_NULL:
          result.setSuccess(false);
          result.setMsg("密码为空");
          break;
      case USERWORD_PASSWORD_EX:
          result.setSuccess(false);
          result.setMsg("用户名或密码错误");
          break;
      case OK:
          User u=dao.findByName(user.getName());
         result.setSuccess(true);
         result.setValue(u);
        break;
  }
      session.setAttribute("user",result.getValue());
    return result;
}
    @RequestMapping("/add.do")
    public Object oldin(User user) {
        Result result = new Result();
        UserEnum anEnum = serviset.addzhuce(user);
        switch (anEnum) {
            case USERNAME_NULL:
                result.setSuccess(false);
                result.setMsg("用户名为空");
                break;
            case PASSWORD_NULL:
                result.setSuccess(false);
                result.setMsg("密码为空");
                break;
            case USERNAMEOO_ER:
                result.setSuccess(false);
                result.setMsg("昵称不能为空");
            case USERWORD_REPEAT:
                result.setSuccess(false);
                result.setMsg("用户名以重复");
                break;
            case OK:
                result.setSuccess(true);
                break;
        }
        System.out.println(result.getSuccess());
        return result;
    }
    @RequestMapping("/update.do")
    public Object updateUser(User user,HttpSession session) {
        Result result = new Result();
        System.out.println(user);
        UserEnum anEnum = serviset.updatezhuce(user);
        switch (anEnum) {
            case OK:
                result.setSuccess(true);
                break;
        }
        result.setValue(dao.findById(user.getId()));
        session.setAttribute("user",result.getValue());
        return result;
    }
    @RequestMapping("/delete.do")
    public Object deleteUser(User user,HttpSession session) {
        Result result = new Result();
        session.invalidate();
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/check.do")
    public Object checkUser(String name) {
        System.out.println(1);
        return dao.findByName(name)==null;
    }
}
