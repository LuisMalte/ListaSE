package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.RangesK;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
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
    @Autowired
    private RangeService rangesService;




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

   /* Location location = locationService.getLocationByCode(kidDTO.getCode());
    Boolean samePet  = listSEService.getKids().samePet(new Kid(kidDTO.getIdentification(),
            kidDTO.getName(), kidDTO.getAge(),
            kidDTO.getGender(), location));
        if(location == null){
        return new ResponseEntity<>(new ResponseDTO(
                404,"La ubicación no existe",
                null), HttpStatus.OK);
    } else if (samePet.equals(false)) {
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
    }Location location = locationService.getLocationByCode(kidDTO.getCode());
    Boolean samePet  = listSEService.getKids().samePet(new Kid(kidDTO.getIdentification(),
            kidDTO.getName(), kidDTO.getAge(),
            kidDTO.getGender(), location));
        if(location == null){
        return new ResponseEntity<>(new ResponseDTO(
                404,"La ubicación no existe",
                null), HttpStatus.OK);
    } else if (samePet.equals(false)) {
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
    }*/



    @GetMapping(path="/deleteKid/{code}")
    public ResponseEntity<ResponseDTO> deleteByCode(@PathVariable String code){
        listSEService.getKids().deleteByIdentification(code);
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


    @GetMapping(path = "/rangeage")

    public ResponseEntity<ResponseDTO> getRangeByKids() {
        List<RangeAgeObjetsDTO>  kidsRangeDTOList = new ArrayList<>();

        for (RangesK i : rangesService.getRanges()) {
            int quantity = listSEService.getKids().rangeByAge(i.getFrom(), i.getTo());
            kidsRangeDTOList.add(new RangeAgeObjetsDTO(i, quantity));


        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsRangeDTOList, null),
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
        List<objetsByLocationDTO> objetsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0){
                objetsByLocationDTOList.add(new objetsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, objetsByLocationDTOList,
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/kidsbydepartment")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(){
        List<objetsByLocationDTO> objetsByLocationDTOList1 = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCodeLimited(loc.getCode());
            if(count>0){
                objetsByLocationDTOList1.add(new objetsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, objetsByLocationDTOList1,
                null), HttpStatus.OK);
    }





    @GetMapping(path="/invert")
    public ResponseEntity<ResponseDTO> invert(){

        listSEService.getKids().invert();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se invirtió la lista",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/deleteKidsbyage/{age}")
    public ResponseEntity<ResponseDTO> deleteKidsbyage(@PathVariable byte age){

        listSEService.getKids().deleteKidsByAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se borro los niños de "+age+ " años",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/intercalatebygender")
    public ResponseEntity<ResponseDTO> intercalatebygender(){

        listSEService.getKids().intercalateByGender();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se intercalo la lista por genero",
                null), HttpStatus.OK);
    }
    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){

        listSEService.getKids().averageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids().averageAge(),
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/sendbottombyletter/{letter}")
    public ResponseEntity<ResponseDTO> sendbottom(@PathVariable char letter) {


            listSEService.getKids().sendBottomByLetter(letter);
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Se envió al final los niños que su nombre inicia con la letra "
                            + letter, null),
                    HttpStatus.OK);

    }

    @GetMapping(path = "/movedposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> movedPosition(@PathVariable  String code,  @PathVariable int move ) {


        listSEService.getKids().advancePosition(code,move, listSEService.getKids());
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se movio el niño"
                        , null),
                HttpStatus.OK);

    }


    @GetMapping(path = "/lostposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> LostPosition(@PathVariable  String code,  @PathVariable int move ) {
        listSEService.getKids().LostPosition(code,move);
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se movio el niño"
                        , null),
                HttpStatus.OK);

    }

    @GetMapping(path = "/kidsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportKisLocationGenders(@PathVariable byte age) {
        ReportobjectsLocationSexDTO report =
                new ReportobjectsLocationSexDTO(locationService.getLocationsByCodeSize(8));
        listSEService.getKids()
                .getReportKidsByLocationGendersByAge(age,report);
        return new ResponseEntity<>(new ResponseDTO(
                200,report,
                null), HttpStatus.OK);
    }

}





