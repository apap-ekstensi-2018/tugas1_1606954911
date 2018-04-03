package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.FakultasModel;
import com.example.model.ProgramStudiModel;
@Mapper
public interface FakultasMapper {
	@Select("select F.nama_fakultas from mahasiswa M, program_studi PS, fakultas F WHERE M.id_prodi = PS.id AND PS.id_fakultas = F.id AND M.npm = #{npm}")
	FakultasModel selectFakultasNPM (@Param("npm") String npm);
	
	@Select("select * from fakultas")
    List<FakultasModel> selectAllFak();
	
	@Select("select * from fakultas where id_univ = #{id_univ}")
    List<FakultasModel> selectAllFakUniv(@Param("id_univ") int kode_univ);
	
	@Select("select *from fakultas where id= #{id}")
	FakultasModel selectFakID(@Param("id") int id_fak);
	
	@Select("select id from fakultas where id= #{id}")
	int selectIdFak(@Param("id") int id_fak);

}
