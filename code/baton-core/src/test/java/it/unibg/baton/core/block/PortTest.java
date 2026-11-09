package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import it.unibg.baton.core.field.NumericField;

class PortTest {
    private final NumericField required = new NumericField("a", "Required", true);
    private final NumericField optional = new NumericField("b", "Optional", false);
    
    @Test
    void rejectsDuplicateFieldName() {
    	NumericField duplicate = new NumericField("a", "same name", true);
    	assertThrows(IllegalArgumentException.class, () -> new Port(List.of(required, duplicate)));
    }
    
    
}
