package by.training.finalproject.service.tag;

import by.training.finalproject.service.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.MessageFormat;

@SuppressWarnings("serial")
public class CommentsStatistic extends TagSupport {
    private static final Logger userLogger = LogManager.getLogger(CommentsStatistic.class);
    private Integer commentsCount;
    private String bundle;

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    @Override
    public int doStartTag() throws JspException {
        String line = "<li><em class=\"icon-comment\"></em>";
        MessageManager manager = MessageManager.getByCode(bundle);
        if (commentsCount == 1) {
            line += MessageFormat.format(manager.getString("label.commentsSecond"), commentsCount);
        } else if (commentsCount >= 2 && commentsCount <= 4) {
            line += MessageFormat.format(manager.getString("label.commentsThird"), commentsCount);
        } else {
            line += MessageFormat.format(manager.getString("label.commentsFirst"), commentsCount);
        }
        line += "</li>";
        try {
            pageContext.getOut().write(line);
        } catch (IOException e) {
            userLogger.error(e);
        }
        return SKIP_BODY;
    }
}
