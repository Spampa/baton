package it.unibg.baton.core.field;

public class FieldParseException extends Exception {
    private static final long serialVersionUID = 1L;

    private final transient Field<?> field;
    private final String rawValue;

    public FieldParseException(Field<?> field, String rawValue, String reason) {
        this(field, rawValue, reason, null);
    }

    public FieldParseException(Field<?> field, String rawValue, String reason, Throwable cause) {
        super("Field '%s': %s (valore: \"%s\")".formatted(field.getName(), reason, rawValue), cause);
        this.field = field;
        this.rawValue = rawValue;
    }

    public Field<?> getField() { return field; }
    public String getRawValue() { return rawValue; }
}