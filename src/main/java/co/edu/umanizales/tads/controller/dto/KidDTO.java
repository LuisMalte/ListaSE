package co.edu.umanizales.tads.controller.dto;

import lombok.Data;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.*;

@Data
public class KidDTO {

    @NotBlank(message = "Identification is mandatory")
    private String identification;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name length cannot exceed 50 characters")
    private String name;

    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 15, message = "Age must be less than or equal to 15")
    private byte age;

    @Pattern(regexp = "[MF]", message = "El género debe ser M o F")
    @Size(min = 1, max = 1, message = "El género debe ser solo una letra")
    private String gender;

    private String code;

}

