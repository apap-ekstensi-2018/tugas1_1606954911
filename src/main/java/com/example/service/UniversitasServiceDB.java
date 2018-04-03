package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.dao.UniversitasMapper;

import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UniversitasServiceDB implements UniversitasService {
	@Autowired
    private UniversitasMapper univMapper;
	
	@Override
	public UniversitasModel selectUnivNPM(String npm) {
		log.info ("select universitas with npm {}", npm);
	     return univMapper.selectUnivNPM(npm);
	}

	@Override
	public List<UniversitasModel> selectAllUniv() {
		log.info ("select all students");
        return univMapper.selectAllUniv();
	}
	
	@Override
	public UniversitasModel selectUnivID(int id_univ) {
		log.info ("select mahasiswa with npm {}", id_univ);
	     return univMapper.selectUnivID(id_univ);
	}
	
	public int selectUnivId (int id_univ) {
		log.info ("select mahasiswa with npm {}", id_univ);
	     return univMapper.selectUnivId(id_univ);
	}

}
