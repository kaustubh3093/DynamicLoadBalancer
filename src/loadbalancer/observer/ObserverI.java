package loadbalancer.observer;

public interface ObserverI {
	public void update(String Operation, String name, String URL, String host);
	public String request(String name);
	public String getUrl(String name);
}
