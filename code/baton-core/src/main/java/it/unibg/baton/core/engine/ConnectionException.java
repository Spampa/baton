package it.unibg.baton.core.engine;

public class ConnectionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ConnectionException(Node out, String key1, Node in, String key2) {
		super(
			"Failed to connect the output field: " + key1 + " of the node: " + out + " with the field: " + key2 + " of the node " + in
		);
	}
	
	public ConnectionException(Node in, String key) {
		super(
			"Failed to set constant value for the input field: " + key + " of the node: " + in + ": the key doesn't exists"
		);
	}
}
