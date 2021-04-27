package propensi.a04.sisdi.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.KaryawanDB;
import propensi.a04.sisdi.repository.PengaduanDb;

@Service
@Transactional
public class PengaduanServiceImpl implements PengaduanService {

    @Autowired
    PengaduanDb pengaduanDb;
    KaryawanDB karyawanDb;

    @Override
    public PengaduanModel addPengaduan(PengaduanModel pengaduan) {
        
        return pengaduanDb.save(pengaduan);
    }

    @Override
    public List<PengaduanModel> getPengaduanList() {
        return pengaduanDb.findAll();
    }
/* 
    @Override
    public List<PengaduanModel> getPengaduanByUser(UserModel user) {
        
        return pengaduanDb.findByUser(user);
    } */

    @Override
    public PengaduanModel updatePengaduan(PengaduanModel pengaduan) {
       
        pengaduanDb.save(pengaduan);
        return pengaduan;
    }

    @Override
    public Optional<PengaduanModel> getPengaduanById(Long id) {
       
        return pengaduanDb.findById(id);
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
