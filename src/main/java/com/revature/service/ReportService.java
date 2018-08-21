package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Report;
import com.revature.repository.ReportDao;

@Service
public class ReportService {
	@Autowired
	ReportDao dao;

	public void setDao(ReportDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Report> getAll() {
		return dao.getReports();
	}

	@Transactional
	public void add(Report report) {
		dao.addReport(report);
	}

	@Transactional
	public void delete(Report report) {
		dao.deleteReport(report);
	}

	@Transactional
	public Report getById(int id) {
		return dao.getReportById(id);
	}

	@Transactional
	public Report update(Report report) {
		return dao.updateReport(report);
	}
}