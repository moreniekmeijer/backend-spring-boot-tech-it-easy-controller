package nl.moreniekmeijer.backendspringboottechiteasycontroller.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nl.moreniekmeijer.backendspringboottechiteasycontroller.models.ScreenType;

public class TelevisionInputDto {

    @NotBlank(message = "Type cannot be empty")
    public String type;

    @NotBlank(message = "Brand cannot be empty")
    public String brand;

    @NotBlank(message = "Name cannot be empty")
    public String name;

    @NotNull(message = "Price cannot be null")
    public Double price;
    public Double availableSize;
    public int refreshRate;

    @Enumerated(EnumType.STRING)
    public ScreenType screenType;

    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;
}
