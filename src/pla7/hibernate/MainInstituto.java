package pla7.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pla7.hibernate.entidades.Alumnos;
import pla7.hibernate.entidades.Modulos;
import pla7.hibernate.entidades.Profesores;

public class MainInstituto {

	public static void main(String[] args) {

		// Crear la configuraci�n cog�endola del xml y a�adiendo la clase Categorias
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Modulos.class)
				.addAnnotatedClass(Profesores.class)
				.addAnnotatedClass(Alumnos.class)
				;
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		// Crear la factor�a de sesiones
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		// Crear la sesi�n
		Session session = factory.getCurrentSession();

		try {

			// Iniciar transacci�n
			session.beginTransaction();
			
			Profesores profesor1 = new Profesores("Profesor 1", "11111111A", "mail1@profesor.es");
			
			Profesores profesor2 = new Profesores("Profesor 2", "22222222B", "mail2@profesor.es");
			
			Modulos modulo1 = new Modulos("Modulo 1");
			modulo1.setProfesores(profesor1);
			
			Modulos modulo2 = new Modulos("Modulo 2");
			modulo2.setProfesores(profesor2);
			
			Alumnos alumno1 = new Alumnos("Alumno 1", "mail1@alumno.es");
			modulo1.addAlumnos(alumno1);
			
			Alumnos alumno2 = new Alumnos("Alumno 2", "mail2@alumno.es");
			modulo1.addAlumnos(alumno2);
			
			Alumnos alumno3 = new Alumnos("Alumno 3", "mail1@alumno.es");
			modulo2.addAlumnos(alumno3);

			session.save(profesor1);
			session.save(profesor2);
			session.save(modulo1);
			session.save(modulo2);
			session.save(alumno1);
			session.save(alumno2);
			session.save(alumno3);
			
			// commit de la transacci�n
			session.getTransaction().commit();


		} finally {
			factory.close();
		}
	}

}
