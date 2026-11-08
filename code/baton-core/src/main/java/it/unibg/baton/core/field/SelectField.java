package it.unibg.baton.core.field;
import java.util.List;

public final class SelectField implements Field<String> {
    private final String name;
    private final String description;
    private final boolean isRequired;
    
    private final List<String> options;
	
	public SelectField(String name, String description, List<String> options, boolean isRequired) {
        if (options == null || options.isEmpty()) {
        	throw new IllegalArgumentException("SelectField requires at least one option");
        } 
        if (options.stream().distinct().count() != options.size()) {
        	throw new IllegalArgumentException("SelectField options must be unique");
        }  

        this.name = name;
        this.description = description;
        this.options = List.copyOf(options);
        this.isRequired = isRequired;
    }

    @Override
    public String parse(String raw) throws FieldParseException {
        if (raw == null || raw.isBlank()) {
        	throw new FieldParseException(this, raw, "value is empty");
        }

        String value = raw.trim();
        if (!options.contains(value)) {
            throw new FieldParseException(this, raw,
            		"not a valid option (allowed: " + String.join(", ", options) + ")");
        }

        return value;
    }

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isRequired() {
		return isRequired;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}
	
}
