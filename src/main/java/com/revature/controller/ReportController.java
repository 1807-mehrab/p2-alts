package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Report;
import com.revature.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	private ReportService service;

	public void setService(ReportService service) {
		this.service = service;
	}

	@GetMapping("/report/{id}")
	public Report byId(@PathVariable("id") long id) {
		return service.getById(id);
	}

	@GetMapping("/report")
	public List<Report> reports() {
		return service.getAll();
	}

	@PostMapping(path = "/report", consumes = "application/json", produces = "application/json")
	public Report addReport(@RequestBody Report report) {
		service.add(report);
		return service.getByTitle(report.getTitle());
	}

	@DeleteMapping("/report/{id}")
	public @ResponseBody ResponseEntity<Report> deleteReport(@PathVariable("id") long id) {
		Report report = service.getById(id);
		if (report == null) {
			return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
		}

		service.delete(report);
		return new ResponseEntity<Report>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/report/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Report> updateUser(@PathVariable("id") long id, @RequestBody Report report) {
		Report currentReport = service.getById(id);

		if (currentReport == null) {
			return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
		}

		ReportController.populate(currentReport, report);

		service.update(currentReport);
		return new ResponseEntity<Report>(currentReport, HttpStatus.OK);
	}

	protected static void populate(Report currentReport, Report report) {
		currentReport.setBody(report.getBody());
		ClientController.populate(currentReport.getClient(), report.getClient());
		ManagerController.populate(currentReport.getManager(), report.getManager());
		currentReport.setClient(report.getClient());
		currentReport.setManager(report.getManager());
		currentReport.setDatereported(report.getDatereported());
		currentReport.setDateresolved(report.getDateresolved());
		currentReport.setResponse(report.getResponse());
		currentReport.setTitle(report.getTitle());
	}
}
