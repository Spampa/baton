package it.unibg.baton.core.block;

import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.NumericField;
import it.unibg.baton.core.field.Option;
import it.unibg.baton.core.field.SelectField;

public class OperationalBlock extends Block{
	//stringhe per definire l operazione da effettuare con i due numeri
	private static final String ADD = "add";
	private static final String SUBTRACT = "subtract";
	private static final String MULTIPLY = "multiply";
	private static final String DIVIDE = "divide";
	
	
	//due numeri in input da usare come calcolo
	private static final NumericField number1Field = new NumericField("number1", "First number for the operation", true);
	private static final NumericField number2Field = new NumericField("number2", "Second number for the operation", true);
	
	//input per selezionare l operazione da effettuare tra i due numeri
	private static final Option OPT1 = new Option(ADD, "Add the two numbers");
	private static final Option OPT2 = new Option(SUBTRACT, "Subtract the two numbers");
	private static final Option OPT3 = new Option(MULTIPLY, "Multiply the two numbers");
	private static final Option OPT4 = new Option(DIVIDE, "Divide the two numbers");
	private static final SelectField operationField = new SelectField("operation", "Select the operation to perform", true, List.of(OPT1, OPT2, OPT3, OPT4));
	
	
	private static final NumericField resultField = new NumericField("result", "Result of the specify operation", true); //è obbligatorio avere un result dopo il calcolo

	public OperationalBlock() {
		super("OperationalBlock", 
				"This block allows you to perform basic arithmetic operations "
				+ "(addition, subtraction, multiplication, and division) on two numeric inputs.",
				List.of(operationField, number1Field, number2Field),
				List.of(resultField) 	
		);
	}

	@Override
	protected Map<String, Object> exec(Input in) {
		switch (in.getText("operation")) {
		
		case ADD: 
			return Map.of("result", in.getNumeric("number1").add(in.getNumeric("number2")));
			
		case SUBTRACT:
			return Map.of("result", in.getNumeric("number1").subtract(in.getNumeric("number2")));
			
		case MULTIPLY:
			return Map.of("result", in.getNumeric("number1").multiply(in.getNumeric("number2")));
			
		case DIVIDE:
			return Map.of("result", in.getNumeric("number1").divide(in.getNumeric("number2")));
			
		default:
			throw new IllegalStateException("unexpected option");
		}
	}

	
	
	
	
	
	
	
	

}
