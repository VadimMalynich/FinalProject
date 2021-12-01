package by.training.finalproject.bean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ClothesSexConverter implements AttributeConverter<ClothesSex, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ClothesSex sex) {
        if (sex == null) {
            return null;
        }
        return sex.getValue();
    }

    @Override
    public ClothesSex convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        return ClothesSex.getByCode(integer);
    }
}
