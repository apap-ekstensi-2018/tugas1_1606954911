package com.example.service;

import java.util.List;


import com.example.model.ProgramStudiModel;

public interface ProdiService {
	ProgramStudiModel selectProdiNPM(String npm);
	
	List<ProgramStudiModel> selectAllProdi();
	
	ProgramStudiModel selectProdiID(int idProdi);
	
	List<ProgramStudiModel> selectAllFakUniv(int idFak);
	
	List<ProgramStudiModel>selectProdi(int idProdi);
}
