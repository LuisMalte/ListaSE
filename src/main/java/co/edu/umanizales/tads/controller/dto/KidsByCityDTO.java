package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class KidsByCityDTO {

    private  String city;
    private List<KidsByGenderDTO> genders;
    private int total;
}

