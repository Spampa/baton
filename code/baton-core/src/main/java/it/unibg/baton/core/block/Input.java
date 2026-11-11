package it.unibg.baton.core.block;

import java.math.BigDecimal;
import java.util.Map;

import it.unibg.baton.core.field.Field;

public class Input { // input sempre validati essendo generato dopo la validazione nel block
	private final Map<String, Object> values; // mappa piatta dei campi con key = padre.figlio
	private final Map<String, Field<?>> index; // indicizzazione dei field con key = padre.figlio
	
	protected Input(Map<String, Object> values, Map<String, Field<?>> index) {
		this.values = values;
		this.index = index;
	}
	
    public String getText(String key)      { return get(key, String.class); }
    public BigDecimal getNumeric(String key)  { return get(key, BigDecimal.class); }
    public boolean getBoolean(String key)    { return get(key, Boolean.class); }
	
    private <T> T get(String key, Class<T> type) {
        Field<?> field = index.get(key);
        if (field == null)
            throw new IllegalArgumentException("Unknown input: " + key); // refuso nel path
        if (!type.equals(field.getType()))
            throw new IllegalArgumentException(
                    "%s is %s, not %s".formatted(key, field.getType().getSimpleName(), type.getSimpleName()));
        return type.cast(values.get(key)); //valori già validati
    }
}
