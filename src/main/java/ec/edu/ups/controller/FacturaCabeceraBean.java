package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.CabeceraFacturaFacade;
import ec.edu.ups.ejb.DetalleFacturaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.UserFacade;
import ec.edu.ups.entidad.Producto;
import ec.edu.ups.entidad.Usuario;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaCabeceraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CabeceraFacturaFacade cabeceraFacturaFacade;
	
	@EJB
	private UserFacade userFacade;
	
	@EJB
	private ProductoFacade productoFacade;
	
	@EJB
	private DetalleFacturaFacade detalleFacturaFacade;
	
	
	private List<Usuario> usuarios;
	private List<Producto> productos;
	
	@PostConstruct
	public void init() {
		setUsuarios(userFacade.findAll());
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
