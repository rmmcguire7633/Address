package UnitTest;

import AddressBook.AddressBook;
import AddressBook.AddressBookController;
import AddressBook.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

// note we do not go into great detail with testing this class because it uses the addressbook with the parameters
public class AddressBookControllerTest {
    private AddressBook addressBook = new AddressBook();
    private AddressBookController ac = new AddressBookController(addressBook);
    private static Person p1 = new Person("James", "Hood", "1234 springfield", "Fort Myers",
            "FL", "12345","1234567890");
    private static Person p2 = new Person("Homer", "Simpson", "1234 springfield", "Fort Myers",
            "FL", "12345","1234567890");
    private static Person p3 = new Person("Frank", "Simpson", "1234 springfield", "Fort Myers",
            "FL", "12345","1234567890");

    // Making sure we can set the constructor
    @Test
    void AddressBookController_Constructor_Should_Be_Set_To_AddressBook_Passed_In () {
        AddressBookController addressBookController = new AddressBookController(addressBook);
    }

    // here we use the addressBook that was passed into the controller to see if the controller added the persons
    @Test
    void add_Should_Add_Person_To_AddressBook_In_AddressBookController() {
        ac.add(p1);
        ac.add(p2);
        Person[] personArray = new Person[] {p1, p2};

        // make sure the correct persons are retrieved
        assertArrayEquals(personArray, addressBook.getPersons());
    }

    @Test
    void set_Should_Add_Person_To_Specific_Index() {
        // add 3 people to make sure there are 3 people in the controller at all times
        ac.add(p1);
        ac.add(p2);
        ac.add(p3);

        Person newPerson = new Person("Ryan", "Billy", "", "", ""
        , "", "");

        ac.set(1, newPerson);

        // here we check if the person was added to the correct index
        assertEquals(newPerson, ac.get(1));
    }

    @Test
    void remove_Should_Remove_Person_From_Index() {
        // we add two persons to set up the test
        ac.add(p1);
        ac.add(p2);

        // we remove the first person added
        ac.remove(0);

        // we check if the person at the index is not the one we removed
        assertNotEquals(p1, ac.get(0));

        // here we check that there is only one person since we started with two
        Person[] personArray = addressBook.getPersons();

        int count = 0;
        for (int i = 0; i < personArray.length; i++) {
            count++;
        }

        assertEquals(1, count);
    }

    @Test
    void get_Should_Get_Specific_Person() {
        // here we add three persons to set up the test
        ac.add(p1);
        ac.add(p2);
        ac.add(p3);

        // we get the person at index 0
        Person actualPerson = addressBook.get(0);

        assertEquals(p1, actualPerson);
    }

    @Test
    void clear_Should_Remove_All_Persons_From_addressBook() {
        // here we add three persons to set up the test
        ac.add(p1);
        ac.add(p2);
        ac.add(p3);

        // make sure there are 3 persons in the addressbook
        int count = 0;
        for(int i = 0; i < addressBook.getPersons().length; i++) {
            count++;
        }
        assertEquals(3, count);

        // here we clear and see if all persons were removed
        ac.clear();

        count = 0;
        for(int i =0; i < addressBook.getPersons().length; i++) {
            count++;
        }

        assertEquals(0, count);
    }

    @Test
    void open_Should_not_Throw_Exception_With_Valid_db_Path() {
        File path = new File("FakeDB.db");

        assertDoesNotThrow(() -> ac.open(path));
    }
}
