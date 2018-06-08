package application;

import javafx.scene.control.CheckBox;

public class Student {

	private String nume,prenume, CNP, grupa, specializare, nota;
	private CheckBox select;

	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getNume() {
		return nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public String getCNP() {
		return CNP;
	}

	public String getGrupa() {
		return grupa;
	}

	public String getSpecializare() {
		return specializare;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}

	public void setSpecializare(String specializare) {
		this.specializare = specializare;
	}

	public Student(String grupa) {
		this(null,null,null,grupa,null);
	}
	public Student(String nume, String prenume, String CNP, String nota) {
		this(nume,prenume,CNP,null,null,nota);
	}
	public Student(String nume, String prenume, String CNP, String grupa, String specializare){
		this(nume,prenume,CNP,grupa,specializare,null);
	}
	public Student(String nume, String prenume, String CNP, String grupa, String specializare,String nota){
		this.nume = nume;
		this.prenume = prenume;
		this.CNP = CNP;
		this.grupa = grupa;
		this.specializare = specializare;
		this.nota = nota;
		this.select = new CheckBox();
	}
}
