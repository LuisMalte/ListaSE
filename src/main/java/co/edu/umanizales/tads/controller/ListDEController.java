package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.model.RangesK;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<ResponseDTO> getPets() throws ListException {
        try {
            listDEService.getPets().print();

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
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
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCode());

        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
            try {
                listDEService.getPets().add(
                        new Pet(petDTO.getIdentification(),
                                petDTO.getNameOwner(),petDTO.getName(), petDTO.getSex().charAt(0),petDTO.getSpecies(),petDTO.getAge(),location));
            } catch (ListException e) {
                return new ResponseEntity<>(new ResponseDTO(
                        409,e.getMessage(),
                        null), HttpStatus.OK);
            }


            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado la mascota",
                    null), HttpStatus.OK);

    }

    @PostMapping(path = "/addtostar")
    public ResponseEntity<ResponseDTO> addToStar(@RequestBody Pet pet) {
        listDEService.getPets().addToStart(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada al inicio", null),
                HttpStatus.OK);

    }

    @GetMapping(path = "/deletepet/{code}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable String code) {
        try {
            listDEService.getPets().deleteById(code);
        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Se borró la mascota con el Id " + code, null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody Pet pet, @PathVariable int position) throws ListException {
        try {
            listDEService.getPets().addInPosition(position,pet);
        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada en la posición "+position , null),
                HttpStatus.OK);

    }

    @GetMapping (path = "/invert")
    public ResponseEntity<ResponseDTO> invert() {

        try {
            listDEService.getPets().invert();

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, "la lista se ha invertido ", null), HttpStatus.OK);
    }

    @GetMapping (path = "/getfirstmale")
    public ResponseEntity<ResponseDTO> getFirstMale() throws ListException {
        try {
            listDEService.getPets().getfirstMale();

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se organizo la lista niños primero despues niñas", null), HttpStatus.OK);
    }

    @GetMapping(path="/intercalatebysex")
    public ResponseEntity<ResponseDTO> intercalateBySex() throws ListException {

        try {
            listDEService.getPets().intercalateBySex();


        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,"Se intercalo la lista por sexo",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/deletepetsbyage/{age}")
    public ResponseEntity<ResponseDTO> deletePetsByAge(@PathVariable byte age) throws ListException {

        try {
            listDEService.getPets().deletePetsByAge(age);

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se borro los niños de "+age+ " años",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge() throws ListException {
        try {
            listDEService.getPets().averageAge();

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPets().averageAge(),
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/advanceposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> advancePosition(@PathVariable String code, @PathVariable int move) throws ListException {
        try {
            listDEService.getPets().advancePosition(code, move);

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se movio la mascota " + move + " posiciones adelante", null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/lostposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> lostPosition(@PathVariable String code, @PathVariable int move) throws ListException {
        try {
            listDEService.getPets().lostPosition(code, move);

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ResponseDTO(200, "la mascota peridio " + move + " posiciones", null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/sendbottombyletter/{letter}")
    public ResponseEntity<ResponseDTO> sendbottom(@PathVariable char letter) throws ListException {
        try {
            listDEService.getPets().sendBottomByLetter(letter);

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }


        return new ResponseEntity<>(
                new ResponseDTO(200, "Se envió al final las mascotas que su nombre inicia con la letra "
                        + letter, null),
                HttpStatus.OK);

    }

    @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO> petsByLocations(){
        List<objetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        try {
            for(Location loc: locationService.getLocations()){
                int count = listDEService.getPets().getCountPetsByLocationCode(loc.getCode());
                if(count>0){
                    petsByLocationDTOList.add(new objetsByLocationDTO(loc,count));
                }
            }

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,petsByLocationDTOList,
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/petsbydepartment")
    public ResponseEntity<ResponseDTO>petsByDepartment (){
        List<objetsByLocationDTO> objetsByLocationDTOList1 = new ArrayList<>();
        try {
            for(Location loc: locationService.getLocations()){
                int count = listDEService.getPets().getCountPetsByLocationCodeLimited(loc.getCode());
                if(count>0){
                    objetsByLocationDTOList1.add(new objetsByLocationDTO(loc,count));
                }
            }

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200, objetsByLocationDTOList1,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/rangeagepets")

    public ResponseEntity<ResponseDTO> getRangeByKids() {
        List<RangeAgeObjetsDTO>  kidsRangeDTOList = new ArrayList<>();

        try {
            for (RangesK i : rangesService.getRanges()) {
                int quantity = listDEService.getPets().rangeByAge(i.getFrom(), i.getTo());
                kidsRangeDTOList.add(new RangeAgeObjetsDTO(i, quantity));


            }

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(200, kidsRangeDTOList, null),
                HttpStatus.OK);


    }

    @GetMapping(path = "/petsbylocationsexes/{age}")
    public ResponseEntity<ResponseDTO> getReportPetsLocationSexes(@PathVariable byte age) {
        ReportobjectsLocationSexDTO report =
                new ReportobjectsLocationSexDTO(locationService.getLocationsByCodeSize(8));
        try {
            listDEService.getPets()
                    .getReportPetsByLocationGendersByAge(age,report);

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,report,
                null), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErrorDTO> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errors.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null, errors), HttpStatus.BAD_REQUEST);
    }








}





