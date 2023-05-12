package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportobjectsLocationSexDTO {
    private List<LocationSexQuantityDTO> locationSexQuantityDTOS;

    public ReportobjectsLocationSexDTO(List<Location> cities) {
        locationSexQuantityDTOS = new ArrayList<>();
        for(Location location: cities){
            locationSexQuantityDTOS.add(new
                    LocationSexQuantityDTO(location.getName()));
        }
    }

    // método actualizar
    public void updateQuantity(String city,char gender){
        for(LocationSexQuantityDTO loc: locationSexQuantityDTOS){
            if(loc.getCity().equals(city)){
                for(SexQuantityDTO genderDTO: loc.getSexes()){
                    if(genderDTO.getSex()==gender){
                        genderDTO.setQuantity(genderDTO.getQuantity()+1);
                        loc.setTotal(loc.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }
}
