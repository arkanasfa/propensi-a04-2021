package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.PengajuanCutiModel;
import propensi.a04.sisdi.model.StatusModel;
import propensi.a04.sisdi.repository.PengajuanCutiDb;
import propensi.a04.sisdi.repository.StatusDB;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PengajuanCutiServiceImpl implements PengajuanCutiService{
    @Autowired
    PengajuanCutiDb pengajuanCutiDb;

    @Autowired
    StatusDB statusDB;

    @Override
    public List<PengajuanCutiModel> getCutiList (){
        return pengajuanCutiDb.findAll();
    }

    @Override
    public PengajuanCutiModel getCutiById(Long id) {
        return pengajuanCutiDb.findById(id).get();
    }

    @Override
    public void addCuti(PengajuanCutiModel cuti) {
        pengajuanCutiDb.save(cuti);
    }


    @Override
    public PengajuanCutiModel changeCuti(PengajuanCutiModel cuti) {
        PengajuanCutiModel targetCuti = pengajuanCutiDb.findById(cuti.getId()).get();
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
    public void batalkanCuti(PengajuanCutiModel cuti) {
        StatusModel pembatalanPU = statusDB.findById(Long.valueOf(10)).get();
        cuti.setIdstatus(pembatalanPU);
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
        int durasi= (int)ChronoUnit.DAYS.between(date1, date2)+1;
        return durasi;
    }

    @Override
    public Integer generateStartValid(PengajuanCutiModel cuti) {
        LocalDate date1 = cuti.getTanggalMulai().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = LocalDate.now();
        int gap= (int)ChronoUnit.DAYS.between(date1, date2);
        return gap;
    }

    @Override
    public void setujuiCuti(PengajuanCutiModel cuti) {
        if(cuti.getIdstatus().getId()==1) {
            StatusModel stat = statusDB.findById(Long.valueOf(2)).get();
            cuti.setIdstatus(stat);
        }
        else if(cuti.getIdstatus().getId()==2){
            StatusModel stat = statusDB.findById(Long.valueOf(3)).get();
            cuti.setIdstatus(stat);
        }
        else if(cuti.getIdstatus().getId()==10){
            StatusModel stat = statusDB.findById(Long.valueOf(11)).get();
            cuti.setIdstatus(stat);
        }
        else if(cuti.getIdstatus().getId()==11){
            StatusModel stat = statusDB.findById(Long.valueOf(16)).get();
            cuti.setIdstatus(stat);
        }
        else if(cuti.getIdstatus().getId()==16){
            StatusModel stat = statusDB.findById(Long.valueOf(4)).get();
            cuti.setIdstatus(stat);
        }
        else {
            StatusModel stat = statusDB.findById(Long.valueOf(7)).get();
            cuti.setIdstatus(stat);
        }
    }

    @Override
    public void tolakCuti(PengajuanCutiModel cuti) {
        StatusModel stat = statusDB.findById(Long.valueOf(5)).get();
        cuti.setIdstatus(stat);
    }

    @Override
    public void tolakPembatalanCuti(PengajuanCutiModel cuti) {
        StatusModel stat = statusDB.findById(Long.valueOf(7)).get();
        cuti.setIdstatus(stat);
    }

    @Override
    public List<PengajuanCutiModel> getPengajuanCutiById_Status(StatusModel stat) {
        return pengajuanCutiDb.findAllByIdstatus(stat);
    }
}
