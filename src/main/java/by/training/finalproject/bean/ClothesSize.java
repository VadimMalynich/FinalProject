package by.training.finalproject.bean;

import java.io.Serializable;

public enum ClothesSize implements Serializable {
    XS(1), S(2), M(3), L(4), XL(5), XXL(6), XXXL(7);
    private Integer value;

    ClothesSize(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ClothesSize getByCode(Integer size) {
        for (ClothesSize g : ClothesSize.values()) {
            if (g.value.equals(size)) {
                return g;
            }
        }
        return null;
    }
}
