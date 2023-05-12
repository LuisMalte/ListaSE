package co.edu.umanizales.tads.controller.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationSexQuantityDTO {
    private String city;
    private List<SexQuantityDTO> sexes;
    private int total;

    public LocationSexQuantityDTO(String city) {
        this.city = city;
        this.total=0;
        this.sexes = new ArrayList<>();
        this.sexes.add(new SexQuantityDTO('M',0));
        this.sexes.add(new SexQuantityDTO('F',0));
    }
}
