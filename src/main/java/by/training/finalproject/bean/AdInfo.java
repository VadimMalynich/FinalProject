package by.training.finalproject.bean;

import java.sql.Date;
import java.util.Objects;

public class AdInfo extends Entity {
    private User userInfo;
    private ClothesType categoryInfo;
    private Ad ad;
    private Integer likesCount;
    private Integer commentsCount;

    public AdInfo() {
    }

    public AdInfo(User userInfo, Integer categoryInfo, Ad ad) {
        this.userInfo = userInfo;
        this.categoryInfo = new ClothesType(categoryInfo);
        this.ad = ad;
    }

    public AdInfo(Integer id, User userInfo, Integer categoryInfo, Ad ad) {
        super(id);
        this.userInfo = userInfo;
        this.categoryInfo = new ClothesType(categoryInfo);
        this.ad = ad;
    }

    public AdInfo(Integer id, User userInfo, ClothesType categoryInfo, Ad ad) {
        super(id);
        this.userInfo = userInfo;
        this.categoryInfo = categoryInfo;
        this.ad = ad;
    }

    public AdInfo(Integer id, Integer userId, Integer categoryId, Integer commentsCount, Integer likesCount, Ad ad) {
        super(id);
        this.userInfo = new User(userId);
        this.categoryInfo = new ClothesType(categoryId);
        this.ad = ad;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

    public AdInfo(Integer id, User userInfo, ClothesType categoryInfo, Ad ad, Integer likesCount, Integer commentsCount) {
        super(id);
        this.userInfo = userInfo;
        this.categoryInfo = categoryInfo;
        this.ad = ad;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public ClothesType getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(ClothesType categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setDate(Date date) {
        ad.setDate(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdInfo adInfo = (AdInfo) o;
        return userInfo.equals(adInfo.userInfo) && Objects.equals(categoryInfo, adInfo.categoryInfo) && ad.equals(adInfo.ad) && likesCount.equals(adInfo.likesCount) && commentsCount.equals(adInfo.commentsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userInfo, categoryInfo, ad, likesCount, commentsCount);
    }

    @Override
    public String toString() {
        return "AdInfo{" +
                "id=" + getId() +
                ", user=" + userInfo +
                ", category=" + categoryInfo +
                ", ad=" + ad +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                '}';
    }
}
