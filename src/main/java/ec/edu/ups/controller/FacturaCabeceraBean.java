package ec.edu.ups.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
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
	
	private List<Factura_Detalle> detalles;
	private Factura_Detalle detalle;
	private Cabecera_Factura cabecera_Factura;
	private String idUser;
	private String idProduct;
	private Date fecha;
	
	private Double subTotal = 0.0;
	private Double iva = 0.0;
	private Double total = 0.0;
	
	private BigDecimal unitario;
	
	public FacturaCabeceraBean() {
	}

	@PostConstruct
	public void init() {
		clientes = new ArrayList<Usuario>();
		productosSelected = new ArrayList<Producto>();
		detalles = new ArrayList<Factura_Detalle>();
		detalle = new Factura_Detalle();
		cabecera_Factura = new Cabecera_Factura();
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
	public List<Factura_Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Factura_Detalle> detalles) {
		this.detalles = detalles;
	}

	public Factura_Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Factura_Detalle detalle) {
		this.detalle = detalle;
	}
	public Cabecera_Factura getCabecera_Factura() {
		return cabecera_Factura;
	}

	public void setCabecera_Factura(Cabecera_Factura cabecera_Factura) {
		this.cabecera_Factura = cabecera_Factura;
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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Agregado"));
		PrimeFaces.current().ajax().update("form:messages", "form:table");
	}
	
	public void onEditRow(Producto productoSelected) {
		BigDecimal unidad = productoSelected.getPrice().multiply(new BigDecimal(detalle.getCantidad()));
		detalle.setValorUnitario(unidad);
		detalle.setProducto(productoSelected);
		detalle.setCabeceraFactura(cabecera_Factura);
		detalles.add(detalle);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cantidad Agregado"));
		PrimeFaces.current().ajax().update("form:messages", "form:table");
		
		detalle = new Factura_Detalle();
		productosSelected.clear();
	}
	
	public void calculateTotales() {
		Double sumDouble = 0.0;
		
		for (Factura_Detalle detalle1: detalles) {
			sumDouble += detalle1.getValorUnitario().doubleValue();
		}
		
		this.subTotal = sumDouble;
		this.iva = (12 * this.subTotal) / 100;
		this.total = this.subTotal + this.iva;
		PrimeFaces.current().ajax().update("form:sub", "form:iva", ":form:tot");
	}
	
	public void addFactura() {
		Usuario usuario = userFacade.find(Integer.valueOf(idUser));
		cabecera_Factura.setFecha(fecha);
		cabecera_Factura.setSubtotal(new BigDecimal(subTotal, MathContext.DECIMAL64));
		cabecera_Factura.setIva(new BigDecimal(iva, MathContext.DECIMAL64));
		cabecera_Factura.setTotal(new BigDecimal(total, MathContext.DECIMAL64));
		cabecera_Factura.setUsuario(usuario);
		
		for(Factura_Detalle detalle2: detalles) {
			Producto productoStock = new Producto();
			productoStock = productoFacade.find(detalle2.getProducto().getId());
			Integer nuevoStock = productoStock.getStock() - detalle2.getCantidad();
			productoStock.setStock(nuevoStock);
			productoFacade.edit(productoStock);
		}
		
		cabecera_Factura.setFacturaDetalles(detalles);
		cabeceraFacturaFacade.create(cabecera_Factura);
		detalles = new ArrayList<Factura_Detalle>();
		setSubTotal(0.0);
		setIva(0.0);
		setTotal(0.0);
	}


}
