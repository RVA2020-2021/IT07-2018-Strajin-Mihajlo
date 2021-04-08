package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Klijent;
import rva.repository.KlijentRepository;

@CrossOrigin // Da se povezu 2 domena, sa frontenda i bekenda
@RestController
@Api(tags = {"Klijent CRUD operacije"})
public class KlijentRestController {
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("klijent")
	@ApiOperation(value = "Vraca kolekciju svih klijenata iz baze podataka")
	public Collection<Klijent> getKlijenti(){
		return klijentRepository.findAll();
	}
	
	@GetMapping("klijent/{id}")
	@ApiOperation(value = "Vraca klijenta u odnosu na prosledjenu vrednost path varijable id")
	public Klijent getKlijent(@PathVariable("id") Integer id) {
		return klijentRepository.getOne(id);
	}
	
	@GetMapping("klijentBrojLk/{brojLk}") 
	@ApiOperation(value = "Vraca kolekciju klijenata koji imaju brojLk koji sadrzi vrednost u okviru path varijable brojLk")
	public Collection<Klijent> getKlijentByBrojLk(@PathVariable("brojLk") Integer brojLk){
		return klijentRepository.findByBrojLk(brojLk);
	}
	
	@PostMapping("klijent")
	@ApiOperation(value = "Dodaje novog klijenta u bazu podataka")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent){
		if(!klijentRepository.existsById(klijent.getId())) {
			klijentRepository.save(klijent);
			return new ResponseEntity<Klijent>(HttpStatus.OK);
		}
		return new ResponseEntity<Klijent>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("klijent")
	@ApiOperation(value = "Update-uje postojeceg klijenta")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent){
		if(!klijentRepository.existsById(klijent.getId())) {
			return new ResponseEntity<Klijent>(HttpStatus.NO_CONTENT);
		}
		klijentRepository.save(klijent);
		return new ResponseEntity<Klijent>(HttpStatus.OK); 
	}
	
	@Transactional //Mora se izvrsiti u celosti
	@DeleteMapping("klijent/{id}")
	@ApiOperation(value = "Brise klijenta u odnosu na vrednost prosledjene path varijable id")
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable("id") Integer id){
		if(!klijentRepository.existsById(id)) {
			return new ResponseEntity<Klijent>(HttpStatus.NO_CONTENT);
		}
		jdbcTemplate.execute("DELETE FROM racun WHERE klijent=" + id);//Kaskadno brisanje
		klijentRepository.deleteById(id);
		klijentRepository.flush();
		if(id==-100) {
			jdbcTemplate.execute("INSERT INTO \"klijent\" (\"id\", \"ime\", \"prezime\", \"broj_lk\", \"kredit\") "
					+ "VALUES (-100, 'Test', 'Test', 0, 1)");
		}
		return new ResponseEntity<Klijent>(HttpStatus.OK);
	}
}
