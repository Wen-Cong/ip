import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A utility class for handling file-based operations, specifically for managing a list of tasks.
 * This class uses the `java.nio.file` API for file system access.
 */
public class FileServices {
    private final Path path;

    /**
     * Constructs a {@code FileServices} instance to operate on a file at the specified path.
     * The file does not need to exist when this object is created.
     *
     * @param path The string representation of the file path.
     */
    public FileServices(String path) {
        this.path = Paths.get(path);
    }

    /**
     * Writes a list of tasks to the file specified during construction.
     * <p>
     * The method first ensures that the file and its parent directories exist.
     * It then iterates through the provided task list, converts each task into
     * a string representation suitable for file storage, and writes the combined
     * string to the file, overwriting its contents.
     *
     * @param taskList The list of {@code Task} objects to be written to the file.
     * @throws IOException If an I/O error occurs during file or directory creation,
     *                     or during the writing process.
     */
    public void writeToFile(List<Task> taskList) throws IOException {
        // Check if file exist. Create path + file if it doesn't exist
        ensureFileExists();

        // Format task list into String for writing to file
        StringBuilder data = new StringBuilder();
        for (Task task : taskList) {
            data.append(task.toFileString());
        }

        // Write to data to file
        Files.writeString(path, data.toString());
    }

    /**
     * Ensures that the file specified by the {@code path} and its parent directories exist.
     * If the file does not exist, it creates the necessary parent directories and then creates
     * an empty file.
     *
     * @throws IOException If an I/O error occurs while creating directories or the file.
     */
    private void ensureFileExists() throws IOException {
        if (!Files.exists(path)) {
            // Create parent folder directories if it doesn't exist
            Files.createDirectories(path.getParent());

            // Create an empty file
            Files.createFile(path);
        }
    }
}

