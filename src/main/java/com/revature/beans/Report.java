package com.revature.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Report {
	@Id
	private long report_id;
	private String title, body, response;
	private Date datereported, dateresolved;
	@ManyToOne
	private Employee employee;
	@ManyToOne
	private Manager manager;
	@ManyToOne
	private Client client;

	public long getReport_id() {
		return report_id;
	}

	public void setReport_id(long report_id) {
		this.report_id = report_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getDatereported() {
		return datereported;
	}

	public void setDatereported(Date datereported) {
		this.datereported = datereported;
	}

	public Date getDateresolved() {
		return dateresolved;
	}

	public void setDateresolved(Date dateresolved) {
		this.dateresolved = dateresolved;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Report [report_id=" + report_id + ", title=" + title + ", body=" + body + ", response=" + response
				+ ", datereported=" + datereported + ", dateresolved=" + dateresolved + ", employee=" + employee
				+ ", manager=" + manager + ", client=" + client + "]";
	}
}
