package cn.jsut.huoguo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url=request.getRequestURI();
        if (url.toLowerCase().indexOf("login")>0||url.toLowerCase().indexOf("register")>0||
                url.toLowerCase().indexOf("create")>0||url.toLowerCase().indexOf("notify")>0
                ||url.toLowerCase().indexOf("cc")>0||url.toLowerCase().indexOf("toindex")>0){
            return true;
        }
        HttpSession session=request.getSession();
        if (session.getAttribute("user")!=null){
            return true;
        }
        if (session.getAttribute("user")==null){
            response.sendRedirect("/toindex2");
        }
        //response.sendRedirect("/tologin");
        return false;
    }
}
