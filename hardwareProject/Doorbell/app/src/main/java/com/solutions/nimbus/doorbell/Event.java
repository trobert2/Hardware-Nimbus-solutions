package com.solutions.nimbus.doorbell;

public class Event {

	private String data;
	private String nome;

	public Event(String data, String nome) {
		super();
		this.data = data;
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
