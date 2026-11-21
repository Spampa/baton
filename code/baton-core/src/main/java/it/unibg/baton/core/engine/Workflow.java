package it.unibg.baton.core.engine;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.unibg.baton.core.block.ValidationException;

public class Workflow {
	private final String name;
	private Set<Node> nodes;
	
	public Workflow(String name) {
		this.name = name;
		this.nodes = new HashSet<Node>();
	}
	
	public void connect(Node out, String key1, Node in, String key2) throws ConnectionException {
		if(!out.output(key1) || !in.input(key2)) {
			throw new ConnectionException(out, key1, in, key2);
		}
		
		nodes.add(in);
		nodes.add(out);
		
		in.getMapper().setInput(out, key1, key2);
	}
	
	public void connect(Node in, String key, Object value) throws ConnectionException {
		if(!in.input(key)) {
			throw new ConnectionException(in, key);
		}

		nodes.add(in);

		in.getMapper().setConstants(key, value);
	}
	
	public String getName() {
		return name;
	}

	public Map<Integer, Map<String, Object>> exec() throws ValidationException {
		ExecutionEnvironment env = new ExecutionEnvironment(nodes);
		env.exec();
		return env.getOutputs();
	}
}
