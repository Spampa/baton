package it.unibg.baton.core.block;

import java.util.List;
import java.util.Map;


import it.unibg.baton.core.field.SelectField;
import it.unibg.baton.core.field.TextField;

public class StringManipulationBlock extends Block {
	
	// input due stringhe
	
	private static final TextField string1 = new TextField("string1", "String to manipulate", true);
	private static final TextField string2 = new TextField("string2", "", true);
	
	//input operazioni da effettuare sulle due stringhe
	private static final String CONCATENATE = "concatenate";
	private static final String SUBSTRING = "substring";
	private static final String TO_UPPERCASE = "toUpperCase";
	private static final String TO_LOWERCASE = "toLowerCase";
	private static final String OCCURRENCE = "occurrence";
	
	//output fields
	
	private static final TextField resultField = new TextField("result", "Result of the string manipulation operation", true);
	
	private static final SelectField operationField = new SelectField("operation", "Select the operation to perform on the strings", 
			List.of(CONCATENATE, SUBSTRING, TO_UPPERCASE, TO_LOWERCASE, OCCURRENCE), true
	);
	
	
	
	public StringManipulationBlock() {
		
		super("StringManipulationBlock", 
				"This block allows you to perform basic string manipulation operations "
				+ "(concatenation, substring extraction, and case conversion, occurrence of words or letters) on two string inputs.",
				List.of(string1, string2,operationField), //input fields
				List.of(resultField)  //output fields
		);
	}
	
	@Override
	protected Map<String, Object> exec(Map<String, Object> in) {

		switch (get(in, operationField)) {
			
			case CONCATENATE: 
				return Map.of(resultField.getName(), get(in, string1) + get(in, string2));
				
			case SUBSTRING:
				// da modificare forse in futuro per gestire meglio gli indici, per ora si assume che string2 contenga due numeri separati da una virgola
				String[] indices = get(in, string2).split(",");
				int start = Integer.parseInt(indices[0].trim());
				int end = Integer.parseInt(indices[1].trim());
				return Map.of(resultField.getName(), get(in, string1).substring(start, end));
				
			case TO_UPPERCASE:
				return Map.of(resultField.getName(), get(in, string1).toUpperCase());
				
			case TO_LOWERCASE:
				return Map.of(resultField.getName(), get(in, string1).toLowerCase());
				
			case OCCURRENCE:
				// conta le occorrenze di string2 in string1
				String str1 = get(in, string1);
				String str2 = get(in, string2);
				int count = str1.split(str2, -1).length - 1;
				return Map.of(resultField.getName(), String.valueOf(count));
				
			default:
				return null;
		
		}
		
	}

}
