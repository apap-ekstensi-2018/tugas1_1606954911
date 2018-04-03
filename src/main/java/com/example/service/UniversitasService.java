package com.example.service;

import java.util.List;

import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

public interface UniversitasService {
	UniversitasModel selectUnivNPM(String npm);
	
	List<UniversitasModel> selectAllUniv ();
	
	UniversitasModel selectUnivID(int idUniv);
	
	int selectUnivId(int idUniv);
}
