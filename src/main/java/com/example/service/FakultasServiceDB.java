package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;

import com.example.model.FakultasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceDB implements FakultasService {
	@Autowired
    private FakultasMapper fakMapper;

	@Override
	public FakultasModel selectFakultasNPM(String npm) {
		 log.info ("select Fakultas with npm {}", npm);
	     return fakMapper.selectFakultasNPM (npm);
		
	}

	@Override
	public List<FakultasModel> selectAllFakultas() {
		log.info ("select all students");
        return fakMapper.selectAllFak();
	}
	
	@Override
	public FakultasModel selectFakID(int idF) {
		 log.info ("select Fakultas with id {}", idF);
	     return fakMapper.selectFakID(idF);
		
	}
	
	@Override
	public List<FakultasModel> selectAllFakUniv(int kode_univ) {
		log.info ("select all students");
        return fakMapper.selectAllFakUniv(kode_univ);
	}
	
	@Override
	public int selectIdFak(int idF) {
		 log.info ("select Fakultas with id {}", idF);
	     return fakMapper.selectIdFak(idF);
		
	}

}
