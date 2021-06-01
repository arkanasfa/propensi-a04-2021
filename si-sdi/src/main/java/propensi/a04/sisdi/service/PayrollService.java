package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.*;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

public interface PayrollService {
    //KomponenPengaliDb
    KomponenPengaliModel getKomponenGajiById(Long komponenGajiId);
    KomponenPengaliModel ubahKomponenGaji(KomponenPengaliModel komponenGaji);


    //DokumenTotalDb
    boolean compareDokumenTotalByTanggalIsu(YearMonth now);
    void statusToAjukan(DokumenTotalModel dokumenSubmit);
    void makeDokumenTotal(DokumenTotalModel dokumenBaru, Date date);
    void updateDokumenTotal(DokumenTotalModel dokumenBaru);
    List<List<Integer>> totalPerDokPerUnit();
    Integer totalAnggaranUnit(String unit, DokumenTotalModel dokumen);
    void simpanGajiKaryawan(GajiModel gaji, KaryawanModel karyawan, DokumenTotalModel dokumen);
    Date YMtoDate(YearMonth now);
    YearMonth datetoYM(Date date);
    DokumenTotalModel getDokumenByTanggal_Isu(Date tanggalIsu);
    List<String> displayYearMonth();
    String displayYearMonth(Date date);
    void statusToMenungguAkses(DokumenTotalModel dokumenSubmit);
    void statusToAksesDiberikan(DokumenTotalModel dokumenSubmit);
    void statusToDisetujui(DokumenTotalModel dokumenSubmit);

    //User
    UserModel findUser();

    //DetailGaji
    String displayStatus(Integer status);

}
