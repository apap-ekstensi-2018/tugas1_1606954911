package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService
{
    @Autowired
    private MahasiswaMapper studentMapper;


    @Override
    public MahasiswaModel selectMahasiswaUpdate (String npm)
    {
        log.info ("select mahasiswa with npm {}", npm);
        return studentMapper.selectMahasiswaUpdate (npm);
    }
    
    public MahasiswaModel selectMahasiswa (String npm)
    {
        log.info ("select mahasiswa with npm {}", npm);
        return studentMapper.selectMahasiswa (npm);
    }

    public List<MahasiswaModel> selectMahasiswaProdi (int id_prodi)
    {
        log.info ("select mahasiswa with id_prodi {}", id_prodi);
        return studentMapper.selectMahasiswaProdi (id_prodi);
    }
   
    
    @Override
    public String selectNPMLast (String npm)
    {
    		log.info("student " + npm + "last");
    		return studentMapper.selectNPMlast(npm);
    }
    
    @Override
    public Float selectCountLulusbyID (String tahun_masuk,int id_prodi)
    {
    		//log.info("student " + npm + "last");
    		return studentMapper.selectCountLulusbyID(tahun_masuk,id_prodi);
    }
    
    @Override
    public Float selectCountTidakLulusbyID (String tahun_masuk,int id_prodi)
    {
    		//log.info("student " + npm + "last");
    		return studentMapper.selectCountTidakLulusbyID(tahun_masuk,id_prodi);
    }
    
    @Override
    public int selectCountMhsbyID (int id_prodi)
    {
    		//log.info("student " + npm + "last");
    		return studentMapper.selectCountMhsbyID(id_prodi);
    }
    
    
    @Override
    public void addMahasiswa (MahasiswaModel mahasiswa)
    {
    		log.info("student " + mahasiswa + "last");
    		studentMapper.addMahasiswa(mahasiswa);
    }
    
    @Override
    public void updateMahasiswa (MahasiswaModel mahasiswa)
    {
    		log.info("student " + mahasiswa + "last");
    		studentMapper.updateMahasiswa(mahasiswa);
    }
    
    
   
    
    
   

}
