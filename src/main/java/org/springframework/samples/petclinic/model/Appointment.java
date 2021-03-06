package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="appointment")
public class Appointment extends BaseEntity {
    
	@Column(name = "apt_date")
	Date appointmentDate;
	String slot;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	Owner owner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vet_id", nullable = false)
	Vet vet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pet_id", nullable = false)
	Pet pet;
	String status;
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Vet getVet() {
		return vet;
	}
	public void setVet(Vet vet) {
		this.vet = vet;
	}
	
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
