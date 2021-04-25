package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.PengajuanCutiModel;
import propensi.a04.sisdi.repository.PengajuanCutiDb;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PengajuanCutiServiceImpl implements PengajuanCutiService{
    @Autowired
    PengajuanCutiDb pengajuanCutiDb;

    @Override
    public List<PengajuanCutiModel> getCutiList (){
        return pengajuanCutiDb.findAll();
    }

    @Override
    public Optional<PengajuanCutiModel> getCutiById(Long id) {
        return pengajuanCutiDb.findById(id);
    }

    @Override
    public void addCuti(PengajuanCutiModel cuti) {
        pengajuanCutiDb.save(cuti);
    }

    @Override
    public PengajuanCutiModel editCuti(PengajuanCutiModel cuti) {
        PengajuanCutiModel targetCuti = pengajuanCutiDb.findById(cuti.getId()).orElse(null);
        try {
            targetCuti.setDurasi(cuti.getDurasi());
            targetCuti.setJenis(cuti.getJenis());
            targetCuti.setKeterangan(cuti.getKeterangan());
            targetCuti.setTanggalMulai(cuti.getTanggalMulai());
            targetCuti.setTanggalSelesai(cuti.getTanggalSelesai());
            targetCuti.setKode_cuti(cuti.getKode_cuti());
            pengajuanCutiDb.save(targetCuti);
            return targetCuti;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deleteCuti(PengajuanCutiModel cuti) {
        pengajuanCutiDb.delete(cuti);
    }

    @Override
    public String generateKodeCuti(PengajuanCutiModel cuti) {
        Random rand = new Random();
        String generate = "CT" + "-" + Integer.toString(cuti.getJenis()) + "-" + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9));
        return generate;
    }

    @Override
    public Integer generateDurasi(PengajuanCutiModel cuti) {
        LocalDate date1 = cuti.getTanggalMulai().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = cuti.getTanggalSelesai().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int durasi = (int)(Duration.between(date1, date2).toDays());
        return durasi;
    }
}
