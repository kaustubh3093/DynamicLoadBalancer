package loadbalancer.observer;

import loadbalancer.subject.Cluster;

public class LoadBalancer implements ObserverI{
	// Index to find the URL and hostname for a given service name.
	
	private Trie ServiceURLAndHostnameFetcher;
    //name of the operation, service name, service URL, and the hostnames of the 
	//machines which each host an instance of the service.
	// Rest of the code.
	public LoadBalancer() {
		ServiceURLAndHostnameFetcher = new Trie();
	}
	//This method will call the update method present in trie for further processing
	@Override
	public void update(String Operation, String name, String URL, String host) {
		ServiceURLAndHostnameFetcher.update(Operation, name, URL, host);
	}
	//This method will call the request method present in trie for getting an output 
	@Override
	public String request(String name) {
		return ServiceURLAndHostnameFetcher.request(name);
	}
	//To get an url for a specific service
	@Override
	public String getUrl(String name) {
		return ServiceURLAndHostnameFetcher.requestURL(name);
	}
}
