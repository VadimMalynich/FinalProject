package by.training.finalproject.bean;

import java.io.Serializable;

public enum Region implements Serializable {
    BREST(1), VITEBSK(2), GOMEL(3), GRODNO(4), MINSK(5), MOGILEV(6);
    private Integer value;

    Region(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Region getByCode(Integer region) {
        for (Region g : Region.values()) {
            if (g.value.equals(region)) {
                return g;
            }
        }
        return null;
    }
}
