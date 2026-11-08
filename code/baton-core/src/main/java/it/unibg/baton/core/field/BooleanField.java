package it.unibg.baton.core.field;

public final class BooleanField implements Field<Boolean> {
    private final String name;
    private final String description;
    private final boolean isRequired;
	
	public BooleanField(String name, String description, boolean isRequired) {
		this.name = name;
		this.description = description;
		this.isRequired = isRequired;
	}
	
	@Override
	public Boolean parse(String raw) throws FieldParseException {
	    if (raw == null || raw.isBlank()) {
	    	throw new FieldParseException(this, raw, "value is empty");
	    }
	    
	    return switch (raw.trim().toLowerCase()) {
	        case "true", "yes", "1" -> true;
	        case "false", "no", "0" -> false;
	        default -> throw new FieldParseException(this, raw,
	                "not a valid boolean (expected 'true' or 'false')");
	    };
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
	public Class<Boolean> getType() {
		return Boolean.class;
	}

}
