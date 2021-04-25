package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.PengajuanCutiModel;

import java.util.List;
import java.util.Optional;

public interface PengajuanCutiService {
    List<PengajuanCutiModel> getCutiList();
    Optional<PengajuanCutiModel> getCutiById(Long id);
    void addCuti(PengajuanCutiModel cuti);
    PengajuanCutiModel editCuti(PengajuanCutiModel cuti);
    void deleteCuti(PengajuanCutiModel cuti);
    String generateKodeCuti(PengajuanCutiModel cuti);
    Integer generateDurasi(PengajuanCutiModel cuti);
}
