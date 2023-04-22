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




