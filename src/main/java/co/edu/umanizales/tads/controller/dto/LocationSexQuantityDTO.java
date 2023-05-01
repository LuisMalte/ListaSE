package co.edu.umanizales.tads.controller.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationSexQuantityDTO {
    private String city;
    private List<SexQuantityDTO> sex;
    private int total;

    public LocationSexQuantityDTO(String city) {
        this.city = city;
        this.total=0;
        this.sex = new ArrayList<>();
        this.sex.add(new SexQuantityDTO('M',0));
        this.sex.add(new SexQuantityDTO('F',0));
    }
}
