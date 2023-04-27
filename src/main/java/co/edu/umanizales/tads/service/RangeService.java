package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.RangesK;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RangeService {
    private List<RangesK> ranges ;

    public RangeService() {
        //Conectar a una base de datos
        ranges= new ArrayList<>();
        ranges.add(new RangesK(1,3));
        ranges.add(new RangesK (4,6));
        ranges.add(new RangesK (7,9));
        ranges.add(new RangesK (10,12));
        ranges.add(new RangesK (14,15));

  }
}



