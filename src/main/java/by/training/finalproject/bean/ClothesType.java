package by.training.finalproject.bean;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "category", schema = "ads_db", catalog = "")
public class ClothesType extends Entity {

    @Column(name = "name", nullable = false, length = 45)
    private String category;

    @Transient
    private Integer count;

    public ClothesType() {
    }

    public ClothesType(Integer id) {
        super(id);
    }

    public ClothesType(String category) {
        this.category = category;
    }

    public ClothesType(Integer id, String category) {
        super(id);
        this.category = category;
    }

    public ClothesType(Integer id, String category, Integer count) {
        super(id);
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClothesType that = (ClothesType) o;
        return Objects.equals(category, that.category) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, count);
    }

    @Override
    public String toString() {
        return "ClothesType{" +
                "id=" + getId() +
                ", category='" + category + '\'' +
                ", count=" + count +
                '}';
    }
}
