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
import rva.jpa.Kredit;
import rva.repository.KreditRepository;

@CrossOrigin
@RestController
@Api(tags = {"Kredit CRUD operacije"})
public class KreditRestController {

	@Autowired //kreira bin koji se injektuje i tako omogucavamo dependencyInjection
	private KreditRepository kreditRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("kredit")
	@ApiOperation(value = "Vraca kolekciju svih kredita iz baze podataka")
	public Collection<Kredit> getKrediti() {
		return kreditRepository.findAll(); //vraca sve kredite iz baze
	}
	
	@GetMapping("kredit/{id}")
	@ApiOperation(value = "Vraca kredit u odnosu na prosledjenu vrednost path varijable id")
	public Kredit getKredit(@PathVariable("id") Integer id) { //vraca samo jedan kredit
		return kreditRepository.getOne(id);
	}
	
	@GetMapping("kreditNaziv/{naziv}")
	@ApiOperation(value = "Vraca kolekciju kredita koji imaju naziv koji sadrzi vrednost u okviru path varijable naziv")
	public Collection<Kredit> getKreditByNaziv(@PathVariable("naziv") String naziv) {
		return kreditRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("kredit")
	@ApiOperation(value = "Dodaje novi kredit u bazu podataka")
	public ResponseEntity<Kredit> insertKredit(@RequestBody Kredit kredit){
		if(!kreditRepository.existsById(kredit.getId())) { // proveravam da li vec postoji
			kreditRepository.save(kredit);
			return new ResponseEntity<Kredit>(HttpStatus.OK);
		}
		return new ResponseEntity<Kredit>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("kredit")
	@ApiOperation(value = "Update-uje postojeci kredit")
	public ResponseEntity<Kredit> updateKredit(@RequestBody Kredit kredit){
		if(!kreditRepository.existsById(kredit.getId())) {
			return new ResponseEntity<Kredit>(HttpStatus.NO_CONTENT);
		}
		kreditRepository.save(kredit);
		return new ResponseEntity<Kredit>(HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping("kredit/{id}")
	@ApiOperation(value = "Brise kredit u odnosu na vrednost prosledjene path varijable id")
	public ResponseEntity<Kredit> deleteKredit(@PathVariable("id") Integer id){
		if(!kreditRepository.existsById(id)) {
			return new ResponseEntity<Kredit>(HttpStatus.NO_CONTENT);
		}
		jdbcTemplate.execute("delete from racun where klijent in (select id from klijent where kredit = " +id+")");
		jdbcTemplate.execute("DELETE FROM klijent WHERE kredit=" + id);//Kaskadno brisanje
		kreditRepository.deleteById(id);
		kreditRepository.flush();
		if(id==-100) {
			jdbcTemplate.execute("INSERT INTO \"kredit\" (\"id\", \"naziv\", \"opis\", \"oznaka\") "
					+ "VALUES (-100, 'Test', 'Test', 'Test')");
		}
		return new ResponseEntity<Kredit>(HttpStatus.OK);
	}
}
