package by.training.finalproject.bean;

import java.io.Serializable;

public enum ClothesSex implements Serializable {
    MAN(0), WOMAN(1), UNISEX(2), CHILD(3);
    private final Integer value;

    ClothesSex(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ClothesSex getByCode(Integer type) {
        for (ClothesSex g : ClothesSex.values()) {
            if (g.value.equals(type)) {
                return g;
            }
        }
        return null;
    }
}
