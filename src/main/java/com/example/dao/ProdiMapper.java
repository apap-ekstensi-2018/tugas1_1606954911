package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.FakultasModel;
import com.example.model.ProgramStudiModel;

@Mapper
public interface ProdiMapper {
	@Select("select PS.nama_prodi from mahasiswa M, program_studi PS WHERE M.id_prodi = PS.id AND M.npm = #{npm}")
    ProgramStudiModel selectProdiNPM(@Param("npm") String npm);
	
	@Select("select * from program_studi")
    List<ProgramStudiModel> selectAllProdi();
	
	@Select("select *from program_studi where id= #{id_prodi}")
	ProgramStudiModel selectProdiID(@Param("id_prodi") int id_prodi);
	
	@Select("select *from program_studi where id= #{id_prodi}")
	List<ProgramStudiModel> selectProdi(@Param("id_prodi") int id_prodi);
	
	@Select("select * from program_studi where id_fakultas = #{id_fakultas}")
    List<ProgramStudiModel> selectAllFakUniv(@Param("id_fakultas") int id_fakultas);
}
