package it.unibg.baton.core.field;

public final class TextField extends Field<String> {
	public TextField(String name, String description, boolean isRequired) {
		super(name, description, isRequired, String.class);
	}

	@Override
	public String parse(String raw) throws FieldParseException {
	    if (raw == null || raw.isBlank()) {
	    	throw new FieldParseException(this, raw, "value is empty");
	    }
	    
		return raw;
	}

	@Override
	public String coerce(Object value) throws FieldParseException {
	    return parse(String.valueOf(value));   // qualsiasi valore va bene come testo così da peremttere il parsaing automatico di valori numerici in testo
	}
}
