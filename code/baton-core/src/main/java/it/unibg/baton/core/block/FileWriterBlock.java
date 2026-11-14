package it.unibg.baton.core.block;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.Map;

import it.unibg.baton.core.field.TextField;

public class FileWriterBlock extends Block {

    private static final TextField filePathField = new TextField("filePath", "Path of the file to write", false);
    private static final TextField contentField = new TextField("ContentToWrite", "Content to write to the file", true);

    public FileWriterBlock() {
        super(
            "FileWriterBlock",
            "This block allows you to write a string to a file. If the file does not exist it will be created, "
            + "if it exists it will be overwritten. If path is omitted, it defaults to the user's Desktop.",
            List.of(filePathField, contentField),
            List.of()
        );
    }

    @Override
    protected Map<String, Object> exec(Input in) {
        String filePathStr = in.getText("filePath");
        String content = in.getText("ContentToWrite");

        Path path;
        if (filePathStr == null || filePathStr.isBlank()) {
            //desktop dell'utente se non specificato
            String userHome = System.getProperty("user.home");
            path = Path.of(userHome, "Desktop", "output.txt");
        } else {
            path = Path.of(filePathStr);
        }

        try {
            
            Files.writeString(path, content != null ? content : "");
            
            return Map.of();
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to file: " + e.getMessage(), e);
        }
    }
}