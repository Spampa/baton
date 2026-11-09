package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;

class OperationalBlockTest {

	@Test
	void test() throws ValidationException{
		
		OperationalBlock block = new OperationalBlock();
		
		System.out.println("Block name: " + block.getName());
		System.out.println("Block description: " + block.getDescription());
		
		System.out.println("Input fields:");
		block.getInputPort().getFields().forEach(field -> {
			System.out.println("- " + field.getName() + " (type: " + field.getType().getSimpleName() + ")");
		});
		
		System.out.println("Output fields:");
		block.getOutputPort().getFields().forEach(field -> {
			System.out.println("- " + field.getName() + " (type: " + field.getType().getSimpleName() + ")");
		});
		
		//Sommatoria - risultato atteso: 92
		
		System.out.println(block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "add +"
		)));
		
		//Sottrazione - risultato atteso: -8
		
		System.out.println(block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "subtract -"
		)));
		
		//Moltiplicazione - risultato atteso: 2100
		
		System.out.println(block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "multiply *"
		)));
		
		//Divisione - risultato atteso: 0.84
		
		System.out.println(block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "divide /"
		)));
		
		//Divisione per zero - risultato atteso: eccezione di divisione per zero //TODO: gestire eccezione di divisione per zero
		
		
		
	
	}

}
