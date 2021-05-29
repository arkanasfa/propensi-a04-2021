package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.PengurusHarianModel;
import propensi.a04.sisdi.model.WakilPengurusHarianModel;
import propensi.a04.sisdi.repository.WakilPengurusHarianDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class WakilPengurusHarianServiceImpl implements WakilPengurusHarianService{
    @Autowired
    WakilPengurusHarianDb wakilPengurusHarianDb;

    @Override
    public void addWakilPengurusHarian(WakilPengurusHarianModel WPH) {
        wakilPengurusHarianDb.save(WPH);
    }
}
