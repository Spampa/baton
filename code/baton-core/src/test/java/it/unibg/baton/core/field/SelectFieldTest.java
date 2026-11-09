package it.unibg.baton.core.field;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class SelectFieldTest {
	
	@Test
	void correctCreation() {
		new SelectField(
			"test", "same name",
			false,
			List.of(
					new Option("numerics", "option with only numeric fields", 
							List.of(
								new NumericField("a", "first", true),
								new NumericField("b", "second", true)
							)
						),
					new Option("text and number", "option that allow a text and a numeric field",
						List.of(
							new TextField("text", "first", true),
							new NumericField("number", "second", false)
						)
					)
				)	
		);
	}

	@Test
	void rejectsDuplicateOptionsName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new SelectField("test", "same name",
					false,
					List.of(
						new Option("a", "test"),
						new Option("a", "test")
					)
			)
		);
	}
	
	@Test
	void rejectsDuplicateNestedFieldsName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new SelectField("test", "same name",
					false,
					List.of(
						new Option("a", "test", List.of(new NumericField("a", null, false), new NumericField("a", null, false))),
						new Option("b", "test")
					)
			)
		);
	}

}
