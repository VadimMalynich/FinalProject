package by.training.finalproject.bean;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Table;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "cities", schema = "ads_db", catalog = "")
public class City extends Entity {

    @Convert(converter = RegionConverter.class)
    @Column(name = "region", nullable = false)
    private Region region;

    @Column(name = "city", nullable = false, length = 25)
    private String name;

    public City() {
    }

    public City(Integer id) {
        super(id);
    }

    public City(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public City(Integer id, Region region, String name) {
        super(id);
        this.region = region;
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        City city = (City) o;
        return region == city.region && name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), region, name);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + getId() +
                ", region=" + region +
                ", name='" + name + '\'' +
                '}';
    }
}
