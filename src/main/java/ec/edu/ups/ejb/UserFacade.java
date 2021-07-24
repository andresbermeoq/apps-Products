package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Usuario;

@Stateless
public class UserFacade extends AbstractFacade<Usuario> {
	
	@PersistenceContext(unitName = "Practice-Products")
	private EntityManager em;

	public UserFacade() {
		super(Usuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public Usuario inicioUsuario(String email, String password) {
		String consulta = "SELECT * FROM public.\"Usuario\" u WHERE u.email = ':email' AND u.password = ':password'";
		Usuario usuario = null;
		try {
			usuario = (Usuario) em.createNativeQuery(consulta)
									.setParameter("email", email)
									.setParameter("password", password).getSingleResult();
			
			
		} catch (Exception e) {
			System.out.println("--> Warning (PersonaFacade:Busqueda por Email y Password): " + e.getMessage());
		}
		return usuario;
	}
	
	public Usuario busquedaCedula(String cedula) {
		String consulta = "SELECT * FROM public.\"Usuario\" u WHERE u.\"idCard\" = ':cedula'";
		Usuario usuario = null;
		try {
			usuario = (Usuario) em.createQuery(consulta)
									.setParameter("cedula", cedula).getSingleResult();
			
			
		} catch (Exception e) {
			System.out.println("--> Warning (PersonaFacade:Busqueda por Cedula): " + e.getMessage());
		}
		return usuario;
	}

}
