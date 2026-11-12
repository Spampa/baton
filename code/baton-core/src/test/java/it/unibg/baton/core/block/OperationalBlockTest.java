package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;

class OperationalBlockTest {

	@Test
	void test() throws ValidationException {
		
		OperationalBlock block = new OperationalBlock();
		
		// Sommatoria: mi aspetto 92
		Map<String, Object> resAdd = block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "add"
		));
		assertEquals(new BigDecimal("92"), resAdd.get("result"));
		
		// Sottrazione: mi aspetto -8
		Map<String, Object> resSub = block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "subtract"
		));
		assertEquals(new BigDecimal("-8"), resSub.get("result"));
		
		// Moltiplicazione: mi aspetto 2100
		Map<String, Object> resMult = block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "multiply"
		));
		assertEquals(new BigDecimal("2100"), resMult.get("result"));
		
		// Divisione: mi aspetto 0.84
		Map<String, Object> resDiv = block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "divide"
		));
		assertEquals(new BigDecimal("0.84"), resDiv.get("result"));
		
		// Divisione per zero: ArithmeticException
		assertThrows(ArithmeticException.class, () -> {
			block.run(Map.of(
					"number1", new BigDecimal(42),
					"number2", "0",
					"operation", "divide"
			));
		});
	}
}