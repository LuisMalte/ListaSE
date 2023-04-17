package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.RangeKids;
import co.edu.umanizales.tads.model.RangesK;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @Autowired
    private LocationService locationService;

    private List<RangesK> ranges;

    private void ranges() {
        ranges = new ArrayList<>();
        ranges.add(new RangesK(1, 3));
        ranges.add(new RangesK(4, 6));
        ranges.add(new RangesK(7, 9));
        ranges.add(new RangesK(10, 12));
        ranges.add(new RangesK(14, 15));
    }


    @GetMapping
    public ResponseEntity<ResponseDTO> getfKids() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKfids(), null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addBoyByPosition(@PathVariable int position, @RequestBody Kid boy) {
        listSEService.getKids().addInPosition(position, boy);
        return new ResponseEntity<>(new ResponseDTO(200, "Niño adicionado", null),
                HttpStatus.OK);

    }




    @GetMapping(path="/deleteKid/{code}")
    public ResponseEntity<ResponseDTO> deleteByCode(@PathVariable String code){
        listSEService.getKids().deleteByIdentification(code); ;
        return new ResponseEntity<>(new ResponseDTO(200,
                "Niño borrado" ,null),
                HttpStatus.OK);


    }


    @PostMapping(path = "/addtostar")
    public ResponseEntity<ResponseDTO> addToStar(@RequestBody Kid kid) {
        listSEService.getKids().addToStart(kid);
        return new ResponseEntity<>(new ResponseDTO(200, "Niño adicionado al inicio", null),
                HttpStatus.OK);

    }


    /*@PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> add(@RequestBody Kid kid) {
        listSEService.getKids().add(kid);
        return new ResponseEntity<>(new ResponseDTO(200, "Niño adicionado", null),
                HttpStatus.OK);

    }*/
    @GetMapping(path = "/kidbygender")
    public ResponseEntity<ResponseDTO> getKidsByGender() {
        listSEService.getKids().kidByGender();
        return new ResponseEntity<>(new ResponseDTO(200, "Se organizo niños primero niñas después", null),
                HttpStatus.OK);

    }



    /*
     * con el metodo que se creó en ListSE en el cual la logica estaba bien se crea
     * dos clases un rango y un rango y niños
     * la cual en rango tiene los rangos menimo(from)-maximo(to) clase rango y niños tiene a rango y cantidad
     *  entonces en lista service creo una lista
     * ranges en lista serive hago un metodo el cual devolvera una lista de rango y niños
     * recorro la lista de rangos llamo al metodo que da la cantidad de niños por rango
     *  le entrego al minima edad
     * y la maxima edad como me entraga un entero creo un rango niños nuevo y
     *  que sea el rango qeu se esta recorirendo en este
     * momento y la cantidad que dio al llamar el metodo rangeByAgey
     *  devuelvo la lista llena de los rangos y llamo este metodo que hice en
     * controller*/
    @GetMapping(path = "/Rangeage")

    public ResponseEntity<ResponseDTO> getRangeByKids() {
        ranges();
        List<RangeKids> rangeKids = new ArrayList<>();
        for (RangesK i : ranges) {
            int quantity = listSEService.getKids().rangeByAge(i.getFrom(), i.getTo());
            rangeKids.add(new RangeKids(i, quantity));


        }
        return new ResponseEntity<>(new ResponseDTO(200, rangeKids, null),
                HttpStatus.OK);


    }

    @PostMapping (path = "/add")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCode());
        Boolean sameKids  = listSEService.getKids().sameKids(new Kid(kidDTO.getIdentification(),
                kidDTO.getName(), kidDTO.getAge(),
                kidDTO.getGender(), location));
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        } else if (sameKids.equals(false)) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,"ERROR Este niño ya ha sido agregado",
                    null), HttpStatus.OK);
        }else {
            listSEService.getKids().add(
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            kidDTO.getGender(), location));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/kidsbydepartment")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(){
        List<KidsByLocationDTO> kidsByLocationDTOList1 = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCodeLimited(loc.getCode());
            if(count>0){
                kidsByLocationDTOList1.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList1,
                null), HttpStatus.OK);
    }


}





