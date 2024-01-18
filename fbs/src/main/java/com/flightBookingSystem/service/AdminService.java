package com.flightBookingSystem.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flightBookingSystem.dto.AdminDto;
import com.flightBookingSystem.dto.AirlineDto;
import com.flightBookingSystem.dto.AirportDto;
import com.flightBookingSystem.dto.FlightlistDto;
import com.flightBookingSystem.repo.AdminRepo;
import com.flightBookingSystem.repo.AirlineRepo;
import com.flightBookingSystem.repo.AirportRepo;
import com.flightBookingSystem.repo.FlightRepo;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminRepo adminRepo;
	private final AirlineRepo airlineRepo;
	private final AirportRepo airportRepo;
	private final FlightRepo flightRepo; 
	
	public boolean adminLogin(AdminDto model) {
		int count = adminRepo.countByAdminGmailAndAdminPassword(model.getAdminGmail(),model.getAdminPassword());
		System.out.println(count);
		if (count>0) {
			return true;
		}
		return false;
	}

	public AdminDto getAdminData(AdminDto model) {
		// TODO Auto-generated method stub
		AdminDto dto = adminRepo.getByAdminGmailAndAdminPassword(model.getAdminGmail(),model.getAdminPassword());
		
		return dto;
	}
	
	
	@Transactional
	public List<AirlineDto> getAirline() {
		List<AirlineDto> dto = airlineRepo.findAll();
		return dto;
	}

	public void addNewAirline(AirlineDto model) throws IOException{
		AirlineDto dto = new AirlineDto();
		dto.setAirlineName(model.getAirlineName());
		dto.setAirlineImgPath(getAirlineImgPath(model.getAirlineImg()));
		airlineRepo.save(dto);
	}

	public void editAirline(AirlineDto model) {
		AirlineDto adto = airlineRepo.getById(model.getAirlineId());
		adto.setAirlineName(model.getAirlineName());
		airlineRepo.save(adto);
	}

	public List<AirportDto> getAirports() {
		List<AirportDto> dto = airportRepo.findAll();
		return dto;
	}

	public void addNewAirport(AirportDto model) {
		AirportDto apdto = new AirportDto();
		apdto.setAirportName(model.getAirportName());
		apdto.setAirportLocation(model.getAirportLocation());
		airportRepo.save(apdto);
	}


	public void editAirport(AirportDto model) {
		// TODO Auto-generated method stub
		AirportDto apdto = airportRepo.getById(model.getAirportId());
		apdto.setAirportName(model.getAirportName());
		apdto.setAirportLocation(model.getAirportLocation());
		airportRepo.save(apdto);
	}

	@Transactional
	public void newFlight(FlightlistDto model) {
		System.out.println("================================flight saving service running================================");
		
		
		AirlineDto adto = airlineRepo.findByAirlineName(model.getAirline().getAirlineName());
		AirportDto apdto = airportRepo.findByAirportName(model.getDepartureAirport().getAirportName());
		AirportDto apdto2 = airportRepo.findByAirportName(model.getArrivalAirport().getAirportName());
		
		FlightlistDto fdto = new FlightlistDto();
		fdto.setAirline(adto);
		fdto.setPlaneNo(model.getPlaneNo());
		fdto.setDepartureAirport(apdto);
		fdto.setArrivalAirport(apdto2);
		fdto.setDepartureDate(model.getDepartureDate());
		fdto.setArrivalDate(model.getArrivalDate());
		fdto.setSeatsAvailable(model.getSeats());
		fdto.setSeatsBooked(0);
		fdto.setSeats(model.getSeats());
		fdto.setPrice(model.getPrice());
		
		flightRepo.save(fdto);
		
		System.out.println("================================flight saved================================");
		
	}
	
	private String getAirlineImgPath(MultipartFile airlineImg) throws IOException {
		String path = "../image/";

		if (!airlineImg.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			try {

				String fileName = "airlineImg" + timestamp.getTime() + ".jpg";
				path = path + fileName;
				File file = new File(
						"C:\\Users\\Admin\\Documents\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\fbs\\src\\main\\webapp\\image\\"
								+ fileName);
				Files.copy(airlineImg.getInputStream(), file.toPath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			path = path + "defaultProPic.jpg";
		}

		return path;
	}
	
	
}
