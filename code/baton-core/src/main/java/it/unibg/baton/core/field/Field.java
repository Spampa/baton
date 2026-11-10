package it.unibg.baton.core.field;

public abstract sealed class Field<T> permits NumericField, TextField, BooleanField, SelectField {
    private final String name;
    private final String description;
    private final boolean required;
    private final Class<T> type;

    protected Field(String name, String description, boolean required, Class<T> type) {
        if (name == null || name.isBlank() || name.contains(".") || name.contains(" ")) {
            throw new IllegalArgumentException("Invalid field name: " + name);
        }
        this.name = name;
        this.description = description;
        this.required = required;
        this.type = type;
    }

    public final String getName() { return name; }
    public final String getDescription() { return description; }
    public final boolean isRequired() { return required; }
    public final Class<T> getType() { return type; }
	
	public abstract T parse(String raw) throws FieldParseException;
	
	public T coerce(Object o) throws FieldParseException {
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
