package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.*;

@Data

public class Pet {

    private String identification;
    private String nameOwner;
    private String name;
    private char sex;
    private String species;
    private byte age;
    private Location location;
    private boolean dirty;

    public Pet(String identification, String nameOwner, String name, char sex, String species, byte age, Location location) {
        this.identification = identification;
        this.nameOwner = nameOwner;
        this.name = name;
        this.sex = sex;
        this.species = species;
        this.age = age;
        this.location = location;
        dirty = true;

    }


}
