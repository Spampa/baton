package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import it.unibg.baton.core.field.NumericField;
import it.unibg.baton.core.field.Option;
import it.unibg.baton.core.field.SelectField;
import it.unibg.baton.core.field.TextField;

class PortTest {
    private final NumericField required = new NumericField("a", "Required", true);
    private final NumericField optional = new NumericField("b", "Optional", false);
    
    @Test
    void rejectsDuplicateFieldName() {
    	NumericField duplicate = new NumericField("a", "same name", true);
    	assertThrows(IllegalArgumentException.class, () -> new Port(List.of(required, duplicate)));
    }
    
    @Test
    void nestedValidation() throws ValidationException {
    	SelectField select = new SelectField(
    			"select", null, 
    			false, 
    			List.of(new Option("opt1", null, List.of(required, optional)), new Option("opt2", null, List.of(required, optional)))
    		);
    	
    	Port p = new Port(List.of(select));
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("select", "opt1");
    	m.put("select.opt1.a", new BigDecimal(45));
    	
    	Map<String, Object> validate = p.validate(m);
    	assertEquals(new BigDecimal(45), validate.get("select.opt1.a"));
    }
    
    @Test
    void rejectMissingNestedField() throws ValidationException {
    	// campi della porta
    	SelectField select = new SelectField(
    			"select", null, 
    			false, 
    			List.of(new Option("opt1", null, List.of(required, new TextField("missing", null, true))))
    		);
    	
    	Port p = new Port(List.of(select));
    	
    	// input utente
    	Map<String, Object> in = new HashMap<String, Object>();
    	in.put("select", "opt1");
    	in.put("select.opt1.a", new BigDecimal(45));
    	// mancante select.opt1.missing
    	
    	assertThrows(ValidationException.class, () -> p.validate(in));
    }
}
