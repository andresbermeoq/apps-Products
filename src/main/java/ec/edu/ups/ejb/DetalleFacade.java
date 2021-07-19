package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Factura_Detalle;

@Stateless
public class DetalleFacade extends AbstractFacade<Factura_Detalle> {

	@PersistenceContext(unitName = "Practice-Products")
	private EntityManager em;
	
	public DetalleFacade() {
		super(Factura_Detalle.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
