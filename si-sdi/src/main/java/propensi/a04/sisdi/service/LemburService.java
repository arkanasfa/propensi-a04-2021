package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.LemburModel;

import java.util.List;

public interface LemburService {
    void addLembur(LemburModel lembur);
    LemburModel changeLembur(LemburModel lembur);
    List<LemburModel> getLemburList();
    String generateKodeLembur(LemburModel lembur);
    Integer generateDurasi(LemburModel lembur);
    LemburModel getLemburById(Long lemburId);
    void deleteLembur(LemburModel lembur);
}
