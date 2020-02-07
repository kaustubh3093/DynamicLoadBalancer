package loadbalancer.observer;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
	private String key;

	// Information pertaining to the service.
	private String URL;
	private List<String> hostnames;

	// Rest of the code.
	public ServiceManager(String name,String URL_,String host) {
		key = name;
		URL = URL_;
		hostnames = new ArrayList<>();
		hostnames.add(host);
	}
	//Getter methods
	public String getKey() {
		return key;
	}
	public String getURL() {
		return URL;
	}
	
	public List<String> getHostName(){
		return hostnames;
	}
	
}
