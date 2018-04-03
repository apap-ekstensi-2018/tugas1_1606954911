package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;


public interface FakultasService {
	FakultasModel selectFakultasNPM(String npm);
	
	List<FakultasModel> selectAllFakultas ();
	
	FakultasModel selectFakID(int idF);
	
	List<FakultasModel> selectAllFakUniv (int kode_univ);
	
	int selectIdFak(int idF);
}
