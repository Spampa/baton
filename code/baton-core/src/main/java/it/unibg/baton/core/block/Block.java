package it.unibg.baton.core.block;

import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.Field;

public abstract class Block {
	private final String name;
	private final String description;
	
	private final Port inputPort;
	private final Port outputPort;
	
	public Block(String name, String description, List<Field<?>> inputs, List<Field<?>> outputs) {
		this.name = name;
		this.description = description;
		this.inputPort = new Port(inputs);
		this.outputPort = new Port(outputs);
	}
	
	public Port getInputPort() {
		return inputPort;
	}

	public Port getOutputPort() {
		return outputPort;
	}

	// trattiamo i field come hashmap di String, Object dove String è il nome del capo
	public final Map<String, Object> run(Map<String, Object> values) throws ValidationException {
	    Map<String, Object> out = exec(new Input(inputPort.validate(values), this.inputPort.getIndex()));
	    if(out == null && outputPort != null) {
	    	throw new IllegalStateException("Block '%s' returned null from exec()".formatted(name));
	    }
	    return outputPort.validate(out);
	}
	
	protected abstract Map<String, Object> exec(Input in);

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	// get the value with cast
	protected static <T> T get(Map<String, Object> in, Field<T> field) {
	    return field.getType().cast(in.get(field.getName()));
	}
}
