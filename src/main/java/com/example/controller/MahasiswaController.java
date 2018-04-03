package com.example.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;
import com.example.service.FakultasService;
import com.example.service.ProdiService;
import com.example.service.MahasiswaService;
import com.example.service.UniversitasService;
@Controller
public class MahasiswaController
{
    @Autowired
    MahasiswaService mahasiswaDAO;
    @Autowired
    ProdiService prodiDAO;
    @Autowired
    UniversitasService univDAO;
    @Autowired
    FakultasService fakDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
        model.addAttribute("title","home");
    		return "index";
    }


    @RequestMapping("/mahasiswa/tambah")
    public String add (@ModelAttribute("mahasiswa") MahasiswaModel mahasiswa, Model model)
    {
    		if (mahasiswa.getNama()==null) {
    			model.addAttribute("title","Menambah Mahasiswa");
        		return "form-add";
    		}else {
    			int idProdi = mahasiswa.getId_prodi();
    			ProgramStudiModel prodiU = prodiDAO.selectProdiID(idProdi);
    			if(prodiU==null) {
    				model.addAttribute("title","Id Prodi not found");
    				return "idProdi-not-found";
    			}else {
    			mahasiswa.setStatus("Aktif");
    			mahasiswa.setNpm(getNPM(mahasiswa));
    			model.addAttribute("npm", mahasiswa.getNpm());
    			//MahasiswaModel mahasiswaB = new MahasiswaModel(mahasiswa);
    			mahasiswaDAO.addMahasiswa(mahasiswa);
    			model.addAttribute("title","Menambah Mahasiswa");
    			return "success-add";
    			}
    		}
    		
    }
    
    @RequestMapping("/mahasiswa/cari")
    public String listUniv (Model model,
    		@RequestParam(value = "univ", required = false) String univ,
    		@RequestParam(value = "idfakultas", required = false) String idfakultas,
    		@RequestParam(value = "idprodi", required = false) String idprodi)
    {
    		List<UniversitasModel> universitas = univDAO.selectAllUniv();
		model.addAttribute("universitas", universitas);
		
    		 
    		if(univ==null) {
    			model.addAttribute("title","Cari Universitas");
    			return "cari-universitas";
    			
    		}else {
    			int idUn = Integer.parseInt(univ);
    			UniversitasModel univs = univDAO.selectUnivID(idUn);
    			int idU = univDAO.selectUnivId(idUn);
    			List<FakultasModel> fakultas = fakDAO.selectAllFakUniv(idU);
    			
    			if (idfakultas==null) {
        			model.addAttribute("fakultas", fakultas);
        			model.addAttribute("selectUniv", idUn);
        			model.addAttribute("title","Cari Fakultas");
        			return "cari-fakultas";
    			}else { 
    				int idF = Integer.parseInt(idfakultas);
    				FakultasModel faks = fakDAO.selectFakID(idF);
    				int idFak = fakDAO.selectIdFak(idF);
				List<ProgramStudiModel> prodis = prodiDAO.selectAllFakUniv(idFak);
				
    				if(idprodi==null) {
    					model.addAttribute("fakultas", fakultas);
    					model.addAttribute("prodi", prodis);
    					model.addAttribute("selectFak", idF);
    					model.addAttribute("selectUniv", idUn);
    					model.addAttribute("title","Cari Prodi");
            			return "cari-prodi";
    				}else {
    					int idP = Integer.parseInt(idprodi);
    					List<MahasiswaModel> mhs = mahasiswaDAO.selectMahasiswaProdi(idP);
    					ProgramStudiModel pro = prodiDAO.selectProdiID(idP);
    					if (mhs!=null) {
    						model.addAttribute("mahasiswa",mhs);
        					model.addAttribute("prodi", pro);
        					model.addAttribute("title","Data Mahasiswa");
        					return "data-mahasiswa";
    					}

    				}
    				
    				
    			}
    		}
    
    		return "cari-universitas";
    }
    @RequestMapping("/kelulusan")
    public String kelulusan (Model model,
    		@RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
    		@RequestParam(value = "id_prodi", required = false) String id_prodi)
    {
    	 	 //MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa (npm);
    		 if(tahun_masuk==null && id_prodi==null) {
    			 model.addAttribute("title","Lihat Kelulusan");
         	 return "kelulusan";
    		 }else {
    			 int idProdi = Integer.parseInt(id_prodi);
     			ProgramStudiModel prodiU = prodiDAO.selectProdiID(idProdi);
     			if(prodiU==null) {
     				model.addAttribute("title","Id Prodi not found");
     				return "idProdi-not-found";
     			}else {
		    			 int idP =Integer.parseInt(id_prodi);
		    	         ProgramStudiModel prodi = prodiDAO.selectProdiID(idP);
		    	         FakultasModel fakultas = fakDAO.selectFakID(prodi.getId_fakultas());
		    	         UniversitasModel univ = univDAO.selectUnivID(fakultas.getId_univ());
		    	         
		    	         float hasil = (mahasiswaDAO.selectCountLulusbyID(tahun_masuk, idP)/mahasiswaDAO.selectCountMhsbyID(idP))*100;
		    	         int countMhsId = mahasiswaDAO.selectCountMhsbyID(idP);
		    	         float hasilLulus = mahasiswaDAO.selectCountLulusbyID(tahun_masuk, idP);
		    	         int hasilP = (int) hasil;
		    	         int hasilL = (int) hasilLulus;
		    	         model.addAttribute("tahun_masuk",tahun_masuk);
		    	         model.addAttribute("nama_prodi", prodi.getNama_prodi());
		    	         model.addAttribute("nama_fakultas", fakultas.getNama_fakultas());
		    	         model.addAttribute("nama_univ", univ.getNama_univ());
		    	         model.addAttribute("hasil",hasilP);
		    	         model.addAttribute("hasilLulus",hasilL);
		    	         model.addAttribute("hasilM",countMhsId);
		    	         model.addAttribute("title","Lihat Kelulusan");
		    	         
		    	         return "view-kelulusan";
     			}
    		 }
    		 
    		
    }
    public String getNPM(MahasiswaModel mahasiswa) {
    		String kdJalurMasuk="";
    		if (mahasiswa.getJalur_masuk().equals("Undangan Olimpiade")) {
    			kdJalurMasuk="53";
    		}else if(mahasiswa.getJalur_masuk().equals("Undangan Reguler/SNMPTN")) {
    			kdJalurMasuk="54";
    		}else if(mahasiswa.getJalur_masuk().equals("Undangan Paralel/PPKB")) {
    			kdJalurMasuk="55";
    		}else if(mahasiswa.getJalur_masuk().equals("Ujian Tulis Bersama/SBMPTN")) {
    			kdJalurMasuk="57";
    		}else if(mahasiswa.getJalur_masuk().equals("Ujian Tulis Mandiri")) {
    			kdJalurMasuk="62";
    		}
    		String thnMasuk=mahasiswa.getTahun_masuk().substring(2, 4);
    		ProgramStudiModel ps = prodiDAO.selectProdiID(mahasiswa.getId_prodi());
    		String kdProdi = String.valueOf(ps.getKode_prodi());
    		
    		FakultasModel fakultasData = fakDAO.selectFakID(ps.getId_fakultas());
    		UniversitasModel universitasKD = univDAO.selectUnivID(fakultasData.getId_univ());
    		String kduniv = universitasKD.getKode_univ();
    		
    		String npmIdentik = thnMasuk+kduniv+kdProdi+kdJalurMasuk;
    		String getNpmSama = mahasiswaDAO.selectNPMLast("%"+npmIdentik+"%");
    		
    		
    		String noUrut = "";
    		
    		
    		if (getNpmSama==null) {
    			noUrut = "001";
    		}else {
    			noUrut = String.valueOf(Integer.parseInt(getNpmSama.substring(9, 12))+1001).substring(1, 4);
    		}
    		
    		String npm = npmIdentik+noUrut;
    		
    		return npm;
    }




    @RequestMapping("/mahasiswa")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa (npm);
        ProgramStudiModel prodi = prodiDAO.selectProdiNPM(npm);
        UniversitasModel univ = univDAO.selectUnivNPM(npm);
        
        if (mahasiswa != null) {
            model.addAttribute ("mahasiswa", mahasiswa);
            model.addAttribute("prodi",prodi);
            model.addAttribute("univ",univ);
            model.addAttribute("title","Lihat Mahasiswa");
            return "lihatmhs";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title","Mahasiswa Tidak Ditemukan");
            return "not-found";
        }
    }


    
    @RequestMapping("/mahasiswa/ubah/{npm}")
    public String update (@PathVariable(value = "npm") String npm, Model model, @ModelAttribute("mahasiswa") MahasiswaModel cekmahasiswa)
    {	
    		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswaUpdate(npm);
    		
    			if (cekmahasiswa.getNama()==null) {
    				if(mahasiswa==null) {
    					model.addAttribute("npm", npm);
    					return "not-found";
    				}
    				model.addAttribute("mahasiswa",mahasiswa);
    	            model.addAttribute("title","Update Mahasiswa");
    	            return "form-update";
    			}else {
    				int idProdi = cekmahasiswa.getId_prodi();
        			ProgramStudiModel prodiU = prodiDAO.selectProdiID(idProdi);
        			if(prodiU==null) {
        				model.addAttribute("title","Id Prodi not found");
        				return "idProdi-not-found";
        			}else {
	    				if(mahasiswa.getTahun_masuk().equals(cekmahasiswa.getTahun_masuk()) && mahasiswa.getId_prodi()==cekmahasiswa.getId_prodi() && mahasiswa.getJalur_masuk().equals(cekmahasiswa.getJalur_masuk())) {
	    					cekmahasiswa.setNpm(mahasiswa.getNpm());
	    				}else {
	    					cekmahasiswa.setNpm(getNPM(cekmahasiswa));
	    				}
	        			mahasiswaDAO.updateMahasiswa(cekmahasiswa);
	        			model.addAttribute("npm", mahasiswa.getNpm());
	        			model.addAttribute("title","Update Mahasiswa");
	        			return "success-update";
	        			}
    			}
    			
        		
    } 
   

}
