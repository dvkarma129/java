package com.flightBookingSystem.control;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
import com.flightBookingSystem.service.EmailService;
import com.flightBookingSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RequiredArgsConstructor
@Controller
@RequestMapping("flight")
public class UserControl {

	private final UserService userService;
	private final FlightRepo flightRepo;
	private final AirlineRepo airlineRepo;
	private final AirportRepo airportRepo;
	private final UserRepo userRepo;
	private final BookedTicketsRepo bookedTicketsRepo;
	private final EmailService emailService;
	boolean validUser;
	int userId;
	int nSeats = 0;
	int nPrice;
	int flightId = 0;
	int otp;

	@GetMapping("/getLogin")
	public String getLogin() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute FlightlistDto model, @ModelAttribute UsersDto model2) {
		validUser = userService.userLogin(model2);
		UsersDto udto = new UsersDto();
		if (validUser) {
			udto = userService.getUserData(model2);
			userId = udto.getUserId();
			ArrayList<Object> list = new ArrayList<Object>();
			List<AirlineDto> adto = airlineRepo.findAll();
			List<AirportDto> apdto = airportRepo.findAll();
			List<FlightlistDto> fdto = flightRepo.findAll();
		
			Collections.reverse(fdto);
			list.add(udto);
			list.add(adto);
			list.add(apdto);
			list.add(fdto);

//			return new ModelAndView("Home", "f", list);
			otp = emailService.sendOtp(udto, userId);
			return new ModelAndView("otp", "f", list);
		}
		return new ModelAndView("userNotFound", "f", udto);
	}

	@PostMapping("/otpVerification")
	public ModelAndView otpVerification(@ModelAttribute UsersDto model) {
		UsersDto udto = userRepo.getById(userId);
		if (udto.getUserOtp() == model.getUserOtp()) {
			
			ArrayList<Object> list = new ArrayList<Object>();
			List<AirlineDto> adto = airlineRepo.findAll();
			List<AirportDto> apdto = airportRepo.findAll();
			List<FlightlistDto> fdto = flightRepo.findAll();
		
			Collections.reverse(fdto);
			list.add(udto);
			list.add(adto);
			list.add(apdto);
			list.add(fdto);
			
			return new ModelAndView("Home", "f", list);
		}
		return new ModelAndView("userNotFound", "f", udto);
	}

	@GetMapping("/getRegister")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute UsersDto model) {
		userService.registerUser(model);
		return "login";
	}

	@GetMapping("/getUserHome")
	public ModelAndView getUserHome(@ModelAttribute FlightlistDto model) {
		UsersDto udto = new UsersDto();
		if (validUser) {
			udto = userRepo.getById(userId);
			ArrayList<Object> list = new ArrayList<Object>();
			List<AirlineDto> adto = airlineRepo.findAll();
			List<AirportDto> apdto = airportRepo.findAll();
			List<FlightlistDto> fdto = flightRepo.findAll();
			Collections.reverse(fdto);
			list.add(udto);
			list.add(adto);
			list.add(apdto);
			list.add(fdto);

			return new ModelAndView("Home", "f", list);
		}
		return new ModelAndView("login", " ", udto);
	}

	@GetMapping("/getAllFlights")
	public ModelAndView getAllFlights(@ModelAttribute FlightlistDto model) {
		ArrayList<Object> list = new ArrayList<Object>();
		UsersDto udto = userRepo.getById(userId);
		List<FlightlistDto> fdto = flightRepo.findAll();
		Collections.reverse(fdto);
		list.add(udto);
		list.add(fdto);
		System.out.println("Flights---> " + fdto);
		return new ModelAndView("allFlights", "f", list);
	}

	@PostMapping("/getSearchedFlights")
	public ModelAndView getSearchedFlights(@ModelAttribute FlightlistDto model) {
		AirportDto departureAirport = airportRepo.findByAirportName(model.getDepartureAirport().getAirportName());
		AirportDto arrivalAirport = airportRepo.findByAirportName(model.getArrivalAirport().getAirportName());
		UsersDto udto = new UsersDto();
		if (validUser) {

			ArrayList<Object> list = new ArrayList<Object>();
			udto = userRepo.getById(userId);
			List<FlightlistDto> fdto = flightRepo
					.findByDepartureAirport_AirportNameAndArrivalAirport_AirportNameOrDepartureDate(
							departureAirport.getAirportName(), arrivalAirport.getAirportName(),
							model.getDepartureDate());
			Collections.reverse(fdto);
			list.add(udto);
			list.add(fdto);
			if (fdto.isEmpty()) {
				return new ModelAndView("notFound", "f", list);
			}
			return new ModelAndView("searchedFlights", "f", list);
		}
		return new ModelAndView("login", "f", udto);
	}

	@PostMapping("/getBookFlights")
	public ModelAndView getBookFlights(@RequestParam int FlightId, @RequestParam String mySeats) {
		UsersDto udto = new UsersDto();
		if (validUser) {
			mySeats = mySeats.replace(",", "");
			nSeats = Integer.parseInt(mySeats);
			flightId = FlightId;
			ArrayList<Object> list = new ArrayList<Object>();
			udto = userRepo.getById(userId);
			FlightlistDto fdto = flightRepo.getById(FlightId);

			nPrice = (fdto.getPrice()) * nSeats;
			System.out.println(nPrice);

			list.add(udto);
			list.add(fdto);
			list.add(nPrice);
			list.add(nSeats);

			return new ModelAndView("bookFlights", "f", list);
		}
		return new ModelAndView("login", "f", udto);
	}

	@PostMapping("/bookFlight")
	public ModelAndView bookFlight() {
		UsersDto udto = new UsersDto();
		FlightlistDto fldto = flightRepo.getById(flightId);
		udto = userRepo.getById(userId);
		BookedTickets bdto = new BookedTickets();

		bdto.setFlight(fldto);
		bdto.setName(udto.getUserFullName());
		bdto.setContact(udto.getUserContact());
		bdto.setSeatsCost(nPrice);
		bdto.setGender(udto.getGender());
		bdto.setSeatsBooked(nSeats);
		bdto.setUser(udto);
		bookedTicketsRepo.save(bdto);

		udto.setBalance(udto.getBalance() - nPrice);
		userRepo.save(udto);

		fldto.setSeatsAvailable(fldto.getSeats() - nSeats);
		fldto.setSeatsBooked(fldto.getSeatsBooked() + nSeats);
		flightRepo.save(fldto);

		try {
	        byte[] pdfData = generateTicketPDF(bdto);

	        // Send email with attached PDF
	        emailService.sendTicketEmail(udto, pdfData);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
		
		ArrayList<Object> list = new ArrayList<Object>();
		List<AirlineDto> adto = airlineRepo.findAll();
		List<AirportDto> apdto = airportRepo.findAll();
		List<FlightlistDto> fdto = flightRepo.findAll();
		Collections.reverse(fdto);
		list.add(udto);
		list.add(adto);
		list.add(apdto);
		list.add(fdto);

		return new ModelAndView("Home", "f", list);
	}

	private byte[] generateTicketPDF(BookedTickets bdto) throws Exception, JRException {
	    JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(Collections.singletonList(bdto));
	    JasperReport compReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/ticket.jrxml"));
	    HashMap<String, Object> map = new HashMap<>();
	    JasperPrint report = JasperFillManager.fillReport(compReport, map, jrBean);

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    JasperExportManager.exportReportToPdfStream(report, baos);

	    return baos.toByteArray();
	}
	
	@GetMapping("/getWallet")
	public ModelAndView getWallet(@ModelAttribute UsersDto model) {
		ArrayList<Object> list = new ArrayList<Object>();
		UsersDto udto = userRepo.getById(userId);
		list.add(udto);
		return new ModelAndView("wallet", "f", list);
	}

	@PostMapping("/addBalance")
	public ModelAndView addBalance(@ModelAttribute UsersDto model) {
		ArrayList<Object> list = new ArrayList<Object>();
		UsersDto udto = userRepo.getById(userId);
		list.add(udto);

		int amt = model.getBalance();
		udto.setBalance(amt + udto.getBalance());
		userRepo.save(udto);
		return new ModelAndView("wallet", "f", list);
	}

	@GetMapping("/getProfile")
	public ModelAndView getProfile(@ModelAttribute UsersDto model) {
		ArrayList<Object> list = new ArrayList<Object>();
		UsersDto udto = userRepo.getById(userId);
		list.add(udto);
		return new ModelAndView("userProfile", "f", list);
	}

	@GetMapping("/getUpdateProfile")
	public ModelAndView getUpdateProfile(@ModelAttribute UsersDto model) {
		ArrayList<Object> list = new ArrayList<>();
		UsersDto udto = userRepo.getById(userId);
		list.add(udto);
		return new ModelAndView("profile", "f", list);
	}

	@PostMapping("/updateUser")
	public ModelAndView updateUser(@ModelAttribute UsersDto model) {
		userService.updateUser(model, userId);
		ArrayList<Object> list = new ArrayList<Object>();
		UsersDto udto = userRepo.getById(userId);
		list.add(udto);
		return new ModelAndView("userProfile", "f", list);
	}

	@GetMapping("/getMyTickets")
	public ModelAndView getBookedTickets(@ModelAttribute BookedTickets model) {
		ArrayList<Object> list = new ArrayList<Object>();
		UsersDto udto = userRepo.getById(userId);
		List<BookedTickets> bdto = bookedTicketsRepo.getByUser(udto);
		Collections.reverse(bdto);
		list.add(udto);
		list.add(bdto);

		return new ModelAndView("myTickets", "f", list);
	}
	
	@GetMapping("/genPdfById")
	public ResponseEntity<byte []> genPdfById(@RequestParam int TicketId) throws Exception, JRException {
		BookedTickets bdto = bookedTicketsRepo.getById(TicketId);
		JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(Collections.singletonList(bdto));
		JasperReport  compReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/ticket.jrxml"));
		  HashMap<String, Object> map = new HashMap<>();
		JasperPrint report =  JasperFillManager.fillReport(compReport, map,jrBean);
//		JasperExportManager.exportReportToPdfFile(report, "JpdfTrial1.pdf");
		
		byte [] data = JasperExportManager.exportReportToPdf(report);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=notes.pdf");
		
		//		return "pdf generatee999999999999999999999";
	
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
