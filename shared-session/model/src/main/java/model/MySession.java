package model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Michael
 * @create 10/30/2020 2:31 PM
 */
public class MySession implements Serializable, HttpSession {


    private String id;
    private Map<String, Object> attrs;

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String,Object> attrs) {
        this.attrs = attrs;
    }

    public MySession(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getAttribute(String name) {
        return attrs.get(name);
    }

    @Override
    public Object getValue(String name) {
        return attrs.get(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(String name, Object value) {
        attrs.put(name, value);
    }

    @Override
    public void putValue(String name, Object value) {
        attrs.put(name, value);
    }


    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public void removeAttribute(String name) {

    }

    @Override
    public void removeValue(String name) {

    }

    @Override
    public void invalidate() {

    }

}
