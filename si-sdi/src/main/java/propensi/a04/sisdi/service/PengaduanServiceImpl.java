package propensi.a04.sisdi.service;

import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.repository.PengaduanDb;

@Service
@Transactional
public class PengaduanServiceImpl implements PengaduanService {

    @Autowired
    PengaduanDb pengaduanDb;

    @Override
    public PengaduanModel addPengaduan(PengaduanModel pengaduan) {

        return pengaduanDb.save(pengaduan);
    }

    @Override
    public List<PengaduanModel> getPengaduanList() {
        return pengaduanDb.findAll();
    }

    @Override
    public PengaduanModel getPengaduanById(Long id) {

        return pengaduanDb.findById(id).get();
    }

    @Override
    public String generateKodePengaduan(PengaduanModel pengaduan){
        Random rand = new Random();
        String generate = "PG" + pengaduan.getId() + "-" + Integer.toString(rand.nextInt(9))
                + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9)) + "-"
                + Integer.toString(rand.nextInt(9))  ;
        return generate;
    }


}