package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {

    private ListDE pets;


    public ListDEService() {
        pets = new ListDE();


    }
    //   kids.addToStart(new Kid("967","Estefania",(byte)6, Gender.GIRL));
     /*   //kids.addInPosition(2,new Kid("89","vaplo",(byte)5, true));
       kids.kidByGeneder();
*/


}





