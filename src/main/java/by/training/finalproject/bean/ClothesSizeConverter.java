package by.training.finalproject.bean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ClothesSizeConverter implements AttributeConverter<ClothesSize, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ClothesSize size) {
        if (size == null) {
            return null;
        }
        return size.getValue();
    }

    @Override
    public ClothesSize convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        return ClothesSize.getByCode(integer);
    }
}
