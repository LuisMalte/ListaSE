package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getPets().print(), null), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> add( @RequestBody Pet pet) {
        listDEService.getPets().add(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota adicionada", null),
                HttpStatus.OK);

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
        listDEService.getPets().advancePosition(code, move, listDEService.getPets());
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se movio la mascota " + move + " posiciones adelante", null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/lostposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> lostPosition(@PathVariable String code, @PathVariable int move) {
        listDEService.getPets().lostPosition(code, move, listDEService.getPets());
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








}





