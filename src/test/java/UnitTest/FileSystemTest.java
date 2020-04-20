package UnitTest;

import AddressBook.AddressBook;
import AddressBook.FileSystem;
import AddressBook.Person;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {

    private AddressBook addressBook = new AddressBook();
    private FileSystem fs = new FileSystem();
    private File filepath = new File("FakeDB.db");

    @Test
    void readFile_Should_Throw_FileNotFoundException_With_Incorrect_FilePath_That_does_Not_Exist() throws FileNotFoundException, SQLException {
        File filepath = new File("thisIsNotAPAth");

        assertThrows(FileNotFoundException.class, () -> {fs.readFile(addressBook, filepath);});
    }

    @Test
    void readFile_Should_NotThrow_SQL_Exception_When_Connecting_To_A_Valid_db_File() {

        assertDoesNotThrow(() -> fs.readFile(addressBook, filepath));
    }

    @Test
    void saveFile_Should_Save_Person_class_in_addressBook() throws SQLException, FileNotFoundException {
        Person person = new Person("Ryan", "McGuire", "123 8th street", "Fort Myers",
                "FL", "33605", "8675309");

        addressBook.add(person);

        // set up the data
        fs.saveFile(addressBook, filepath);

        // make sure the data was saved by reading it
        fs.readFile(addressBook, filepath);

        Person[] personarray = addressBook.getPersons();

        assertEquals("McGuire", personarray[0].getLastName());
        assertEquals("Ryan", personarray[0].getFirstName());
        assertEquals("123 8th street", personarray[0].getAddress());
        assertEquals("Fort Myers", personarray[0].getCity());
        assertEquals("FL", personarray[0].getState());
        assertEquals("33605", personarray[0].getZip());
        assertEquals("8675309", personarray[0].getPhone());

        // make sure there was only one person added
        int count = 0;
        for (int i = 0; i < personarray.length; i++) {
            count++;
        }

        assertEquals(1, count);
    }
}
