package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
public class Kid {


    @NotBlank(message = "La identificación no puede estar vaciá ")
    private String identification;

    @NotBlank(message = "El nombre no puede estar vació")
    @Size(max = 30, message = "El nombre no puede tener mas de 30 caracteres")
    private String name;
    @Min(value = 1, message = "La edad minima es 1")
    @Max(value = 15, message = "La edad de ser menor o igual a 15")
    private byte age;
    
    private char gender;

    private Location location;

}
