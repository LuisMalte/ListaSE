package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Data
@AllArgsConstructor
public class ListSEService {
    private ListSE kids;
    private List<RangesK> ranges;

    private void ranges()
    {
        ranges= new ArrayList<>();
        ranges.add(new RangesK(1,3));
        ranges.add(new RangesK (4,6));
        ranges.add(new RangesK (7,9));
        ranges.add(new RangesK (10,12));
        ranges.add(new RangesK (14,15));
    }

    public ListSEService() {
        kids = new ListSE();
        ranges();
        kids.add(new Kid("123","Carlos",(byte)4, Gender.BOY));
        kids.add(new Kid("256","Mariana",(byte)3, Gender.GIRL ));
        kids.add(new Kid("789","Daniel",(byte)5, Gender.BOY));

       kids.addToStart(new Kid("967","Estefania",(byte)6, Gender.GIRL));
     /*   //kids.addInPosition(2,new Kid("89","vaplo",(byte)5, true));
//          kids.deleteByIdentification("123");
       kids.kidByGeneder();
*/
    }
    public Node getKids()
    {
        return kids.getHead();
    }

    public  void addToStar(Kid kid ){
        kids.addToStart(kid);
    }
    public  void addBoyInPosition(Kid kid, int position){
        kids.addInPosition(position,kid);
    }

    public  void getKidsByGender(){
        kids.kidByGeneder();
    }

    public  void add(Kid kid ){
        kids.add(kid);
    }

    public  void deleteKidByCode( String code){
        kids.deleteByIdentification(code);
    }

    /*
    * con el metodo que se creo en ListSE en el cual la logica estaba bien se crea
    * dos clases una rango y una rango y niños
    * la cual en rango tiene los rangos y rango y niños tiene a rango y cantidad
    *  entonces en lista service creo una lista
    * ranges en lista serive hago un metodo el cual devolvera una lista de rango y niños
    * recorro la lista de rangos llamo al metodo que da la cantidad de niños por rango
    *  le entrego al minima edad
    * y la maxima edad como me entraga un entero creo un rango niños nuevo y
    *  que sea el rango qeu se esta recorirendo en este
    * momento y la cantidad que dio al llamar el metodo rangeByAgey
    *  devuelvo la lista llena de los rangos y llamo este metodo que hice en
    * controller*/
    public List<RangeKids> getRangeByKids()
    {
        List<RangeKids> rangeKids = new ArrayList<>();
        for (RangesK i: ranges){
            int quantity = kids.rangeByAge(i.getMin(),i.getMax());
            rangeKids.add(new RangeKids(i, quantity));

        }
        return rangeKids;

    }
}
