import java.util.Hashtable;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sound.midi.Soundbank;

import dao.IDao;
import entities.Filiere;
import entities.Student;

public class TestEmploye {
	public static IDao<Student> lookUpStudentRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDao<Student>) context.lookup("ejb:/G4Serveur/G4EmS!dao.IDao");

	}

	
	
	public static IDao<Filiere> lookUpFiliereRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDao<Filiere>) context.lookup("ejb:/G4Serveur/FiliereDao!dao.IDao");

	}
	
	public static void main(String[] args) {
		try {
			IDao<Student> dao = lookUpStudentRemote();
			IDao<Filiere> daof = lookUpFiliereRemote();
			Filiere f1=daof.create(new Filiere(1,"IIR"));
			Filiere f2=daof.create(new Filiere(2,"GC"));
			dao.create(new Student("pass1","naja","Mohamed","Naja",f1));
			dao.create(new Student("pass2","raze","Ahmed","Polan",f1));
			dao.create(new Student("pass3","plomm","Soufiane","latex",f1));
			dao.create(new Student("pass4","peterson","Inssaf","Daln",f2));
			
			for(Student e: dao.findAll())
				System.out.println(e.getFirstName()+"-> filiere : "+e.getFiliere().getName());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
