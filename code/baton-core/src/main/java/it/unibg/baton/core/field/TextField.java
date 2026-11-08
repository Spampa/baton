package it.unibg.baton.core.field;

public final class TextField implements Field<String> {
    private final String name;
    private final String description;
    private final boolean isRequired;
	
	public TextField(String name, String description, boolean isRequired) {
		this.name = name;
		this.description = description;
		this.isRequired = isRequired;
	}

	@Override
	public String parse(String raw) throws FieldParseException {
	    if (raw == null || raw.isBlank()) {
	    	throw new FieldParseException(this, raw, "value is empty");
	    }
	    
		return raw;
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

	@Override
	public String coerce(Object value) throws FieldParseException {
	    return parse(String.valueOf(value));   // qualsiasi valore va bene come testo così da peremttere il parsaing automatico di valori numerici in testo
	}
}
