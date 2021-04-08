package rva.ctrls;

import java.util.Collection;

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
import rva.jpa.Kredit;
import rva.jpa.Racun;
import rva.repository.KlijentRepository;
import rva.repository.RacunRepository;

@CrossOrigin
@RestController
@Api(tags = {"Racun CRUD operacije"})
public class RacunRestController {
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired KlijentRepository klijentRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("racun")
	@ApiOperation(value = "Vraca kolekciju svih racuna iz baze podataka")
	public Collection<Racun> getRacuni(){
		return racunRepository.findAll();
	}
	
	@GetMapping("racun/{id}")
	@ApiOperation(value = "Vraca racun u odnosu na prosledjenu vrednost path varijable id")
	public Racun getRacun(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}
	
	@GetMapping("racuniZaKlijenta/{id}")
	@ApiOperation(value = "Vraca kolekciju racuna za klijenta koji ima id koji sadrzi vrednost u okviru path varijable id")
	public Collection<Racun> getRacuniZaKlijenta (@PathVariable("id") Integer id) { //ovaj id se odnosi na klijenta
		Klijent k = klijentRepository.getOne(id);
		return racunRepository.findByKlijent(k);
	}
	
	@GetMapping("racunNaziv/{naziv}")
	@ApiOperation(value = "Vraca kolekciju racuna koji imaju naziv koji sadrzi vrednost u okviru path varijable naziv")
	public Collection<Racun> getRacunByNaziv (@PathVariable("naziv") String naziv){
		return racunRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("racun")
	@ApiOperation(value = "Dodaje novi racun u bazu podataka")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) { // proveravam da li vec postoji
			racunRepository.save(racun);
			return new ResponseEntity<Racun>(HttpStatus.OK);
		}
		return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("racun")
	@ApiOperation(value = "Update-uje postojeci racun")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) { // proveravam da li vec postoji
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
			
		}
		racunRepository.save(racun);
		return new ResponseEntity<Racun>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("racun/{id}")
	@ApiOperation(value = "Brise racun u odnosu na vrednost prosledjene path varijable id")
	public ResponseEntity<Racun> deleteRacun(@PathVariable("id") Integer id){
		if(!racunRepository.existsById(id)) {
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
		}
		racunRepository.deleteById(id);
		if(id==-100) {
			jdbcTemplate.execute("INSERT INTO \"racun\" (\"id\", \"naziv\", \"oznaka\", \"opis\", \"tip_racuna\", \"klijent\") "
					+ "VALUES (-100, 'Test', 'Test', 'Test', 1, 1)");
		}
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}
}
