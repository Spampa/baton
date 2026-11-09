package it.unibg.baton.core.field;

import java.util.List;

public record Option(String name, String description, List<Field<?>> fields) {
    public Option {
        if (fields.stream().map(Field::getName).distinct().count() != fields.size()) {
        	throw new IllegalArgumentException("Nested field in option must be unique");
        }
    	fields = List.copyOf(fields); 
    }
    
    public Option(String name, String description) { 
    	this(name, description, List.of());
    }
}
