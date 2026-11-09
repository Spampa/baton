package it.unibg.baton.core.block;

import java.util.List;
import java.util.Map;


import it.unibg.baton.core.field.TextField;

public class FileWriterBlock extends Block{
	
	//input del path del file e del contenuto da scrivere
	private static final TextField filePathField = new TextField("filePath", "Path of the file to write", true);
	private static final TextField contentField = new TextField("ContentToWrite", "Content to write to the file", true);
	
	//output fields
	
	private static final TextField errorField = new TextField("error", "Indicates a problem in manipulating the file", false);
	
	public FileWriterBlock() {
		super("FileWriterBlock", 
				"This block allows you to write a string to a file, if the file does not exist it will be created, if it exists it will be overwritten, the path is mandatory even if the file does not exist.",
				List.of(filePathField, contentField), //input fields
				List.of(errorField)  //output fields
				
		);
	}

	@Override
	protected Map<String, Object> exec(Map<String, Object> in) {
		// se il file dell path specificato non esiste lo creo, se esiste lo sovrascrivo con il contenuto specificato
		String filePath = get(in, filePathField);
		String content = get(in, contentField);
		try {
			
			java.nio.file.Files.writeString(java.nio.file.Path.of(filePath), content); // da gestire il caso in cui il file è in cartelle non esistenti
			return null;
		} catch (Exception e) {
			return Map.of(errorField.getName(), "Error writing to file: " + e.getMessage());
		}
		
		
	}

}
