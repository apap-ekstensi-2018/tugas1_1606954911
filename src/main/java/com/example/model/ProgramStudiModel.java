package com.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramStudiModel {
	private Integer id;
	private Integer kode_prodi;
    private String nama_prodi;
    private Integer id_fakultas;
    //private FakultasModel fakultas;
}
