package nl.moreniekmeijer.backendspringboottechiteasycontroller.mappers;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos.TelevisionInputDto;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos.TelevisionResponseDto;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.ScreenType;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.Television;

public class TelevisionMapper {
    public static TelevisionResponseDto toResponseDto(Television television) {
        TelevisionResponseDto televisionResponseDto = new TelevisionResponseDto();
        televisionResponseDto.setId(television.getId());
        televisionResponseDto.setType(television.getType());
        televisionResponseDto.setBrand(television.getBrand());
        televisionResponseDto.setName(television.getName());
        televisionResponseDto.setPrice(television.getPrice());
        return televisionResponseDto;
    }

    public static Television toEntity(TelevisionInputDto televisionInputDto) {
        Television television = new Television();
        television.setType(televisionInputDto.type);
        television.setBrand(televisionInputDto.brand);
        television.setName(televisionInputDto.name);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSize(televisionInputDto.availableSize);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenType(televisionInputDto.screenType);
        // and more...
        return television;
    }

}
