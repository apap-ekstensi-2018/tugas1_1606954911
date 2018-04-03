package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;

@Mapper
public interface MahasiswaMapper
{
	@Select("SELECT m.npm, m.nama, m.tempat_lahir, m.tanggal_lahir, ps.nama_prodi, f.nama_fakultas, u.nama_univ, m.jenis_kelamin, m.agama, m.golongan_darah, m.tahun_masuk, m.jalur_masuk, m.status FROM fakultas f, mahasiswa m, program_studi ps, universitas u WHERE m.npm = #{npm} AND m.id_prodi = ps.id AND ps.id_fakultas = f.id AND f.id_univ = u.id")
    MahasiswaModel selectMahasiswa (@Param("npm") String npm);

	@Select("SELECT * from mahasiswa where npm = #{npm}")
	MahasiswaModel selectMahasiswaUpdate (@Param("npm") String npm);
    
    //@Insert("select p.* from program_studi p where p.id = #{id_prodi}")
    //void selectProdi (@Param("id_prodi") int id_prodi);
    
    @Select("SELECT count(*) as lulus FROM mahasiswa WHERE status LIKE 'Lulus' AND tahun_masuk LIKE #{tahun_masuk} AND id_prodi = #{id_prodi}")
    Float selectCountLulusbyID(@Param("tahun_masuk") String tahun_masuk,@Param("id_prodi") int id_prodi);
    
    @Select("SELECT count(*) as tidak_lulus FROM mahasiswa WHERE status NOT LIKE 'Lulus' AND tahun_masuk LIKE #{tahun_masuk} AND id_prodi = #{id_prodi}")
    Float selectCountTidakLulusbyID(@Param("tahun_masuk") String tahun_masuk,@Param("id_prodi") int id_prodi);
    
    @Select("SELECT count(*) as mhs FROM mahasiswa WHERE id_prodi = #{id_prodi}")
    int selectCountMhsbyID(@Param("id_prodi") int id_prodi);
    
    @Update("UPDATE mahasiswa SET npm=#{npm}, nama=#{nama}, tempat_lahir=#{tempat_lahir}, tanggal_lahir=#{tanggal_lahir}, jenis_kelamin=#{jenis_kelamin}, agama=#{agama},status=#{status}, golongan_darah=#{golongan_darah}, tahun_masuk=#{tahun_masuk}, jalur_masuk=#{jalur_masuk}, id_prodi=#{id_prodi} WHERE id = #{id}")
    void updateMahasiswa (MahasiswaModel mahasiswa);
    
    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah,status, tahun_masuk, jalur_masuk, id_prodi) VALUES (#{npm},#{nama},#{tempat_lahir},#{tanggal_lahir},#{jenis_kelamin},#{agama},#{golongan_darah},#{status},#{tahun_masuk},#{jalur_masuk},#{id_prodi})")
    void addMahasiswa (MahasiswaModel mahasiswa);
    
    @Select("SELECT max(npm) FROM mahasiswa WHERE npm LIKE #{npm}")
    String selectNPMlast (@Param("npm") String npm);
    
    @Select("SELECT * FROM mahasiswa WHERE id_prodi =#{id_prodi}")
    List<MahasiswaModel> selectMahasiswaProdi (@Param("id_prodi") int id_prodi);
    
    

   
}
