package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.KomponenPengaliModel;
import propensi.a04.sisdi.repository.KomponenPengaliDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService{
    @Autowired
    KomponenPengaliDb komponenPengaliDb;

    //Find Id
    @Override
    public KomponenPengaliModel getKomponenGajiById(Long komponenGajiId){
        return komponenPengaliDb.findById(komponenGajiId).get();
    }

    //Ubah Komponen Gaji
    @Override
    public KomponenPengaliModel ubahKomponenGaji(KomponenPengaliModel komponenGaji){
        KomponenPengaliModel targetUbah= komponenPengaliDb.findById(komponenGaji.getId()).get();
        try{
           targetUbah.setUangSnack(komponenGaji.getUangSnack());
           targetUbah.setAbsensi(komponenGaji.getAbsensi());
           targetUbah.setInfal(komponenGaji.getInfal());
           targetUbah.setKeluarga(komponenGaji.getKeluarga());
           targetUbah.setLembur(komponenGaji.getLembur());
           targetUbah.setPPH(komponenGaji.getPPH());
           targetUbah.settPPH(komponenGaji.gettPPH());
           targetUbah.setTunjanganPrestasi(komponenGaji.getTunjanganPrestasi());
           targetUbah.setGol1AC(komponenGaji.getGol1AC());
           targetUbah.setGol1D2A(komponenGaji.getGol1D2A());
           targetUbah.setGol2B(komponenGaji.getGol2B());
           targetUbah.setGol2CD(komponenGaji.getGol2CD());
           targetUbah.setGol3A(komponenGaji.getGol3A());
           targetUbah.setGol3BC(komponenGaji.getGol3BC());
           targetUbah.setGol3D4AB(komponenGaji.getGol3D4AB());
           targetUbah.setGol4CD(komponenGaji.getGol4CD());
           targetUbah.setGol5AD(komponenGaji.getGol5AD());
           komponenPengaliDb.save(targetUbah);
           return komponenGaji;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }
}
