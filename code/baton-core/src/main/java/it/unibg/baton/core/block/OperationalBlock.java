package it.unibg.baton.core.block;

import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.NumericField;
import it.unibg.baton.core.field.SelectField;

public class OperationalBlock extends Block{
	//stringhe per definire l operazione da effettuare con i due numeri
	private static final String ADD = "add +";
	private static final String SUBTRACT = "subtract -";
	private static final String MULTIPLY = "multiply *";
	private static final String DIVIDE = "divide /";
	
	
	//due numeri in input da usare come calcolo
	private static final NumericField number1Field = new NumericField("number1", null, true);
	private static final NumericField number2Field = new NumericField("number2", null, true);
	//input per selezionare l operazione da effettuare tra i due numeri
	private static final SelectField operationField = new SelectField("operation", null, List.of(ADD, SUBTRACT, MULTIPLY, DIVIDE), true);
	
	private static final NumericField resultField = new NumericField("result", null, true); //è obbligatorio avere un result dopo il calcolo

	public OperationalBlock() {
		super("OperationalBlock", 
				"This block allows you to perform basic arithmetic operations "
				+ "(addition, subtraction, multiplication, and division) on two numeric inputs.",
				List.of(number1Field, number2Field, operationField),
				List.of(resultField) 	
		);
		
	}

	@Override
	protected Map<String, Object> exec(Map<String, Object> in) {
		
		switch (get(in, operationField)) {
			
			case ADD: 
				return Map.of(resultField.getName(), get(in, number1Field).add(get(in, number2Field)));
				
			case SUBTRACT:
				return Map.of(resultField.getName(), get(in, number1Field).subtract(get(in, number2Field)));
				
			case MULTIPLY:
				return Map.of(resultField.getName(), get(in, number1Field).multiply(get(in, number2Field)));
				
			case DIVIDE:
				return Map.of(resultField.getName(), get(in, number1Field).divide(get(in, number2Field)));
				
			default:
				return null;
		
		}
		
	}
	
	
	
	
	
	

}
