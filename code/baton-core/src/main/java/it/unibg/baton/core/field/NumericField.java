package it.unibg.baton.core.field;

import java.math.BigDecimal;
import java.math.BigInteger;

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
	
	@Override
	public BigDecimal coerce(Object o) throws FieldParseException {
	    return switch (o) {
	        case BigDecimal bd -> bd;
	        case BigInteger bi -> new BigDecimal(bi);
	        case Double d when d.isNaN() || d.isInfinite() ->
	            throw new FieldParseException(this, d.toString(), "not a finite number");
	        case Float f when f.isNaN() || f.isInfinite() ->
	            throw new FieldParseException(this, f.toString(), "not a finite number");
	        case Number n -> new BigDecimal(n.toString());
	        default -> super.coerce(o);
	    };
	}
	
}
