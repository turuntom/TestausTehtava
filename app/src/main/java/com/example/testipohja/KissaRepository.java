package com.example.testipohja;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class KissaRepository {
    KissaDao kissaDao;
    LiveData<List<KissaEntity>> kissaEntityLiveData;

    public KissaRepository(Context applicationContext) {
        this.kissaDao = Tietokanta.getDatabase(applicationContext).kissaDao();
        this.kissaEntityLiveData = kissaDao.haeKaikki();
    }

    public boolean InsertKissa(KissaEntity k) {
        //nimen testaus
        if(k.nimi == null || k.nimi.isEmpty() || k.nimi.length() > 30){
            return false;
        }
        //iän testaus
        if(k.ika < 0 || k.ika > 40){
            return false;
        }
        if(k.omistaja == null || k.omistaja.isEmpty() || k.omistaja.length() > 50){
            return false;
        }

        kissaDao.LaitaKissa(k);
        return true;
    }

    public boolean HaeParametreilla(String nimi, int lkm) {
        if(nimi.isEmpty() || nimi == null || lkm < 1 || nimi.length() > 30){
            return false;
        }
        kissaDao.haeNimella(nimi, lkm);
        return true;
    }
}
