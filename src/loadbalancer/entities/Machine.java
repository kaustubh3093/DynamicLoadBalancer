package loadbalancer.entities;

import java.util.HashMap;
import java.util.Map;

public class Machine {
	private String hostname;
	// Service name to hosted services.
	private Map<String, Service> hostedServices;
	public Machine() {
		hostedServices = new HashMap<>();
	}
	//Update the hashmap with appropriate value
	public void addService(String name , Service service) {
		hostedServices.put(name, service);
	}
	//Will need to validate if the host and service already present 
	public boolean validate(String name , String host) {
		if(hostedServices.containsKey(name)) {
		Service service = hostedServices.get(name);
		if(service.getName().equals(name)) {
			return true;
		}
		}
		return false;
	}
	//will need to delete the service
	public boolean deleteService(String host ,String name) {
		if(hostedServices.containsKey(name)) {
		Service service = hostedServices.get(name);
		if(service.getName().equals(name)) {
			hostedServices.remove(name);
			return true;
		}
		}
		return false;
	}
	
	//Will perform the validation if the service name is already present on the host
	//If the service name present it will not add the instance
	//Return false indicating the given instance failed to get added on the host
	public boolean addInstance(String host, String name , String url) {
		if(hostedServices.containsKey(name)) {
			Service service = hostedServices.get(name);
			if(service.getName().equals(name)) {
				return false;
			}}
			    if(url == null) {
			    	return false;
			    }
				Service serv = new Service(name , url);
				hostedServices.put(name, serv);
				return true;
		}
	//Will perform the validation that if the service is present on the host then delete the instance
	public boolean deleteInstance(String host , String name) {
		if(hostedServices.containsKey(name)) {
			Service service = hostedServices.get(name);
			if(service.getName().equals(name)) {
				hostedServices.remove(name);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	//To return the service url and the host name
	public void getService(String host ,String name) {
		Service service = hostedServices.get(name);
		if(service.getName() == null) {
			return;
		}
		else if(service.getName().equals(name)) {
			System.out.println("Service URL: " +service.getUrl() + "HOST: " + host );
		}
	}
	
}
