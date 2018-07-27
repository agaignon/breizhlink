package src.main.java.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import src.main.java.model.AuthenticatedUrl;
import src.main.java.util.Pagination;

public class PaginationTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private Pagination<AuthenticatedUrl> pagination;

    public int doStartTag() throws JspException {

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        String firstUrl = request.getContextPath() + "/auth/urls?page=0";
        String lastUrl = request.getContextPath() + "/auth/urls?page=" + pagination.getLastIndex();
        String prevUrl = request.getContextPath() + "/auth/urls?page=" + (pagination.getCurrentIndex() - 1);
        String nextUrl = request.getContextPath() + "/auth/urls?page=" + (pagination.getCurrentIndex() + 1);

        try {

            if (pagination.getTotalPages() > 1) {

                pageContext.getOut().print("<ul class=\"pagination\">");

                if (pagination.getCurrentIndex() == 0) {
                    pageContext.getOut().print("<li class=\"disabled\"><a href=\"#\">&lt;&lt;</a></li>");
                    pageContext.getOut().print("<li class=\"disabled\"><a href=\"#\">&lt;</a></li>");
                } else {
                    pageContext.getOut().print("<li><a href=\"" + firstUrl + "\">&lt;&lt;</a></li>");
                    pageContext.getOut().print("<li><a href=\"" + prevUrl + "\">&lt;</a></li>");
                }

                for (int i = pagination.getBeginIndex(); i <= pagination.getEndIndex(); i++) {

                    String pageUrl = request.getContextPath() + "/auth/urls?page=" + i;

                    if (i == pagination.getCurrentIndex()) {
                        pageContext.getOut().print("<li class=\"active\"><a href=\"" + pageUrl + "\">" + (i + 1) + "</a></li>");
                    } else {
                        pageContext.getOut().print("<li><a href=\"" + pageUrl + "\">" + (i + 1) + "</a></li>");
                    }
                }

                if (pagination.getCurrentIndex() == pagination.getLastIndex()) {
                    pageContext.getOut().print("<li class=\"disabled\"><a href=\"#\">&gt;</a></li>");
                    pageContext.getOut().print("<li class=\"disabled\"><a href=\"#\">&gt;&gt;</a></li>");
                } else {
                    pageContext.getOut().print("<li><a href=\"" + nextUrl + "\">&gt;</a></li>");
                    pageContext.getOut().print("<li><a href=\"" + lastUrl + "\">&gt;&gt;</a></li>");
                }

                pageContext.getOut().print("</ul>");
            }

        } catch (Exception e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }

    public void setPagination(Pagination<AuthenticatedUrl> pagination) {
        this.pagination = pagination;
    }
}
