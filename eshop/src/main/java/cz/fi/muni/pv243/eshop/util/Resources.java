package cz.fi.muni.pv243.eshop.util;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2013017526777741872L;

	@SuppressWarnings("unused")
	@Produces
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unused")
	@Produces
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@SuppressWarnings("unused")
	@Produces
	@Resource(mappedName = "java:/queue/test")
	private Queue queue;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}

}
