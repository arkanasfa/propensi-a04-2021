package propensi.a04.sisdi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import propensi.a04.sisdi.model.SiswaModel;
import propensi.a04.sisdi.repository.SiswaDb;


@Service
@Transactional
public class SiswaServiceImpl implements SiswaService {
    @Autowired
    SiswaDb siswaDb;

    @Override
    public void addSiswa(SiswaModel siswaModel){siswaDb.save(siswaModel);}

    @Override
    public List<SiswaModel> getListSiswa() {
        return siswaDb.findAll();
    }

    @Override
    public SiswaModel getSiswaById(Long id) {

        return siswaDb.findById(id).get();
    }
    
}
