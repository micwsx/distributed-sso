package model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Michael
 * @create 10/30/2020 2:57 PM
 */
public final class CookieConstant {
    public static final String COOKIE_NAME_SESSION = "psession";

    // 从cookie中获取jessionid值
    public static String getRequestedSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie>  cookie = Arrays.stream(cookies).filter(c -> c.getName().equalsIgnoreCase(COOKIE_NAME_SESSION)).findFirst();
            if (cookie.isPresent()){
                return cookie.get().getValue();
            }
        }
        return "";
    }

    // 将生成的session UUID存放到redis中。
    public static void setGlobalSessionId(HttpServletRequest request,
                                          HttpServletResponse response){
        String id = request.getSession().getId();
        Cookie cookie=new Cookie(COOKIE_NAME_SESSION, id);
        cookie.setDomain("group.com");//顶级域名
        cookie.setHttpOnly(true);
        cookie.setPath(request.getContextPath()+"/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
    }
}
