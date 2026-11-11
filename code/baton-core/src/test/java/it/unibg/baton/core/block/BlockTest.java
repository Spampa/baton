package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibg.baton.core.field.Field;
import it.unibg.baton.core.field.Option;
import it.unibg.baton.core.field.SelectField;
import it.unibg.baton.core.field.TextField;

class BlockTest {
	
	private static class DummyBlock extends Block {
		private static final List<Field<?>> PARAMS = List.of(new TextField("foo", null, true), new TextField("bar", null, true));
		
		private static final Option OPT1 = new Option("a", null, PARAMS);
		private static final Option OPT2 = new Option("b", null, PARAMS);
		
		private static final SelectField SELECT = new SelectField("select", "", true, List.of(OPT1, OPT2));
		
		public DummyBlock() {
			super("Dummy", "test block", List.of(SELECT), List.of(new TextField("result", "", true)));
		}

		@Override
		protected Map<String, Object> exec(Input in) {
			return switch(in.getText("select")) {
				case "a" -> Map.of("result", in.getText("select.a.foo") + in.getText("select.a.bar"));
				case "b" -> Map.of("result", in.getText("select.b.bar") + in.getText("select.b.foo"));
				default -> throw new IllegalStateException("unexpected option");
			};
		}
		
	}
	
	@Test
	void blockWithNestedField() throws ValidationException {
		DummyBlock b = new DummyBlock();
		Map<String, Object> in = new HashMap<String, Object>();
		
		in.put("select", "b");
		
		in.put("select.b.foo", "hello");
		in.put("select.b.bar", "world!");

		assertEquals("world!hello", b.run(in).get("result"));
	}

}
