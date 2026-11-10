package it.unibg.baton.core.field;
import java.util.List;

public final class SelectField extends Field<String> {
    private final List<Option> options;
	
	public SelectField(String name, String description, boolean isRequired, List<Option> options) {
		super(name, description, isRequired, String.class);
		
        if (options == null || options.isEmpty()) {
        	throw new IllegalArgumentException("SelectField requires at least one option");
        } 
        if (options.stream().distinct().count() != options.size()) {
        	throw new IllegalArgumentException("SelectField options must be unique");
        }
        
        this.options = List.copyOf(options);
    }

    @Override
    public String parse(String raw) throws FieldParseException {
        if (raw == null || raw.isBlank()) {
        	throw new FieldParseException(this, raw, "value is empty");
        }
        
        String value = raw.trim();
        if (!this.contains(value)) {
            throw new FieldParseException(this, raw,
            		"not a valid option (allowed: " + String.join(", ", getOptionNames()) + ")");
        }

        return value;
    }
    
    private boolean contains(String name) {
    	for(Option option : options) {
    		if(option.name().equals(name)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public List<String> getOptionNames() {
        return options.stream().map(Option::name).toList();
    }
    
    public List<Option> getOptions() {
        return options;
    }
    
    public List<Field<?>> fieldFor(String fieldName) {
    	for(Option option : options) {
    		if(option.name() == fieldName) {
    			return option.fields();
    		}
    	}
    	return List.of(); // empty list
    }
	
}
