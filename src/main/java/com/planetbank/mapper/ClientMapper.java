package com.planetbank.mapper;

import java.time.LocalDate;

import com.planetbank.entity.Client;

public class ClientMapper {
	private Integer id_client;
	private String name;
	private String lastname;
	private String username;
	private Integer age;
	private String address;
	private String dni;
	private String phone;
	private String city;
	private String country;
	private String account_number;
	private LocalDate birthday;
	private LocalDate registration_date;

	public ClientMapper() {
	}

	public ClientMapper(Integer id_client, String name, String lastname, String username, Integer age, String address,
			String dni, String phone, String city, String country, String account_number, LocalDate birthday,
			LocalDate registration_date) {
		this.id_client = id_client;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.age = age;
		this.address = address;
		this.dni = dni;
		this.phone = phone;
		this.city = city;
		this.country = country;
		this.account_number = account_number;
		this.birthday = birthday;
		this.registration_date = registration_date;
	}

	public ClientMapper(Client client) {
		this.id_client = client.getIdClient();
		this.name = client.getName();
		this.lastname = client.getLastname();
		this.username = client.getCredential().getUsername();
		this.age = client.getAge();
		this.dni = client.getDni();
		this.phone = client.getPhone();
		this.city = client.getCity();
		this.country = client.getCountry();
		this.address = client.getAddress();
		this.birthday = client.getBirthday();
		this.registration_date = client.getRegistrationDate();
		this.account_number = client.getAccount().getAccountNumber();
	}

	public Integer getId_client() {
		return id_client;
	}

	public void setId_client(Integer id_client) {
		this.id_client = id_client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(LocalDate registration_date) {
		this.registration_date = registration_date;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

}
