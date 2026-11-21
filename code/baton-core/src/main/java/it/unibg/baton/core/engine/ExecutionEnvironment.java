package it.unibg.baton.core.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.unibg.baton.core.block.ValidationException;

class ExecutionEnvironment {
	private final Set<Node> nodes;

	private final Map<Integer, Map<String, Object>> outputs;

	public ExecutionEnvironment(Set<Node> nodes) {
		this.nodes = new HashSet<Node>(nodes);
		this.outputs = new HashMap<Integer, Map<String, Object>>();
	}

	public void exec() throws ValidationException {
		while (nodes.size() > 0) {
			boolean progress = false;
			Iterator<Node> it = nodes.iterator();

			while (it.hasNext()) {
				Node n = it.next();
				if (!isReady(n)) {
					continue;
				}

				outputs.put(n.getId(), n.run(resolveInputs(n)));
				it.remove();
				progress = true;
			}

			if (!progress) {
				throw new IllegalStateException("Unresolvable dependencies (cycle?) among nodes: " + nodes);
			}
		}
	}

	private boolean isReady(Node n) {
		return outputs.keySet().containsAll(n.getMapper().getInputs().keySet());
	}

	private Map<String, Object> resolveInputs(Node n) {
		Map<String, Object> in = new HashMap<String, Object>(n.getMapper().getConstants());

		for (Map.Entry<Integer, Map<String, String>> dep : n.getMapper().getInputs().entrySet()) {
			Map<String, Object> produced = outputs.get(dep.getKey());

			for (Map.Entry<String, String> link : dep.getValue().entrySet()) {
				in.put(link.getKey(), produced.get(link.getValue()));
			}
		}
		return in;
	}

	public Map<Integer, Map<String, Object>> getOutputs() {
		return Map.copyOf(outputs);
	}
}
