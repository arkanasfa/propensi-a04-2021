package propensi.a04.sisdi.service;

import java.util.Collections;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public PengaduanModel updatePengaduan(PengaduanModel pengaduan) {
        PengaduanModel pengaduanTarget = pengaduanDb.findById(pengaduan.getId()).get();
        try {
            pengaduanDb.save(pengaduanTarget);
            return pengaduanTarget;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }
    
    @Override
    public void deletePengaduan(PengaduanModel pengaduan) {
        pengaduanDb.delete(pengaduan);
    }

    @Override
    public Page<PengaduanModel> findPaginated(Pageable pageable, List<PengaduanModel> pengaduan) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<PengaduanModel> list;

        if(pengaduan.size() < startItem){
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, pengaduan.size());
            list = pengaduan.subList(startItem, toIndex);
        }
        Page<PengaduanModel> pengaduanPage
        = new PageImpl<PengaduanModel>(list,
                PageRequest.of(currentPage,pageSize), pengaduan.size());
        return pengaduanPage;
    }

}