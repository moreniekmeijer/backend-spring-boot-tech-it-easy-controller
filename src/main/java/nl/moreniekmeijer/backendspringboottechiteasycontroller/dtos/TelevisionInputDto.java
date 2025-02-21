package nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.ScreenType;

public class TelevisionInputDto {

    @NotBlank(message = "Type cannot be empty")
    public String type;

    @NotBlank(message = "Brand cannot be empty")
    public String brand;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 20, message = "Name cannot be longer than 20 characters")
    public String name;

    @NotNull(message = "Price cannot be null")
    public Double price;
    public Double availableSize;
    public int refreshRate;

    @Enumerated(EnumType.STRING)
    public ScreenType screenType;

    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;
}
