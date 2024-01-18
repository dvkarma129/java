package hospitalMangementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospitalMangementSystem.Entity.History;
import hospitalMangementSystem.service.HistoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/history")
public class HistoryController {

	private HistoryService historyService;
	
	@GetMapping
	public ResponseEntity<List<History>> getAllHistorys(){
		List<History> historyList = historyService.getAllHistorys();
		return new ResponseEntity<>(historyList,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getHistory(@PathVariable long id) throws Exception{
		Optional<History> history = historyService.getHistory(id);
		return new ResponseEntity<>(history,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addHistory(@RequestBody History model) throws Exception{
		History history = historyService.addHistory(model);
		return new ResponseEntity<>(history,HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeHistory(@PathVariable long id) throws Exception{
		historyService.removeHistory(id);
		return new ResponseEntity<>("History deleted successfully",HttpStatus.OK);
	}
}
