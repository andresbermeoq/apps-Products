package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Factura_Detalle;

@Stateless
public class DetalleFacturaFacade extends AbstractFacade<Factura_Detalle> {
	
	@PersistenceContext(unitName = "Practice-Products")
	private EntityManager em;

	public DetalleFacturaFacade() {
		super(Factura_Detalle.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
