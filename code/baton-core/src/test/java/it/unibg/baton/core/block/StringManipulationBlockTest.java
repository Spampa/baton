package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;

class StringManipulationBlockTest {

	@Test
	void test() throws ValidationException {
		
		StringManipulationBlock block = new StringManipulationBlock();
		
		// 1. Test CONCATENATE
		Map<String, Object> concatResult = block.run(Map.of(
				"string1", "Hello",
				"operation", "concatenate",
				"operation.concatenate.string2", " World"
		));
		assertEquals("Hello World", concatResult.get("result"));
		
		
		// 2. Test SUBSTRING (nota: passo BigDecimal per simulare un input numerico generico)
		Map<String, Object> substringResult = block.run(Map.of(
				"string1", "Ingegneria Informatica",
				"operation", "substring",
				"operation.substring.index1", new BigDecimal(0),
				"operation.substring.index2", new BigDecimal(10)
		));
		assertEquals("Ingegneria", substringResult.get("result"));
		
		
		// 3. Test TO_UPPERCASE (Normale)
		Map<String, Object> upperResult = block.run(Map.of(
				"string1", "ciao mondo",
				"operation", "toUpperCase"
		));
		assertEquals("CIAO MONDO", upperResult.get("result"));
		
		
		// 4. Test TO_UPPERCASE (Con string2 per conversione selettiva)
		Map<String, Object> upperSelectiveResult = block.run(Map.of(
				"string1", "ciao mondo, ciao a tutti",
				"operation", "toUpperCase",
				"operation.toUpperCase.string2", "ciao"
		));
		assertEquals("CIAO mondo, CIAO a tutti", upperSelectiveResult.get("result"));
		
		
		// 5. Test TO_LOWERCASE (Normale)
		Map<String, Object> lowerResult = block.run(Map.of(
				"string1", "JAVA REACT BATON",
				"operation", "toLowerCase"
		));
		assertEquals("java react baton", lowerResult.get("result"));
		
		
		// 6. Test TO_LOWERCASE (Con string2 per conversione selettiva)
		Map<String, Object> lowerSelectiveResult = block.run(Map.of(
				"string1", "PROGETTO DI INGEGNERIA",
				"operation", "toLowerCase",
				"operation.toLowerCase.string2", "INGEGNERIA"
		));
		assertEquals("PROGETTO DI ingegneria", lowerSelectiveResult.get("result"));
		
		
		// 7. Test OCCURRENCE
		Map<String, Object> occurrenceResult = block.run(Map.of(
				"string1", "banana",
				"operation", "occurrence",
				"operation.occurrence.string2", "na"
		));
		assertEquals("2", occurrenceResult.get("result"));
		
		
		// 8. Test opzione non valida (se supportata dal field type validation)
		// Ci aspettiamo che lanci un'eccezione se passiamo un'operazione non prevista
		assertThrows(Exception.class, () -> {
			block.run(Map.of(
					"string1", "test",
					"operation", "operazione_inesistente"
			));
		});
	}
}