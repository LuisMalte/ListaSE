package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
public class Pet {

    private String identification;
    private String nameOwner;
    @NotNull
    @NotEmpty
    private String name;
    private char sex;
    private String species;

    @Max(15) @Min(0)
    private byte age;
    private Location location;


}
