package by.training.finalproject.bean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RegionConverter implements AttributeConverter<Region, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Region region) {
        if (region == null) {
            return null;
        }
        return region.getValue();
    }

    @Override
    public Region convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        return Region.getByCode(integer);
    }
}
