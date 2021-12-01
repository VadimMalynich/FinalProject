package by.training.finalproject.bean;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "ads_info", schema = "ads_db", catalog = "")
public class AdInfo extends Entity {

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))
    private User userInfo;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id_fk"))
    private ClothesType categoryInfo;

    @Embedded
    private Ad ad;

    @Transient
    private Integer likesCount;

    @Transient
    private Integer commentsCount;

//    @ManyToMany(mappedBy = "adInfoList", fetch = FetchType.LAZY)
//    private List<User> userList;

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
