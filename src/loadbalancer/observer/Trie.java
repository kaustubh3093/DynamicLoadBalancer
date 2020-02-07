package loadbalancer.observer;

import java.util.HashMap;

public class Trie {
	// Code for Trie data structure.
	// Used as a key value store.
	// Key is the service name. Value is an instance of ServiceManager.

	// Rest of the code.
	HashMap<String, ServiceManager> trie;
	public Trie() {
		trie = new HashMap<>();
	}
	public void update(String Operation, String name, String URL, String host) {
		if(Operation.equals("SERVICE_OP_ADD_SERVICE")) {
			int flag = 0;
			for(String str : trie.keySet()) {
				ServiceManager sm = trie.get(str);
				if(sm.getKey().equals(name) && sm.getURL().equals(URL)) {
					sm.getHostName().add(host);
					flag++;
				}
			}
			if(flag == 0) {
				ServiceManager service = new ServiceManager(name, URL, host);
				trie.put(name, service);
			}
		}
		else if(Operation.equals("SERVICE_OP_REMOVE_SERVICE")) {
			trie.remove(name);
		}
		else if(Operation.equals("SERVICE_OP_ADD_INSTANCE")) {
			ServiceManager servMan = trie.get(name);
			servMan.getHostName().add(host);
		}
		else if(Operation.equals("SERVICE_OP_REMOVE_INSTANCE")) {
			ServiceManager servMan = trie.get(name);
			servMan.getHostName().remove(host);
		}
		else if(Operation.equals("CLUSTER_OP__SCALE_DOWN")) {
			for(String str : trie.keySet()) {
				ServiceManager servMan = trie.get(name);
				if(servMan.getHostName().contains(host)) {
					servMan.getHostName().remove(host);
				}
			}
		}
	}
	//To return the result of requested service
	public String request(String name) {
		if(trie.containsKey(name)) {
			ServiceManager sm = trie.get(name);
			if(sm.getHostName().size() != 0) {
				String host = sm.getHostName().get(0);
				sm.getHostName().remove(0);
				sm.getHostName().add(host);
				return "Processed Request - Service URL: " + sm.getURL() + " Host:: " + host;
			}else {
				return "The given service " + name + " has no host";
			}
		}else {
			return "Invalid Service";
		}
	}
	//To return the requested url 
	public String requestURL(String name) {
		if(trie.containsKey(name)) {
			ServiceManager smg = trie.get(name);
			return smg.getURL();
		}else {
			return null;
		}
	}
	
}
