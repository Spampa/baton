package it.unibg.baton.core.field;

import java.math.BigDecimal;

public final class NumericField extends Field<BigDecimal> { // final significa non estendibile da altre classi
	public NumericField(String name, String description, boolean isRequired) {
		super(name, description, isRequired, BigDecimal.class);
	}

	@Override
	public BigDecimal parse(String raw) throws FieldParseException {
	    if (raw == null || raw.isBlank()) {
	    	throw new FieldParseException(this, raw, "value is empty");
	    }
	    try {
	        return new BigDecimal(raw.trim());
	    } catch (NumberFormatException e) {
	        throw new FieldParseException(this, raw,
	                "not a valid number (use '.' as decimal separator)", e);
	    }
	}
	
}
