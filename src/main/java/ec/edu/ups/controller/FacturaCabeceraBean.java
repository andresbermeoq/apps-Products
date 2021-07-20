package ec.edu.ups.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import ec.edu.ups.ejb.CabeceraFacturaFacade;
import ec.edu.ups.ejb.DetalleFacturaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.UserFacade;
import ec.edu.ups.entidad.Cabecera_Factura;
import ec.edu.ups.entidad.Factura_Detalle;
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
	
	private List<Usuario> clientes;
	private List<Producto> productos;
	private List<Producto> productosSelected;
	private String idUser;
	private String idProduct;
	private Date fecha;
	
	private Double subTotal = 0.0;
	private Double iva = 0.0;
	private Double total = 0.0;
	private Double total1 = 0.0;
	
	private Integer cantidad;
	private BigDecimal unitario;
	
	public FacturaCabeceraBean() {
		cantidad = null;
	}

	@PostConstruct
	public void init() {
		clientes = new ArrayList<Usuario>();
		productosSelected = new ArrayList<Producto>();
		userEmployers();
		setProductos(productoFacade.findAll());
	}

	public List<Usuario> getClientes() {
		return clientes;
	}
	public void setClientes(List<Usuario> clientes) {
		this.clientes = clientes;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Producto> getProductosSelected() {
		return productosSelected;
	}

	public void setProductosSelected(List<Producto> productosSelected) {
		this.productosSelected = productosSelected;
	}
	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public BigDecimal getUnitario() {
		return unitario;
	}

	public void setUnitario(BigDecimal unitario) {
		this.unitario = unitario;
	}
	public Double getTotal1() {
		return total1;
	}

	public void setTotal1(Double total1) {
		this.total1 = total1;
	}

	public void userEmployers() {
		for(Usuario usuario: userFacade.findAll()) {
			if (usuario.getRole().equals("Cliente")) {
				clientes.add(usuario);
			}
		}
	}
	
	public void searchDetalle() {
		Producto productSelect = productoFacade.find(Integer.valueOf(idProduct));
		productosSelected.add(productSelect);
	}
	
	public void removeDetalle(Producto prodRemove) {
		productosSelected.remove(prodRemove);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
		PrimeFaces.current().ajax().update("form:messages", "form:table");
	}
	
	public void onEditRow(Producto productoSelected) {
		if (comprobarStock(this.cantidad, productoSelected)) {
			this.unitario = productoSelected.getPrice().multiply(new BigDecimal(cantidad));
			detalleFacturaFacade.create(new Factura_Detalle(cantidad, unitario, null, productoSelected));
		}
		
	}
	
	public void calculateFacture() {
		this.setSubTotal(subTotal + total);
	}
	
	public boolean comprobarStock(int cantidad, Producto prodc) {
		int cantidadComprar = prodc.getStock() - cantidad;
		
		if (cantidadComprar >= 0) {
			return true;
		}
		
		return false;
	}
	
	public void addFactura() {
		
	}

}
