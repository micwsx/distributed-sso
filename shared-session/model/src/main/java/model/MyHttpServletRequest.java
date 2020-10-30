package model;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Michael
 * @create 10/30/2020 2:35 PM
 */
public class MyHttpServletRequest extends HttpServletRequestWrapper {


    private MySession mySession;
    private RedisTemplate redisTemplate;

    public MyHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    public MyHttpServletRequest(HttpServletRequest request, RedisTemplate redisTemplate) {
        super(request);
        this.redisTemplate = redisTemplate;
    }

    public MySession getSession() {
        if (mySession != null) {
            return mySession;
        }
        // 从cookie中获取jsessionid
        Map<String,Object> attrs;
        String jsessionId = CookieConstant.getRequestedSessionId(this);
        if (StringUtils.isEmpty(jsessionId)) {
            // 创建session
            jsessionId=UUID.randomUUID().toString().replace("-", "");
            attrs=new HashMap<>();
        }else{
            // 从redis中获取属性
            attrs = redisTemplate.opsForHash().entries(jsessionId);
        }
        mySession=new MySession(jsessionId);
        mySession.setAttrs(attrs);
        return mySession;
    }

    // 保存session到redis中
    public void commitSession(){
        if (this.mySession!=null){
            redisTemplate.opsForHash().putAll(this.mySession.getId(), this.mySession.getAttrs());
        }
    }

}
