package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import co.edu.umanizales.tads.model.RangesK;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Data

public class ListSEService {
    private ListSE kids;
    private List<RangesK> ranges;



    public ListSEService() {
        kids = new ListSE();



    //   kids.addToStart(new Kid("967","Estefania",(byte)6, Gender.GIRL));
     /*   //kids.addInPosition(2,new Kid("89","vaplo",(byte)5, true));
       kids.kidByGeneder();
*/


    }
    public Node getKfids()
    {
        return kids.getHead();
    }

}




