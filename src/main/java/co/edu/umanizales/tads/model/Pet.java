package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
public class Pet {

    private String identification;
    private String nameOwner;
    private String name;
    private char sex;
    private String species;
    private byte age;
    private Location location;


}
