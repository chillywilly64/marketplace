package interceptor;

import dto.ItemDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor{
    
    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        System.out.println("sss");
        if(checkAccess(hsr)) {
            return true;
        } else {
            hsr1.sendRedirect("/");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean checkAccess(HttpServletRequest request){
        if(request.getSession().getAttribute("user") == null) {
            return false;
        } else if(request.getAttribute("itemForm") == null) {
            return true;
        }else if(((ItemDTO) request.getAttribute("itemForm")).isSold()){
            return false;
        } else if(request.getSession().getAttribute("user")
                        .equals(((ItemDTO) request.getAttribute("itemForm")).getLogin())){
            return true;
        } else {
            return false;
        }
    }
}
