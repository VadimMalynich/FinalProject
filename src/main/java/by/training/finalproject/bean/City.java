package by.training.finalproject.bean;

import java.util.Objects;

public class City extends Entity {
    private Region region;
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
