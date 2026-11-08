package it.unibg.baton.core.block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.Field;
import it.unibg.baton.core.field.FieldParseException;

public class Port {
	private final HashMap<String, Field<?>> fieldsMap;
	
	public Port(List<Field<?>> fields) throws IllegalArgumentException {
		this.fieldsMap = generateFieldsMap(fields);
	}
	
	private HashMap<String, Field<?>> generateFieldsMap(List<Field<?>> fields) {
		HashMap<String, Field<?>> seen = new HashMap<String, Field<?>>();
		
		for(Field<?> field : fields) {
			if(seen.put(field.getName(), field) != null) {
				throw new IllegalArgumentException("Duplicated %s field name" + field.getName());
			}
		}
		
		return seen;
	}
	
	public Map<String, Object> validate(Map<String, Object> values) throws ValidationException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<FieldParseException> errors = new ArrayList<FieldParseException>();
		
		for(Field<?> field : fieldsMap.values()) {
			Object value = values.get(field.getName());
			
			if(value == null) {
				if(field.isRequired()) {
					errors.add(new FieldParseException(field, null, "no value provided"));
				}
			} else {
				try {
					result.put(field.getName(), field.coerce(value));
				} catch (FieldParseException e) {
					errors.add(e);
				}
			}
		}
		
		if (!errors.isEmpty()) throw new ValidationException(errors);
		return result;
	}
	
	public Collection<Field<?>> getFields() { 
		return fieldsMap.values(); 
	}
}
