package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

class StringManipulationBlockTest {

	@Test
	void test() throws ValidationException{
		
		StringManipulationBlock block = new StringManipulationBlock();
		
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
		
		//Concatenazione - risultato atteso: "Hello World"
		
		System.out.println(block.run(Map.of(
				"string1", "Hello ",
				"string2", "World",
				"operation", "concatenate"
		)));
		
		//substring - risultato atteso: "lo"
		
		System.out.println(block.run(Map.of(
				"string1", "Hello World",
				"string2", "3,5",
				"operation", "substring"
		)));
		
		//toUpperCase - risultato atteso: "HELLO WORLD"
		
		System.out.println(block.run(Map.of(
				"string1", "Hello World",
				
				"operation", "toUpperCase"
		)));
		
		//toLowerCase - risultato atteso: "hello world"
		
		System.out.println(block.run(Map.of(
				"string1", "Hello World",
				
				"operation", "toLowerCase"
		)));
		
		//occurrence - risultato atteso: 3
		
		System.out.println(block.run(Map.of(
				"string1", "Hello World, Hello Universe, Hello Galaxy",
				"string2", "Hello",
				"operation", "occurrence"
		)));
		
		
		
		
		
	}

}
