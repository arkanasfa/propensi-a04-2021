package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.LemburModel;
import propensi.a04.sisdi.repository.LemburDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class LemburServiceImpl implements LemburService{
    @Autowired
    LemburDB lemburDB;

    @Override
    public void addLembur(LemburModel lembur){lemburDB.save(lembur);}

    @Override
    public LemburModel changeLembur(LemburModel lembur) {
        LemburModel targetLembur= lemburDB.findById(lembur.getId()).get();
        try{
            targetLembur.setId_status(lembur.getId_status());
            targetLembur.setAlasan(lembur.getAlasan());
            targetLembur.setJamMulai(lembur.getJamMulai());
            targetLembur.setJamSelesai(lembur.getJamSelesai());
            targetLembur.setTanggalLembur(lembur.getTanggalLembur());
            targetLembur.setTanggalRequest(lembur.getTanggalRequest());
            targetLembur.setJenis(lembur.getJenis());
            targetLembur.setId_infal(lembur.getId_infal());
            targetLembur.setKode_lembur(lembur.getKode_lembur());
            lemburDB.save(targetLembur);
            return targetLembur;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }

    @Override
    public List<LemburModel> getLemburList(){return lemburDB.findAll();}

    @Override
    public String generateKodeLembur(LemburModel lembur){
        Random rand = new Random();
        if(lembur.getJenis()==1){
            String generate = "LB" + "-" + Integer.toString(lembur.getJenis()) + "-" + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9))  ;
            return generate;
        }
        else{
            String generate = "IF" + "-" + Integer.toString(lembur.getJenis()) + "-" + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9)) + Integer.toString(rand.nextInt(9))  ;
            return generate;
        }
    }

    @Override
    public Integer generateDurasi(LemburModel lembur){
        Integer durasiMulai = lembur.getJamMulai().getHours();
        Integer durasiSelesai = lembur.getJamSelesai().getHours();
        Integer durasiAkhir = durasiSelesai-durasiMulai;
        return durasiAkhir;
    }
    @Override
    public LemburModel getLemburById(Long lemburId){return lemburDB.findById(lemburId).get();}

    @Override
    public void deleteLembur(LemburModel lembur){lemburDB.delete(lembur);}
}
