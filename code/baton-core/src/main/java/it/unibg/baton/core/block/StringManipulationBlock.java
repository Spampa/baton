package it.unibg.baton.core.block;

import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.Field;
import it.unibg.baton.core.field.NumericField;
import it.unibg.baton.core.field.Option;
import it.unibg.baton.core.field.SelectField;
import it.unibg.baton.core.field.TextField;

public class StringManipulationBlock extends Block {
	
	//input di almeno una stringa da manipolare
	private static final TextField string1 = new TextField("string1", "String to manipulate", true);
	
	//input operazioni da effettuare sulla stringa con aggiunta di cami opzionali 
	//(nel caso di substring, ad esempio, servono due campi obbligatori per gli indici di inizio e fine)
	private static final String CONCATENATE = "concatenate";
	private static final String SUBSTRING = "substring";
	private static final String TO_UPPERCASE = "toUpperCase";
	private static final String TO_LOWERCASE = "toLowerCase";
	private static final String OCCURRENCE = "occurrence";
	
	private static final NumericField index1 = new NumericField("index1", "index of the first letter of the substring", true);
	private static final NumericField index2 = new NumericField("index2", "index of the last letter of the substring", true);
	
	// string2 non è obbligatoria per tutte le operazioni
	
	private static final TextField string2 = new TextField("string2", null, false);
	
	
	private static final List<Field<?>> string2Fields = List.of(string2);// lista per la seconda stringa per i campi che la richiedono
	
	private static final Option OPT1 = new Option(CONCATENATE, "Concatenate the two strings", List.of(new TextField("string2", "String to concatenate with the first string", true)));
	private static final Option OPT2 = new Option(SUBSTRING, "Extract a substring from the first string", List.of(index1, index2));
	private static final Option OPT3 = new Option(TO_UPPERCASE, "Convert all the letter to upper case of the first string, "
	+ "if there is a second string passed, then every equals string to the string passed is converted to uppercase", string2Fields);  //nel caso di toUpperCase se passo una seconda scritta tutte le lettere e parole nella prima che sono identiche alla seconda diventano to upper
	private static final Option OPT4 = new Option(TO_LOWERCASE, "Convert all the letter to lower case of the first string, "
	+ "if there is a second string passed, then every equals string to the string passed is converted to lowercase", string2Fields); //nel caso di toLowerCase se passo una seconda scritta tutte le lettere e parole nella prima che sono identiche alla seconda diventano to lower
	private static final Option OPT5 = new Option(OCCURRENCE, "Count the occurrences of the second string in the first string", string2Fields);
	
	//list of options to pass to the select field
	private static final List<Option> options = List.of(OPT1, OPT2, OPT3, OPT4, OPT5);
	
	private static final SelectField operationField = new SelectField("operation", "Select the operation to perform on the strings", 
			true, options
	);
	
	//output 
	
	private static final TextField resultField = new TextField("result", "Result of the string manipulation operation", true);
	
	
	public StringManipulationBlock() {
		
		super("StringManipulationBlock", 
				"This block allows you to perform basic string manipulation operations "
				+ "(concatenation, substring extraction, and case conversion, occurrence of words or letters) on two string inputs.",
				List.of(string1, operationField), //input fields
				List.of(resultField)  //output fields
		);
	}


	@Override
	protected Map<String, Object> exec(Input in) {
		
		switch (in.getText("operation")) {
		
			case CONCATENATE: 
				
				return Map.of("result", in.getText("string1") + in.getText("operation."+ CONCATENATE + ".string2"));
				
				
			case SUBSTRING:
				
				String str = in.getText("string1");
				int start = in.getNumeric("operation."+ SUBSTRING + ".index1").intValue();
				int end = in.getNumeric("operation."+ SUBSTRING + ".index2").intValue();
				return Map.of("result", str.substring(start, end));
				
				
			case TO_UPPERCASE:
				
				String strToUpper = in.getText("string1");
				String strToUpper2 = in.getText("operation."+ TO_UPPERCASE + ".string2");
				if (strToUpper2 != null && !strToUpper2.isEmpty()) {
					strToUpper = strToUpper.replace(strToUpper2, strToUpper2.toUpperCase());
				} else {
					strToUpper = strToUpper.toUpperCase();
				}
				return Map.of("result", strToUpper);
				
				
			case TO_LOWERCASE:
				
				String strToLower = in.getText("string1");
				String strToLower2 = in.getText("operation."+ TO_LOWERCASE + ".string2");
				if (strToLower2 != null && !strToLower2.isEmpty()) {
					strToLower = strToLower.replace(strToLower2, strToLower2.toLowerCase());
				} else {
					strToLower = strToLower.toLowerCase();
				}
				return Map.of("result", strToLower);
				
				
			case OCCURRENCE:
				
				String str1 = in.getText("string1");
				String str2 = in.getText("operation."+ OCCURRENCE + ".string2");
				int count = str1.split(str2, -1).length - 1;
				return Map.of("result", String.valueOf(count));
				
				
			default:
				throw new IllegalStateException("unexpected option");
		}
	}

}
