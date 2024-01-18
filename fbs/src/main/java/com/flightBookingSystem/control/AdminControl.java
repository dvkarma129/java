package com.flightBookingSystem.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.flightBookingSystem.dto.AdminDto;
import com.flightBookingSystem.dto.AirlineDto;
import com.flightBookingSystem.dto.AirportDto;
import com.flightBookingSystem.dto.BookedTickets;
import com.flightBookingSystem.dto.FlightlistDto;
import com.flightBookingSystem.dto.UsersDto;
import com.flightBookingSystem.repo.AirlineRepo;
import com.flightBookingSystem.repo.AirportRepo;
import com.flightBookingSystem.repo.BookedTicketsRepo;
import com.flightBookingSystem.repo.FlightRepo;
import com.flightBookingSystem.repo.UserRepo;
import com.flightBookingSystem.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/Admin")
public class AdminControl {

	private final AdminService adminService;
	private final AirlineRepo airlineRepo;
	private final AirportRepo airportRepo;
	private final BookedTicketsRepo bookedTicketsRepo;
	private final UserRepo userRepo;
	private final FlightRepo flightRepo;

	boolean validAdmin;

	@GetMapping("/getLogin")
	public String getLogin() {
		return "adminLogin";
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute AdminDto model) {
		validAdmin = adminService.adminLogin(model);
		AdminDto dto = new AdminDto();

		if (validAdmin) {
			dto = adminService.getAdminData(model);
			return new ModelAndView("adminHome", "a", dto);
		}
		return new ModelAndView("getLogin", "a", dto);

	}

	@GetMapping("/getHome")
	public ModelAndView getHome(@ModelAttribute AdminDto model) {
		AdminDto dto = new AdminDto();
		if (validAdmin) {
			dto = adminService.getAdminData(model);
			return new ModelAndView("adminHome", "a", dto);
		}
		return new ModelAndView("getLogin", "a", dto);
	}

	@GetMapping("/getAirlines")
	public ModelAndView getAirlines(@ModelAttribute AirlineDto model) {
		List<AirlineDto> adto = adminService.getAirline();
		System.out.println("airlines-->" + adto);
		return new ModelAndView("airlines", "a", adto);
	}

	@GetMapping("/getNewAirline")
	public String getNewAirline() {
		return "newAirline";
	}

	@PostMapping("/newAirline")
	public ModelAndView newAirline(@ModelAttribute AirlineDto model) throws IOException {
		adminService.addNewAirline(model);
		List<AirlineDto> adto = adminService.getAirline();
		return new ModelAndView("airlines", "a", adto);
	}

	@GetMapping("/deleteAirline")
	public ModelAndView deleteAirline(@RequestParam int AirlineId) {
		airlineRepo.deleteById(AirlineId);
		List<AirlineDto> adto = adminService.getAirline();
		return new ModelAndView("airlines", "a", adto);
	}

	@GetMapping("/getEditAirline")
	public ModelAndView getEditAirline(@RequestParam int AirlineId) {
		AirlineDto adto = airlineRepo.getById(AirlineId);
		return new ModelAndView("editAirline", "a", adto);
	}

	@PostMapping("/editAirline")
	public ModelAndView editAirline(@ModelAttribute AirlineDto model) {
		adminService.editAirline(model);
		List<AirlineDto> adto = adminService.getAirline();
		System.out.println("airlines-->" + adto);
		return new ModelAndView("airlines", "a", adto);
	}

	@GetMapping("/getAirports")
	public ModelAndView getAairports(@ModelAttribute AirportDto model) {
		List<AirportDto> apdto = adminService.getAirports();
		System.out.println("airports---> " + apdto);
		return new ModelAndView("airports", "ap", apdto);
	}

	@PostMapping("/newAirport")
	public ModelAndView newAirport(@ModelAttribute AirportDto model) {
		adminService.addNewAirport(model);
		List<AirportDto> apdto = adminService.getAirports();
		System.out.println("airports---> " + apdto);
		return new ModelAndView("airports", "ap", apdto);
	}

	@GetMapping("/getEditAirport")
	public ModelAndView getEditAirport(@RequestParam int AirportId) {
		AirportDto apdto = airportRepo.getById(AirportId);
		return new ModelAndView("editAirport", "ap", apdto);
	}

	@PostMapping("/editAirport")
	public ModelAndView editAirport(@ModelAttribute AirportDto model) {
		adminService.editAirport(model);
		List<AirportDto> apdto = adminService.getAirports();
		System.out.println("airports---> " + apdto);
		return new ModelAndView("airports", "ap", apdto);
	}

	@GetMapping("/deleteAirport")
	public ModelAndView deleteAirport(@RequestParam int AirportId) {
		airportRepo.deleteById(AirportId);
		List<AirportDto> apdto = adminService.getAirports();
		System.out.println("airports---> " + apdto);
		return new ModelAndView("airports", "ap", apdto);
	}

	@GetMapping("/getUsers")
	public ModelAndView getUsers(@ModelAttribute UsersDto model) {
		List<UsersDto> udto = userRepo.findAll();
		return new ModelAndView("manageUsers", "u", udto);
	}

	@GetMapping("/getFlights")
	public ModelAndView getFlights(@ModelAttribute FlightlistDto model) {
		List<FlightlistDto> fdto = flightRepo.findAll();
		System.out.println("Flights---> " + fdto);
		return new ModelAndView("flights", "f", fdto);
	}

	@GetMapping("/getNewFlights")
	public ModelAndView getNewFlights(@ModelAttribute FlightlistDto model) {

		ArrayList<Object> list = new ArrayList<Object>();
		List<AirlineDto> adto = airlineRepo.findAll();
		List<AirportDto> apdto = airportRepo.findAll();
		list.add(adto);
		list.add(apdto);
		return new ModelAndView("newFlight", "f", list);
	}

	@GetMapping("/deleteFlight")
	public ModelAndView deleteFlight(@RequestParam int FlightId) {
		flightRepo.deleteById(FlightId);
		List<FlightlistDto> fdto = flightRepo.findAll();
		return new ModelAndView("flights", "f", fdto);
	}
	
	@PostMapping("/newFlights")
	public ModelAndView newFlights(@ModelAttribute FlightlistDto model) {
		adminService.newFlight(model);
		List<FlightlistDto> fdto = flightRepo.findAll();
		System.out.println("Flights---> " + fdto);
		System.out.println(
				"================================ post map called for flight saving ================================");
		return new ModelAndView("flights", "f", fdto);
	}

	@GetMapping("/getBookedTickets")
	public ModelAndView getBookedTickets(@ModelAttribute BookedTickets model) {
		List<BookedTickets> bdto = bookedTicketsRepo.findAll();
		System.out.println("tickets---> " + bdto);
		return new ModelAndView("bookedTickets", "b", bdto);
	}
}
