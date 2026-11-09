package it.unibg.baton.core.field;

public final class BooleanField extends Field<Boolean> {
	public BooleanField(String name, String description, boolean isRequired) {
		super(name, description, isRequired, Boolean.class);
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
}
