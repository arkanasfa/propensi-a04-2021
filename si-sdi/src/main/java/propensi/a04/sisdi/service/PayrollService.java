package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.DokumenTotalModel;
import propensi.a04.sisdi.model.GajiModel;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.KomponenPengaliModel;

import java.time.YearMonth;
import java.util.List;

public interface PayrollService {
    //KomponenPengaliDb
    KomponenPengaliModel getKomponenGajiById(Long komponenGajiId);
    KomponenPengaliModel ubahKomponenGaji(KomponenPengaliModel komponenGaji);


    //DokumenTotalDb
    boolean compareDokumenTotalByTanggalIsu(YearMonth now);
    void ajukanDokumenTotalGaji(DokumenTotalModel ajukan);
    void makeDokumenTotal(DokumenTotalModel dokumenBaru);
    void updateDokumenTotal(DokumenTotalModel dokumenBaru);
    List<List<Integer>> totalPerDokPerUnit();
    Integer totalAnggaranUnit(String unit, DokumenTotalModel dokumen);
    void simpanGajiKaryawan(GajiModel gaji, KaryawanModel karyawan, DokumenTotalModel dokumen);
}
