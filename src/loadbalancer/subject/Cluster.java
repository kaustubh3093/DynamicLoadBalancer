package loadbalancer.subject;
import java.util.HashMap;
import java.util.Map;
import loadbalancer.entities.Machine;
import loadbalancer.entities.Service;
import loadbalancer.observer.LoadBalancer;

public class Cluster implements SubjectI{
	// Hostnames to corresponding machine instances.
	private Map<String, Machine> machines;
	LoadBalancer load;
	// Rest of the code.
	public Cluster() {
		machines = new HashMap<>();
		load = new LoadBalancer();
	}
	//It will add the machine into the cluster and if the machine already present it will throw an message 
	@Override
	public String CLUSTER_OP__SCALE_UP(String host) {
		Machine add_machine = new Machine();
		if(machines.containsKey(host)) {
			return "Machine with the host name already present on the cluster";
		}else {
			machines.put(host, add_machine);
			return "Cluster scaled up";
		}
	}
	//Use to remove the machine from the cluster and the associated service is marked as inactive in the service
	//manager
	@Override
	public String CLUSTER_OP__SCALE_DOWN(String host) {
		if(machines.containsKey(host)) {
			machines.remove(host);
			load.update("CLUSTER_OP__SCALE_DOWN", null, null, host);
			return "Cluster scaled down";
		}else {
			return "No machine with the given host name exist on the cluster";
		}
	}
	//it will check if the cluster has the machine with the given host name and if the cluster has the machine
	//with the host name it will add the service to it
	@Override
	public String SERVICE_OP_ADD_SERVICE(String name , String url , String host) {
		if(machines.containsKey(host)) {
			Machine machine = machines.get(host);
			if(machine.validate(name , host)) {
				return "The service instance is already added to the host";
			}
			Service service = new Service(name , url);
			machine.addService(name, service);
			load.update("SERVICE_OP_ADD_SERVICE", name, url, host);
			return "";
		}else {
			return "Machine with the given host name does not exist";
		}
	}
	//It will remove all instance of service from the cluster and also from the service manager
	@Override
	public String SERVICE_OP_REMOVE_SERVICE(String name) {
		int i = 0;
		for(String str : machines.keySet()){
			Machine machine = machines.get(str);
			if(machine.deleteService(str , name)) {
				i++;
			}
		}
		if(i > 0) {
			load.update("SERVICE_OP_REMOVE_SERVICE", name, null,null);
			return "Service Removed";
		}else {
			return "Invalid Service";
		}
	}
	//It will add the instance of the service so it will call the load method to get the url of the service
	// if there is no service with the name then url is null so we got to know that instance of service is 
	// not added before.
	@Override
	public String SERVICE_OP_ADD_INSTANCE(String name, String host) {
		if(machines.containsKey(host)) {
			Machine machine = machines.get(host);	
			String url = load.getUrl(name);
			if(machine.addInstance(host,name , url)) {
				load.update("SERVICE_OP_ADD_INSTANCE", name, null, host);
				return "Instance added";
			}else {
				return "Unable to add Instance of the service";
			}
		}else {
			return "Machine with the given host name does not exist";
		}
		
	}
	//It will remove the instance of the service
	@Override
	public String SERVICE_OP_REMOVE_INSTANCE(String name , String host) {
		if(machines.containsKey(host)) {
			Machine machine  = machines.get(host);
			if(machine.deleteInstance(host , name)) {
				load.update("SERVICE_OP_REMOVE_INSTANCE", name, null, host);
				return "Instance removed";
			}else {
				return "No instance of the service is present on the machine";
			}
		}else {
			return "Machine with the given host name does not exist";
		}
	}
	//To request any service from the cluster later we will to request any service from load balancer
	@Override
	public String request(String name) {
		return load.request(name);
	}
	
}
