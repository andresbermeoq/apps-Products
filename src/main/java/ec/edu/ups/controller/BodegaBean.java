package ec.edu.ups.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.entidad.Bodega;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BodegaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String address;
	private String name;
	
	@EJB
	private BodegaFacade bodegaFacade;
	
	public BodegaBean() {
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String addBodega() {
		bodegaFacade.create(new Bodega(address, name));
		return null;
	}
	
	

}
