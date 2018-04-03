package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;
import com.example.dao.ProdiMapper;
import com.example.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdiServiceDB implements ProdiService {
	
	@Autowired
    private ProdiMapper prodiMapper;

	@Override
	public ProgramStudiModel selectProdiNPM(String npm) {
		log.info ("select mahasiswa with npm {}", npm);
	     return prodiMapper.selectProdiNPM (npm);
	}

	@Override
	public List<ProgramStudiModel> selectAllProdi() {
		log.info ("select all students");
        return prodiMapper.selectAllProdi();
	}
	@Override
	public ProgramStudiModel selectProdiID(int id_prodi) {
		log.info ("select mahasiswa with npm {}", id_prodi);
	     return prodiMapper.selectProdiID (id_prodi);
	}
	
	@Override
	public List<ProgramStudiModel> selectAllFakUniv(int idFak) {
		log.info ("select all Prodi");
        return prodiMapper.selectAllFakUniv(idFak);
	}
	
	@Override
	public List<ProgramStudiModel> selectProdi(int id_prodi) {
		log.info ("select all Prodi");
        return prodiMapper.selectProdi(id_prodi);
	}
	
	

}
