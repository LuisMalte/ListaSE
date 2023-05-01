package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.objetsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.RangeAgeObjetsDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.model.RangesK;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RangeService rangesService;


    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getPets().print(), null), HttpStatus.OK);
    }

    /*@PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> add( @RequestBody Pet pet) {
        listDEService.getPets().add(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada", null),
                HttpStatus.OK);

    }*/

    @PostMapping (path = "/add")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCode());
        Boolean sameKids  = listDEService.getPets().samePet(new Pet(petDTO.getIdentification(),
                petDTO.getNameOwner(),petDTO.getName(), petDTO.getSex(),petDTO.getSpecies(),petDTO.getAge(),location));
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        } else if (sameKids.equals(false)) {
            return new ResponseEntity<>(new ResponseDTO(
                    400,"ERROR Esta mascota ya ha sido agregado",
                    null), HttpStatus.OK);
        }else {
            listDEService.getPets().add(
                    new Pet(petDTO.getIdentification(),
                            petDTO.getNameOwner(),petDTO.getName(), petDTO.getSex(),petDTO.getSpecies(),petDTO.getAge(),location));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado la mascota",
                    null), HttpStatus.OK);
        }
    }
    @PostMapping(path = "/addtostar")
    public ResponseEntity<ResponseDTO> addToStar(@RequestBody Pet pet) {
        listDEService.getPets().addToStart(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada al inicio", null),
                HttpStatus.OK);

    }

    @GetMapping (path = "/deletepet/{code}")
    public ResponseEntity<ResponseDTO> deletepet(@PathVariable String code) {
        listDEService.getPets().deleteById(code);
        return new ResponseEntity<>(new ResponseDTO(
                200, "se borro la mascota con el id "+ code, null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody Pet pet, @PathVariable int position) {
        listDEService.getPets().addInPosition(position,pet);
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada en la posición "+position , null),
                HttpStatus.OK);

    }

    @GetMapping (path = "/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listDEService.getPets().invert();
        return new ResponseEntity<>(new ResponseDTO(
                200, "la lista se ha invertido ", null), HttpStatus.OK);
    }

    @GetMapping (path = "/getfirstmale")
    public ResponseEntity<ResponseDTO> getFirstMale() {
        listDEService.getPets().getfirstMale();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se organizo la lista niños primero despues niñas", null), HttpStatus.OK);
    }

    @GetMapping(path="/intercalatebysex")
    public ResponseEntity<ResponseDTO> intercalateBySex(){

        listDEService.getPets().intercalateBySex();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se intercalo la lista por sexo",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/deletepetsbyage/{age}")
    public ResponseEntity<ResponseDTO> deletePetsByAge(@PathVariable byte age){

        listDEService.getPets().deletePetsByAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se borro los niños de "+age+ " años",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){

        listDEService.getPets().averageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPets().averageAge(),
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/advanceposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> advancePosition(@PathVariable String code, @PathVariable int move) {
        listDEService.getPets().advancePosition(code, move);
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se movio la mascota " + move + " posiciones adelante", null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/lostposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> lostPosition(@PathVariable String code, @PathVariable int move) {
        listDEService.getPets().lostPosition(code, move);
        return new ResponseEntity<>(
                new ResponseDTO(200, "la mascota peridio " + move + " posiciones", null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/sendbottombyletter/{letter}")
    public ResponseEntity<ResponseDTO> sendbottom(@PathVariable char letter) {


        listDEService.getPets().sendBottomByLetter(letter);
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se envió al final las mascotas que su nombre inicia con la letra "
                        + letter, null),
                HttpStatus.OK);

    }

    @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO> petsByLocations(){
        List<objetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listDEService.getPets().getCountPetsByLocationCode(loc.getCode());
            if(count>0){
                petsByLocationDTOList.add(new objetsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,petsByLocationDTOList,
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/petsbydepartment")
    public ResponseEntity<ResponseDTO>petsByDepartment (){
        List<objetsByLocationDTO> objetsByLocationDTOList1 = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listDEService.getPets().getCountPetsByLocationCodeLimited(loc.getCode());
            if(count>0){
                objetsByLocationDTOList1.add(new objetsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, objetsByLocationDTOList1,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/rangeagepets")

    public ResponseEntity<ResponseDTO> getRangeByKids() {
        List<RangeAgeObjetsDTO>  kidsRangeDTOList = new ArrayList<>();

        for (RangesK i : rangesService.getRanges()) {
            int quantity = listDEService.getPets().rangeByAge(i.getFrom(), i.getTo());
            kidsRangeDTOList.add(new RangeAgeObjetsDTO(i, quantity));


        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsRangeDTOList, null),
                HttpStatus.OK);


    }









}





