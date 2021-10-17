package by.training.finalproject.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Comments extends Entity {
    private Integer adId;
    private User user;
    private String commentText;
    private Timestamp commentDate;

    public Comments() {
    }

    public Comments(Integer adId, Integer userId, String commentText) {
        this.adId = adId;
        this.user = new User(userId);
        this.commentText = commentText;
    }

    public Comments(Integer adId, Integer userId, String commentText, Timestamp commentDate) {
        this.adId = adId;
        this.user = new User(userId);
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public Comments(Integer id, Integer adId, Integer userId, String commentText, Timestamp commentDate) {
        super(id);
        this.adId = adId;
        this.user = new User(userId);
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comments comments = (Comments) o;
        return adId.equals(comments.adId) && user.equals(comments.user) && commentText.equals(comments.commentText) && commentDate.equals(comments.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), adId, user, commentText, commentDate);
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + getId() +
                ", adID=" + adId +
                ", userId=" + user.getId() +
                ", commentText='" + commentText + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}
