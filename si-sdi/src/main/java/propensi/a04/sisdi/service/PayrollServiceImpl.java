package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import propensi.a04.sisdi.model.*;
import propensi.a04.sisdi.repository.*;

import javax.naming.Context;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService{
    @Autowired
    KomponenPengaliDb komponenPengaliDb;

    @Autowired
    DokumenTotalDb dokumenTotalDb;

    @Autowired
    StatusDB statusDb;

    @Autowired
    KaryawanDb karyawanDb;

    @Autowired
    GajiDb gajiDb;

    @Autowired
    UserDb userDb;

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

    //Cari Dokumen Total udah ada belum
    @Override
    public boolean compareDokumenTotalByTanggalIsu(YearMonth now){
        int check;
        check=0;
        try{
            // List semua dokumen Total
            List<DokumenTotalModel> listDate = dokumenTotalDb.findAll();
            // Ubah bentuknya jadi YearMonth
            List<YearMonth> yearMonths = new ArrayList<>();
            for(DokumenTotalModel date : listDate){
                Date dateFormat = date.getTanggalIsu();
                YearMonth yearMonth = YearMonth.from(dateFormat.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());
                yearMonths.add(yearMonth);
            }
            // Cari berdasarkan YearMonth
            if(yearMonths.contains(now)){
                check =1;
            }
        } catch(Exception e){

        }
        if(check==0){
            return false;
        } else{

            return true;
        }
    }

    @Override
    public void makeDokumenTotal(DokumenTotalModel dokumenBaru, Date date){
        dokumenBaru.setTotalAnggaran(0);
        dokumenBaru.setId_status(statusDb.findByStatus("Menunggu Pengajuan"));
        dokumenBaru.setTanggalIsu(date);
        dokumenTotalDb.save(dokumenBaru);

        List<KaryawanModel> karyawanList = karyawanDb.findAll();
        for(KaryawanModel karyawan:karyawanList){
            GajiModel gaji = new GajiModel();
            makeGajiKaryawan(gaji, karyawan, dokumenBaru);
        }
    }

    public void makeGajiKaryawan(GajiModel gaji, KaryawanModel karyawan, DokumenTotalModel dokumen){
        KomponenPengaliModel komponenGaji = komponenPengaliDb.findById(Long.parseLong("1")).get();
        List<LemburModel> lemburList = karyawan.getListLembur();
        gaji.setUnit(karyawan.getUnit());

        //Perincian Gaji
        gaji.setGajiPokok(golonganGetter(karyawan.getGolongan()));
        gaji.setTunjanganJabatan(0);
        gaji.setTunjanganFungsional(0);
        gaji.setTunjanganTransport(0);
        gaji.setTunjanganKeluarga(komponenGaji.getKeluarga());
        gaji.setTunjanganKesehatan(0);
        gaji.setTunjanganPerintis(0);
        gaji.setTotalFixCost(gaji.getGajiPokok()+gaji.getTunjanganJabatan()
                +gaji.getTunjanganFungsional()+gaji.getTunjanganKeluarga()
                +gaji.getTunjanganKesehatan()+gaji.getTunjanganPerintis());

        //TLP,TP,Infal
        gaji.settPPH(komponenGaji.gettPPH());
        gaji.setUangSnack(komponenGaji.getUangSnack());
        gaji.setTunjanganPrestasi(komponenGaji.getTunjanganPrestasi());
        gaji.setTotalLembur(lemburCounter(lemburList, komponenGaji));
        gaji.setTotalInfal(0);
        gaji.setTotalVarCost((int)(gaji.gettPPH()+gaji.getUangSnack()
                +gaji.getTunjanganPrestasi()+gaji.getTotalLembur()
                +gaji.getTotalInfal()));

        //Potongan
        gaji.setAngsuranBank(0);
        gaji.setAngsuranKopeg(0);
        gaji.setAngsuranYayasan(0);
        gaji.setPotonganAbsensi(0);
        gaji.setPotonganPPH((int)(float)(komponenGaji.getPPH()));
        gaji.setTotalPotongan(gaji.getAngsuranBank()+gaji.getAngsuranKopeg()
                +gaji.getAngsuranYayasan()+gaji.getPotonganAbsensi()
                +gaji.getPotonganPPH());
        gaji.setTHPA(gaji.getTotalFixCost()+gaji.getTotalVarCost()-gaji.getTotalPotongan());
        gaji.setTHPB(gaji.getTotalFixCost()+gaji.getTotalVarCost()-gaji.getTotalPotongan());

        gaji.setDokumen(dokumen);
        gaji.setId_karyawan(karyawan);
        karyawan.setGaji((long)gaji.getTHPA());
        gajiDb.save(gaji);
        karyawan.getGajiModel().add(gaji);
    }

    @Override
    public void updateDokumenTotal(DokumenTotalModel dokumen){
        List<GajiModel> gajiList= gajiDb.findByDokumen(dokumen);
        Integer anggaranTotal;
        anggaranTotal = 0;
        for(GajiModel gaji:gajiList){
            anggaranTotal += gaji.getTHPA();
        }
        dokumen.setTotalAnggaran(anggaranTotal);
        dokumenTotalDb.save(dokumen);
    }

    @Override
   public void statusToAjukan(DokumenTotalModel dokumenSubmit){
        dokumenSubmit.setId_status(statusDb.findByStatus("Menunggu Persetujuan"));
        dokumenTotalDb.save(dokumenSubmit);
    }

    @Override
    public List<List<Integer>> totalPerDokPerUnit(){
        List<DokumenTotalModel> dokumenList = dokumenTotalDb.findAll();
        List<List<Integer>> ret = new ArrayList<>();
        for(DokumenTotalModel dokumen:dokumenList){
            List<Integer> totalList = new ArrayList<>();
            Integer totalTK = totalAnggaranUnit("TKIT", dokumen);
            totalList.add(totalTK);
            Integer totalSD = totalAnggaranUnit("SDIT", dokumen);
            totalList.add(totalSD);
            Integer totalSMP = totalAnggaranUnit("SMPIT", dokumen);
            totalList.add(totalSMP);
            Integer totalSU = totalAnggaranUnit("SU", dokumen);
            totalList.add(totalSU);
            ret.add(totalList);
        }
        return ret;
    }

    @Override
    public void simpanGajiKaryawan(GajiModel gaji, KaryawanModel karyawan, DokumenTotalModel dokumen){
        GajiModel targetGaji= gajiDb.findById(gaji.getId()).get();
        System.out.println(targetGaji.getId());
//        targetGaji.setUnit(karyawan.getUnit());
        try{
            //Perincian Gaji
            targetGaji.setGajiPokok(gaji.getGajiPokok());
            targetGaji.setTunjanganJabatan(gaji.getTunjanganJabatan());
            targetGaji.setTunjanganFungsional(gaji.getTunjanganFungsional());
            targetGaji.setTunjanganTransport(gaji.getTunjanganTransport());
            targetGaji.setTunjanganKeluarga(gaji.getTunjanganKeluarga());
            targetGaji.setTunjanganKesehatan(gaji.getTunjanganKesehatan());
            targetGaji.setTunjanganPerintis(gaji.getTunjanganPerintis());
            targetGaji.setTotalFixCost(targetGaji.getGajiPokok()+targetGaji.getTunjanganJabatan()
                    +targetGaji.getTunjanganFungsional()+targetGaji.getTunjanganKeluarga()
                    +targetGaji.getTunjanganKesehatan()+targetGaji.getTunjanganPerintis());

            //TLP,TP,Infal
            targetGaji.settPPH(gaji.gettPPH());
            targetGaji.setUangSnack(gaji.getUangSnack());
            targetGaji.setTunjanganPrestasi(gaji.getTunjanganPrestasi());
            targetGaji.setTotalLembur(gaji.getTotalLembur());
            targetGaji.setTotalInfal(gaji.getTotalInfal());
            targetGaji.setTotalVarCost((int)(targetGaji.gettPPH()+targetGaji.getUangSnack()
                    +targetGaji.getTunjanganPrestasi()+targetGaji.getTotalLembur()
                    +targetGaji.getTotalInfal()));

            //Potongan
            targetGaji.setAngsuranBank(gaji.getAngsuranBank());
            targetGaji.setAngsuranKopeg(gaji.getAngsuranKopeg());
            targetGaji.setAngsuranYayasan(gaji.getAngsuranYayasan());
            targetGaji.setPotonganAbsensi(gaji.getPotonganAbsensi());
            targetGaji.setPotonganPPH(gaji.getPotonganPPH());
            targetGaji.setTotalPotongan(targetGaji.getAngsuranBank()+targetGaji.getAngsuranKopeg()
                    +targetGaji.getAngsuranYayasan()+targetGaji.getPotonganAbsensi()
                    +targetGaji.getPotonganPPH());
            targetGaji.setTHPA(targetGaji.getTotalFixCost()+targetGaji.getTotalVarCost()-targetGaji.getTotalPotongan());
            targetGaji.setTHPB(targetGaji.getTotalFixCost()+targetGaji.getTotalVarCost()-targetGaji.getTotalPotongan());
            targetGaji.setDokumen(dokumen);
            targetGaji.setId_karyawan(karyawan);
            karyawan.setGaji((long)targetGaji.getTHPB());
            gajiDb.save(targetGaji);
        }catch(NullPointerException nullException){

        }

    }

    public Integer totalAnggaranUnit(String unit, DokumenTotalModel dokumen){
        List<GajiModel> gajiList = gajiDb.findByUnitAndDokumen(unit,dokumen);
        Integer ret;
        ret=0;
        for(GajiModel gaji : gajiList){
            ret += gaji.getTHPA();
        }
        return ret;
    }


    public Integer lemburCounter(List<LemburModel> lemburList, KomponenPengaliModel komponenGaji) {
        Integer counter;
        counter=0;
        if (!lemburList.isEmpty()) {
            for (LemburModel lembur : lemburList) {
                Calendar jamMulai = Calendar.getInstance();
                jamMulai.setTime(lembur.getJamMulai());
                Calendar jamSelesai = Calendar.getInstance();
                jamSelesai.setTime(lembur.getJamSelesai());
                Integer totalMenit = ((jamSelesai.get(Calendar.HOUR_OF_DAY) * 60) + (jamSelesai.get(Calendar.MINUTE)))
                        - (jamMulai.get(Calendar.HOUR_OF_DAY) * 60) + (jamMulai.get(Calendar.MINUTE));
                Integer totalJam = Math.floorDiv(totalMenit, 60);
                counter += (int) (totalJam * (komponenGaji.getLembur()));
            }
        }
        return counter;
    }

    public Integer golonganGetter(Long gol){
        int finGol;
        KomponenPengaliModel komponenGaji = komponenPengaliDb.findById(Long.parseLong("1")).get();
        if(gol == 1){
            finGol=komponenGaji.getGol1AC();
        } else if(gol == 2){
            finGol=komponenGaji.getGol1D2A();
        }else if(gol == 3) {
            finGol = komponenGaji.getGol2B();
        }else if(gol == 4) {
            finGol = komponenGaji.getGol2CD();
        }else if(gol == 5) {
            finGol = komponenGaji.getGol2B();
        }else if(gol == 6) {
            finGol = komponenGaji.getGol3A();
        }else if(gol == 7) {
            finGol = komponenGaji.getGol3BC();
        }else if(gol == 8) {
            finGol = komponenGaji.getGol3D4AB();
        }else if(gol == 9) {
            finGol = komponenGaji.getGol4CD();
        }else{
            finGol = komponenGaji.getGol5AD();
        }
        return finGol;
    }

    public Date YMtoDate(YearMonth now){
        final Date date =
                Date.from(now.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

    public YearMonth datetoYM(Date date){
        YearMonth yearMonth =
                YearMonth.from(date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
        return yearMonth;
    }

    public DokumenTotalModel getDokumenByTanggal_Isu(Date tanggalIsu) throws NoSuchElementException{
        return dokumenTotalDb.findDokumenTotalByTanggalIsu(tanggalIsu).get();
    }

    public List<String> displayYearMonth(){
        List<DokumenTotalModel> dokumenList = dokumenTotalDb.findAll();
        List<String> yearMonths = new ArrayList<>();
        for(DokumenTotalModel dokumen : dokumenList){
            Date date = dokumen.getTanggalIsu();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String yearMonth = localDate.getMonth() + " - " + localDate.getYear();
            yearMonths.add(yearMonth);
        }
        return yearMonths;
    }

    public String displayYearMonth(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String yearMonth = localDate.getMonth() + " - " + localDate.getYear();
        return yearMonth;
    }

    @Override
    public UserModel findUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDb.findByUsername(username);
    }

    @Override
    public  void statusToMenungguAkses(DokumenTotalModel dokumenSubmit){
        dokumenSubmit.setId_status(statusDb.findByStatus("Menunggu Akses"));
        dokumenTotalDb.save(dokumenSubmit);
    }

    @Override
    public void statusToAksesDiberikan(DokumenTotalModel dokumenSubmit){
        dokumenSubmit.setId_status(statusDb.findByStatus("Akses Diberikan"));
        dokumenTotalDb.save(dokumenSubmit);
    }

    @Override
    public void statusToDisetujui(DokumenTotalModel dokumenSubmit){
        dokumenSubmit.setId_status(statusDb.findByStatus("Disetujui"));
        dokumenTotalDb.save(dokumenSubmit);
    }

    @Override
    public String displayStatus(Integer status){
        String ret;
        if(status==1){
            ret="Tetap";
        }else if(status==2){
            ret="Kontrak";
        }else{
            ret="Tenaga Ahli";
        }
        return ret;
    }


}
