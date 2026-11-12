package it.unibg.baton.core.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileWriterBlockTest {

	// JUnit crea questa cartella temporanea in automatico e la distrugge a fine test
	@TempDir
	Path tempDir;

	@Test
	void testSuccessfulWrite() throws Exception {
		
		FileWriterBlock block = new FileWriterBlock();
		
		// 1. Definisco il path del file all'interno della cartella temporanea sicura
		Path filePath = tempDir.resolve("test_output.txt");
		String contentToTest = "Contenuto generato dal test di Baton";
		
		// 2. Eseguo il blocco
		Map<String, Object> result = block.run(Map.of(
				"filePath", filePath.toString(),
				"ContentToWrite", contentToTest
		));
		
		// 3. Verifica: il metodo exec() ritorna null
		assertNull(result, "Il risultato deve essere null come definito nel blocco");
		
		// 4. Verifica: il file è stato effettivamente creato nel file system?
		assertTrue(Files.exists(filePath), "Il file dovrebbe esistere nel percorso specificato");
		
		// 5. Verifica: il contenuto scritto dentro al file è corretto?
		String writtenContent = Files.readString(filePath);
		assertEquals(contentToTest, writtenContent, "Il contenuto del file non corrisponde a quello atteso");
	}

	@Test
	void testWriteError_FolderDoesNotExist() {
		
		FileWriterBlock block = new FileWriterBlock();
		
		// Creo un path verso una sottocartella che non esiste per scatenare appositamente un errore
		String invalidPath = tempDir.resolve("cartella_inventata/test_output.txt").toString();
		String contentToTest = "Non scriverà mai questo contenuto";
		
		// Mi aspetto che il tuo blocco catturi l'eccezione nativa e lanci la tua IllegalStateException
		IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
			block.run(Map.of(
					"filePath", invalidPath,
					"ContentToWrite", contentToTest
			));
		});
		
		// Verifico anche che il messaggio di errore contenga il prefisso che hai impostato tu
		assertTrue(exception.getMessage().startsWith("Error writing to file:"));
	}
}

//da fixare