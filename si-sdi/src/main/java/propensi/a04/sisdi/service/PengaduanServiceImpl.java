package propensi.a04.sisdi.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

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
    public void addPengaduan(PengaduanModel pengaduan) {
        pengaduanDb.save(pengaduan);    
        
    }

    @Override
    public List<PengaduanModel> getPengaduanList() {
        return pengaduanDb.findAll();
    }

    @Override
    public PengaduanModel getPengaduanByIdKaryaPengadu(Long idKarya_pengadu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PengaduanModel getPengaduanByIdOrtuPengadu(Long idOrtu_pengadu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PengaduanModel getPengaduanByIdSiswaPengadu(Long idSiswa_pengadu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PengaduanModel updatePengaduan(PengaduanModel pengaduan) {
        // TODO Auto-generated method stub
        pengaduanDb.save(pengaduan);
        return pengaduan;
    }

    @Override
    public Optional<PengaduanModel> getPengaduanById(Long id) {
        // TODO Auto-generated method stub
        return pengaduanDb.findById(id);
    }

    
}
