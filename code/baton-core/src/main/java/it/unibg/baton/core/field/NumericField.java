package it.unibg.baton.core.field;

import java.math.BigDecimal;

public final class NumericField implements Field<BigDecimal> { // final significa non estendibile da altre classi
    private final String name;
    private final String description;
    private final boolean isRequired;
	
	public NumericField(String name, String description, boolean isRequired) {
		this.name = name;
		this.description = description;
		this.isRequired = isRequired;
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
	public Class<BigDecimal> getType() {
		return BigDecimal.class;
	}
	
}
