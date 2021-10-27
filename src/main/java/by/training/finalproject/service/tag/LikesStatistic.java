package by.training.finalproject.service.tag;

import by.training.finalproject.service.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.MessageFormat;

@SuppressWarnings("serial")
public class LikesStatistic extends TagSupport {
    private static final Logger userLogger = LogManager.getLogger(LikesStatistic.class);
    private Integer likesCount;
    private String bundle;

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    @Override
    public int doStartTag() throws JspException {
        String line = "<li><em class=\"icon-heart-empty\"></em>";
        MessageManager manager = MessageManager.getByCode(bundle);
        if (likesCount == 1) {
            line += MessageFormat.format(manager.getString("label.likesSecond"), likesCount);
        } else if (likesCount >= 2 && likesCount <= 4) {
            line += MessageFormat.format(manager.getString("label.likesThird"), likesCount);
        } else {
            line += MessageFormat.format(manager.getString("label.likesFirst"), likesCount);
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
