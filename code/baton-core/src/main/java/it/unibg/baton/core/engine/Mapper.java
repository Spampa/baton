package it.unibg.baton.core.engine;

import java.util.HashMap;
import java.util.Map;

class Mapper {
	private Map<Integer, Map<String, String>> inputs;
	private Map<String, Object> constants;
	
	public Mapper() {
		inputs = new HashMap<Integer, Map<String,String>>();
		constants = new HashMap<String, Object>();
	}
	
	// node -> { inField -> outField }
	public void setInput(Node out, String outKey, String inKey) {
		inputs.computeIfAbsent(out.getId(), id -> new HashMap<String, String>()).put(inKey, outKey);
	}
	
	public void setConstants(String key, Object value) {
		constants.put(key, value);
	}
	
	public Map<Integer, Map<String, String>> getInputs() {
		return Map.copyOf(inputs);
	}
	
	public Map<String, Object> getConstants() {
		return Map.copyOf(constants);
	}
	
	protected int getDependencies() {
		return inputs.size();
	}
}
