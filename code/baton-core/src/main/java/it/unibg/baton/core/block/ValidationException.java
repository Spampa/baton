package it.unibg.baton.core.block;

import java.util.List;
import java.util.stream.Collectors;

import it.unibg.baton.core.field.FieldParseException;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private final transient List<FieldParseException> errors;

    public ValidationException(List<FieldParseException> errors) {
        super(buildMessage(errors));
        this.errors = List.copyOf(errors);
        errors.forEach(this::addSuppressed);
    }

    private static String buildMessage(List<FieldParseException> errors) {
        return errors.stream()
                .map(e -> " - " + e.getMessage())
                .collect(Collectors.joining("\n",
                        "%d invalid field(s):\n".formatted(errors.size()), ""));
    }

    public List<FieldParseException> getErrors() {
        return errors;
    }
}
