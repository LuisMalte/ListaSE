package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @GetMapping   public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);
    }

 @PostMapping(path="/addtoposition/{position}")
 public ResponseEntity<ResponseDTO> addBoyByPosition(@PathVariable int position, @RequestBody Kid boy)
 {
     listSEService.addBoyInPosition(boy,position);
     return new ResponseEntity<>(new ResponseDTO(200,"Niño adicionado", null),
                 HttpStatus.OK);

 }
    @GetMapping(path="/deleteKid/{code}")
    public ResponseEntity<ResponseDTO> deleteByCode(@PathVariable String code)
    {
        listSEService.deleteKidByCode(code);
        return new ResponseEntity<>(new ResponseDTO(200,"Niño borrado", null),
                HttpStatus.OK);

    }

    @PostMapping(path="/addtostar")
    public ResponseEntity<ResponseDTO> addToStar(@RequestBody Kid boy)
    {
        listSEService.addToStar(boy);
        return new ResponseEntity<>(new ResponseDTO(200,"Niño adicionado al inicio", null),
                HttpStatus.OK);

    }


    @PostMapping(path="/add")
    public ResponseEntity<ResponseDTO> add(@RequestBody Kid boy)
    {
        listSEService.add(boy);
        return new ResponseEntity<>(new ResponseDTO(200,"Niño adicionado", null),
                HttpStatus.OK);

    }
    @GetMapping(path="/kidbygender")
    public ResponseEntity<ResponseDTO> getKidsByGender()
    {
        listSEService.getKidsByGender();
        return new ResponseEntity<>(new ResponseDTO(200,"Se organizo niños primero niñas después", null),
                HttpStatus.OK);

    }


    @GetMapping(path="/Rangeage")
    public ResponseEntity<ResponseDTO> getRangeByKids()
    {
        return new ResponseEntity<>(new ResponseDTO(200,listSEService.getRangeByKids(), null),
                HttpStatus.OK);

    }



}





