package it.unibg.baton.core.block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.Field;
import it.unibg.baton.core.field.FieldParseException;
import it.unibg.baton.core.field.SelectField;

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
		Map<String, Object> result = new HashMap<String, Object>();
		List<FieldParseException> errors = new ArrayList<FieldParseException>();
		
		
		validateFields(fieldsMap.values(), values, "", result, errors);
		
		if (!errors.isEmpty()) throw new ValidationException(errors);
		return result;
	}
	
	private String key(String prefix, Field<?> f) { // figlio.nome come nei JSON per garantire univocità nei nomi dei campi annidati
		return prefix.isEmpty() ? f.getName() : prefix + "." + f.getName();
	}
	
	private void validateFields(
			Collection<Field<?>> fields , Map<String, Object> values, String prefix, 
			Map<String, Object> result, List<FieldParseException> errors
	) throws ValidationException {
		for(Field<?> field : fieldsMap.values()) {
			String key = key(prefix, field); // crea la nuova chiave con aggiunta di . in base al nodo nidificato visitato
			Object raw = values.get(key);
			
			if(raw == null) {
				if(field.isRequired()) {
					errors.add(new FieldParseException(field, null, "no value provided"));
				}
				continue;
			}
			
			try {
				Object value = field.coerce(raw); // parsing del valore se non parsato, non siamo a conoscenza del suo tipo esatto
				result.put(key, value);
				
				if(value instanceof SelectField selected) {
					validateFields(selected.fieldFor((String) value), values, key, result, errors); // ricorsione sul metodo per i field annidati
				}
			} catch (FieldParseException e) {
				errors.add(e);
			}
		}
	}
	
	public Collection<Field<?>> getFields() { 
		return fieldsMap.values(); 
	}
}
