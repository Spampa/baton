package it.unibg.baton.core;
//test block

import java.math.BigDecimal;
import java.util.Map;

import it.unibg.baton.core.block.OperationalBlock;
import it.unibg.baton.core.block.ValidationException;




public class Main {

	public static void main(String[] args) throws ValidationException {
		
		
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
		
		System.out.println(block.run(Map.of(
				"number1", new BigDecimal(42),
				"number2", "50",
				"operation", "add +"
		)));
		
		
		
		
		
		
		
	}

}
