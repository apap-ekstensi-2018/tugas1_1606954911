package com.example.service;

import java.util.List;

import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;

public interface MahasiswaService
{
    
   
    
    void addMahasiswa(MahasiswaModel mahasiswa);
    
    void updateMahasiswa(MahasiswaModel mahasiswa);

    String selectNPMLast(String npm);
    
    Float selectCountLulusbyID(String tahun_masuk,int id_prodi);
    
    Float selectCountTidakLulusbyID(String tahun_masuk,int id_prodi);
    
    int selectCountMhsbyID(int id_prodi);

	MahasiswaModel selectMahasiswa(String npm);
	
	//void selectProdi (int id_prodi);
	
	MahasiswaModel selectMahasiswaUpdate(String npm);
	
	List<MahasiswaModel> selectMahasiswaProdi(int id_prodi);
	
	
    
    //void updateStudent (MahasiswaModel student);
}
