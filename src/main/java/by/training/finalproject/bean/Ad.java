package by.training.finalproject.bean;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Embeddable
public class Ad implements Serializable {
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "topic", nullable = false, length = 50)
    private String topic;

    @Column(name = "material", nullable = false, length = 50)
    private String material;

    @Convert(converter = ClothesSizeConverter.class)
    @Column(name = "size", nullable = false)
    private ClothesSize size;

    @Convert(converter = ClothesSexConverter.class)
    @Column(name = "sex", nullable = false)
    private ClothesSex sex;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "picture", nullable = false, length = 260)
    private String picture;

    public Ad() {
    }

    public Ad(Date date, String topic, String description, String picture) {
        this.date = date;
        this.topic = topic;
        this.description = description;
        this.picture = picture;
    }

    public Ad(String topic, String material, ClothesSize size, ClothesSex sex, String description, String picture) {
        this.topic = topic;
        this.material = material;
        this.size = size;
        this.sex = sex;
        this.description = description;
        this.picture = picture;
    }

    public Ad(String topic, String material, ClothesSize size, ClothesSex sex, String description) {
        this.topic = topic;
        this.material = material;
        this.size = size;
        this.sex = sex;
        this.description = description;
    }

    public Ad(Date date, String topic, String material, ClothesSize size, ClothesSex sex, String description, String picture) {
        this.date = date;
        this.topic = topic;
        this.material = material;
        this.size = size;
        this.sex = sex;
        this.description = description;
        this.picture = picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ClothesSize getSize() {
        return size;
    }

    public void setSize(ClothesSize size) {
        this.size = size;
    }

    public ClothesSex getSex() {
        return sex;
    }

    public void setSex(ClothesSex sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return date.equals(ad.date) && topic.equals(ad.topic) && Objects.equals(material, ad.material) && size == ad.size && sex == ad.sex && description.equals(ad.description) && picture.equals(ad.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, topic, material, size, sex, description, picture);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "date=" + date +
                ", topic='" + topic + '\'' +
                ", material='" + material + '\'' +
                ", size=" + size +
                ", sex=" + sex +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
