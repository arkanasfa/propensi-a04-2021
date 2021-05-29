package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.PengurusHarianModel;
import propensi.a04.sisdi.repository.PengurusHarianDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class PengurusHarianServiceImpl implements PengurusHarianService{
    @Autowired
    PengurusHarianDb pengurusHarianDb;

    @Override
    public void addPengurusHarian(PengurusHarianModel PH) {
        pengurusHarianDb.save(PH);
    }
}
