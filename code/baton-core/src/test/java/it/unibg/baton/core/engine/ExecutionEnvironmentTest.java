package it.unibg.baton.core.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibg.baton.core.block.OperationalBlock;
import it.unibg.baton.core.block.ValidationException;

class ExecutionEnvironmentTest {

	@Test
	void serialSumFromWorkflow() throws ConnectionException, ValidationException {
		Workflow wf = new Workflow("serial-sum");

		Node first = new Node(new OperationalBlock());
		Node second = new Node(new OperationalBlock());

		// first: 2 + 3 = 5
		wf.connect(first, "operation", "add"); // forced value
		wf.connect(first, "number1", new BigDecimal(2));
		wf.connect(first, "number2", new BigDecimal(3));

		// second: first.result + 10 = 15
		wf.connect(second, "operation", "add"); // forced value
		wf.connect(first, "result", second, "number1");
		wf.connect(second, "number2", new BigDecimal(10));

		Map<Integer, Map<String, Object>> out = wf.exec();
		assertEquals(new BigDecimal("5"), out.get(first.getId()).get("result"));
		assertEquals(new BigDecimal("15"), out.get(second.getId()).get("result"));
	}
}
