package co.edu.umanizales.tads.controller.dto;

import lombok.Data;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.*;

@Data
public class KidDTO {

    @NotBlank(message = "La identificación no puede estar vaciá ")
    private String identification;

    @NotBlank(message = "El nombre no puede estar vació")
    @Size(min = 3, max = 30, message = "El nombre debe tener de 3 a 30 caracteres")
    private String name;
    @Min(value = 1, message = "La edad minima es 1")
    @Max(value = 15, message = "La edad debe ser menor o igual a 15")
    private byte age;

    @Pattern(regexp = "[MF]", message = "El género debe ser M o F")
    @Size(min = 1, max = 1, message = "El género debe ser solo una letra")
    private String gender;
    @Size(min = 3, max = 8, message = "El código de la locación debe de tener 3 a 8 caracteres ")
    private String code;

}

