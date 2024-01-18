
package hospitalMangementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hospitalMangementSystem.Entity.History;
import hospitalMangementSystem.Entity.History;
import hospitalMangementSystem.repo.HistoryRepo;
import hospitalMangementSystem.repo.HistoryRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class HistoryService {

	private HistoryRepo historyRepo;

	public History addHistory(History model) throws Exception {
		return historyRepo.save(model);
	}

	public List<History> getAllHistorys() {
		return historyRepo.findAll();
	}

	public void removeHistory(long id) throws Exception {
		Optional<History> history =  historyRepo.findById(id);
		if (history.isEmpty()) {
			throw new Exception("docotor not found with given id");
		}
		historyRepo.deleteById(id);
		
	}

	public Optional<History> getHistory(long id) throws Exception{
		if (!historyRepo.existsById(id)) {
			throw new Exception("docotor not found with given id");
		}
		return historyRepo.findById(id);
	}
}
