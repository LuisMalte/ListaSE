package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ErrorDTO;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListException;

import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDECircularService;
import co.edu.umanizales.tads.service.LocationService;
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
@RequestMapping(path = "/listcircular")
public class ListDECircularController {

    @Autowired
    private ListDECircularService listDECircularService ;
    @Autowired
    private LocationService locationService;


    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() throws ListException {
        try {
            listDECircularService.getPetCircular().print();

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, listDECircularService.getPetCircular().print(), null), HttpStatus.OK);
    }



    @PostMapping (path = "/add")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCode());

        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listDECircularService.getPetCircular().add(
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
    public ResponseEntity<ResponseDTO> addToStar(@RequestBody  @Valid PetDTO petDTO) {

        Location location = locationService.getLocationByCode(petDTO.getCode());

        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listDECircularService.getPetCircular().addToStart(
                    new Pet(petDTO.getIdentification(),
                            petDTO.getNameOwner(),petDTO.getName(), petDTO.getSex().charAt(0),petDTO.getSpecies(),petDTO.getAge(),location));
        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada al inicio", null),
                HttpStatus.OK);

    }

    @GetMapping(path = "/bathepets/{letter}" )
    public ResponseEntity<ResponseDTO> bathePets(@PathVariable char letter)  {
        int num;
        try {

           num= listDECircularService.getPetCircular().bathePets(letter);

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }

        if (num ==1 ){
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se baño la primera mascota de la lista ", null), HttpStatus.OK);


        }else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se baño la mascota numero " + num +" partiendo desde la cabeza hacia "+letter , null), HttpStatus.OK);

        }



    }

    @GetMapping(path = "/clean" )
    public ResponseEntity<ResponseDTO> getCleanEveryone() {
        try {

            listDECircularService.getPetCircular().getCleanEveryone();

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }


        return new ResponseEntity<>(new ResponseDTO(
                200, "Ahora todas las mascotas están limpias" , null), HttpStatus.OK);


    }

    @GetMapping(path = "/dirty" )
    public ResponseEntity<ResponseDTO> getDirtyEveryone() {
        try {

            listDECircularService.getPetCircular().getDirtyEveryone();

        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }


        return new ResponseEntity<>(new ResponseDTO(
                200, "Ahora todos las mascotas están sucias" , null), HttpStatus.OK);


    }

    @PostMapping(path = "/addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody Pet pet, @PathVariable int position)  {
        try {
            listDECircularService.getPetCircular().addInPosition(position,pet);
        } catch (ListException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada en la posición "+position , null),
                HttpStatus.OK);

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





