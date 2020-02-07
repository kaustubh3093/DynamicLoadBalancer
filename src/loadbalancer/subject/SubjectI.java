package loadbalancer.subject;

public interface SubjectI {

	public String CLUSTER_OP__SCALE_UP(String host);
	public String CLUSTER_OP__SCALE_DOWN(String host);
	public String SERVICE_OP_ADD_SERVICE(String name , String url , String host);
	public String SERVICE_OP_REMOVE_SERVICE(String name);
	public String SERVICE_OP_ADD_INSTANCE(String name, String host);
	public String SERVICE_OP_REMOVE_INSTANCE(String name , String host);
	public String request(String name);
}
