package it.unibg.baton.core.field;

public sealed interface Field<T> permits NumericField, TextField, BooleanField, SelectField {
	public String getName();
	public String getDescription();
	public boolean isRequired();
	Class<T> getType();
	
	public T parse(String raw) throws FieldParseException;
	
	default T coerce(Object o) throws FieldParseException {
		if(o instanceof String s) { // priorità al parsing in raw questo permette alla select di funzionare sempre
			return parse(s);
		} if(getType().isInstance(o)) {
			return getType().cast(o);
		}
		
		throw new FieldParseException(this, String.valueOf(o),
                "expected %s but got %s".formatted(
                        getType().getSimpleName(), o.getClass().getSimpleName()));
	}
}
