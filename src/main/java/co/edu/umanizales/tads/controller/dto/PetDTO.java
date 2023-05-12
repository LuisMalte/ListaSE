package co.edu.umanizales.tads.controller.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
@Data

public class PetDTO {
    @NotBlank(message = "La identificación no puede estar vaciá ")
    private String identification;

    @NotBlank(message = "El nombre del dueño  no puede estar vació")
    @Size(max = 30, message = "El nombre del dueño debe tener de 3 a 30 caracteres")
    private String nameOwner;

    @NotBlank(message = "El nombre no puede estar vació")
    @Size(max = 30, message = "El nombre debe tener de 3 a 30 caracteres")
    private String name;

    @Pattern(regexp = "[MF]", message = "El sexo debe ser M o F")
    @Size(min = 1, max = 1, message = "El sexo debe ser solo una letra")
    private String sex;

    private String species;


    @Min(value = 1, message = "La edad minima es 1")
    @Max(value = 15, message = "La edad debe ser menor o igual a 15")
    private byte age;
    @Size(min = 3, max = 8, message = "El código de la locación debe de tener 3 a 8 caracteres ")
    private String code;


}
