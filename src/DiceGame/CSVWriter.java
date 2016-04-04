package DiceGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A {@code CSVWriter} provides the framework for writing data to a Comma
 * Separated Value (CSV) file. CSV files are a simple method of keeping data in
 * a table.
 * <p>
 * Created on Apr 4, 2016.
 *
 * @author Wes Hampson
 */
public class CSVWriter extends FileWriter
{
    private int cellIndex;
    
    /**
     * Creates a new {@code CSVWriter} instance that will write to the specified
     * {@code File}.
     * @param f a the file to write
     * @throws IOException if the {@code File} object points to a file that is
     *         inaccessible or a directory
     */
    public CSVWriter(File f) throws IOException
    {
        super(f);
        cellIndex = 0;
    }
    
    /**
     * Creates a new {@code CSVWriter} instance that will write to the specified
     * {@code File}.
     * @param f a the file to write
     * @param append bytes will be written to the end of the file if
     *        {@code true}, otherwise the current contents of the file will be
     *        overwritten
     * @throws IOException if the {@code File} object points to a file that is
     *         inaccessible or a directory
     */
    public CSVWriter(File f, boolean append) throws IOException
    {
        super(f, append);
        cellIndex = 0;
    }
    
    /**
     * Creates a new {@code CSVWriter} instance that will write to the specified
     * {@code File}.
     * @param filePath the path to the file to write
     * @throws IOException if the {@code File} object points to a file that is
     *         inaccessible or a directory
     */
    public CSVWriter(String filePath) throws IOException
    {
        super(filePath);
        cellIndex = 0;
    }
    
    /**
     * Creates a new {@code CSVWriter} instance that will write to the specified
     * {@code File}.
     * @param filePath the path to the file to write
     * @param append bytes will be written to the end of the file if
     *        {@code true}, otherwise the current contents of the file will be
     *        overwritten
     * @throws IOException if the {@code File} object points to a file that is
     *         inaccessible or a directory
     */
    public CSVWriter(String filePath, boolean append) throws IOException
    {
        super(filePath, append);
        cellIndex = 0;
    }
    
    /**
     * Writes the string representation of an object to the CSV file as a
     * single cell. If the string contains a comma, it will be surrounded by
     * quotation marks to avoid writing a false delimiter.
     * 
     * @param cell the object to write
     * @throws IOException if an I/O error occurs
     */
    public void writeCell(Object cell) throws IOException
    {
        // Get cell object as a string
        String cellStr = (cell == null) ? "null" : cell.toString();

        // Surround cell data with quotes if it contains a comma
        if (cellStr.contains(",")) {
            cellStr = "\"" + cellStr + "\"";
        }
        
        // Append a comma to the previous cell
        // Don't prepend a comma if cell is first in the row
        if (cellIndex != 0) {
            cellStr = ',' + cellStr;
        }
        
        // Write the cell data to the file
        cellIndex++;
        write(cellStr);
    }
    
    /**
     * Moves to the next line in the file.
     * 
     * @throws IOException if an I/O error occurs
     */
    public void writeLine() throws IOException
    {
        cellIndex = 0;
        write("\n");
    }
    
    /**
     * Writes a collection of objects to the file as strings, each separated by
     * a comma, then moves to the next line in the file.
     * 
     * @param cells the objects to write to the file
     * @throws IOException if an I/O error occurs
     */
    public void writeRow(Object... cells) throws IOException
    {
        // Write all cells in row
        for (Object cell : cells) {
            writeCell(cell);
        }
        
        // Move to next line
        writeLine();
    }
}
