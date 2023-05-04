package co.edu.umanizales.tads.controller.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
@Data

public class PetDTO {
    private String identification;
    private String nameOwner;

    @NotNull
    @NotEmpty
    private String name;

    private char sex;
    private String species;

    @NotEmpty
    @Min(1) @Max(15)
    private byte age;
    private String code;


}
