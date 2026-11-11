package it.unibg.baton.core.block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.Field;
import it.unibg.baton.core.field.FieldParseException;
import it.unibg.baton.core.field.Option;
import it.unibg.baton.core.field.SelectField;

public class Port {
	private final List<Field<?>> fields; // lista dei campi della porta
	private final Map<String, Field<?>> index; // indicizzazione dei campi usando notazione padre.figlio
	
	public Port(List<Field<?>> fields) throws IllegalArgumentException {
		this.fields = fields;
		Map<String, Field<?>> index = new LinkedHashMap<String, Field<?>>();
		generateIndexFields(fields, "", index);
		this.index = index;
	}
	
	private void generateIndexFields(List<Field<?>> fields, String prefix, Map<String, Field<?>> index) {
		for(Field<?> field : fields) {
			String key = prefix.isEmpty() ? field.getName() : prefix + "." + field.getName();
			
			if(index.putIfAbsent(key, field) != null) {
				throw new IllegalArgumentException("Duplicated %s field name" + field.getName());
			}
			
			if(field instanceof SelectField select) {
				for(Option option : select.getOptions()) {
					generateIndexFields(option.fields(), key + "." + option.name(), index);
				}
			}
		}
	}
	
	public Map<String, Object> validate(Map<String, Object> values) throws ValidationException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<FieldParseException> errors = new ArrayList<FieldParseException>();
		
		
		validateFields(fields, values, "", result, errors);
		
		if (!errors.isEmpty()) throw new ValidationException(errors);
		return result;
	}
	
	private String key(String prefix, Field<?> f) { // figlio.nome come nei JSON per garantire univocità nei nomi dei campi annidati
		return prefix.isEmpty() ? f.getName() : prefix + "." + f.getName();
	}
	
	private void validateFields(
			Collection<Field<?>> fields , Map<String, Object> values, 
			String prefix, 
			Map<String, Object> result, List<FieldParseException> errors
	) throws ValidationException {
		for(Field<?> field : fields) {
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
				
				if(field instanceof SelectField selected) {
					validateFields(selected.fieldFor((String) value), values, key + "." + value, result, errors); // ricorsione sul metodo per i field annidati
					// value in questo caso è il nome della selezione effettuata es: "opt1"
				}
			} catch (FieldParseException e) {
				errors.add(e);
			}
		}
	}
	
	public Collection<Field<?>> getFields() { 
		return fields; 
	}
	
	public Map<String, Field<?>> getIndex() {
		return index;
	}
}
