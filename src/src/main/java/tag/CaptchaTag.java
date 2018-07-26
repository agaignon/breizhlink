package src.main.java.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;

public class CaptchaTag extends TagSupport {
    
    private static final long serialVersionUID = 1L;
    
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            
            // Adding BotDetect Captcha to the page
            SimpleCaptcha captcha = SimpleCaptcha.load(request, "captcha");
            pageContext.getOut().print(captcha.getHtml());
                
        } catch (Exception e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }
}
