package it.unibg.baton.core.engine;

import java.util.Map;

import it.unibg.baton.core.block.Block;
import it.unibg.baton.core.block.ValidationException;

class Node {
	private static int nodeCounter = 0;
	
	private final int id;
	private final Block block;
	private final Mapper mapper;
	
	public Node(Block block) {
		this.block = block;
		this.id = nodeCounter;
		this.mapper = new Mapper();
		Node.nodeCounter++;
	}
	
	public Mapper getMapper() {
		return mapper;
	}
	
	public boolean input(String key) {
		return block.input(key);
	}
	
	public boolean output(String key) {
		return block.output(key);
	}
	
	public int getId() {
		return this.id;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public int getDependencies() {
		return mapper.getDependencies();
	}
	
	public Map<String, Object> run(Map<String, Object> inputs) throws ValidationException {
		return block.run(inputs);
	}
	
	@Override
	public String toString() {
		return block.getName() + ":node-" + id;
	}
}
