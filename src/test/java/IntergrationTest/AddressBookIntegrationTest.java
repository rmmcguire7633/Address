package IntergrationTest;

import AddressBook.AddressBook;
import AddressBook.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class AddressBookIntegrationTest {

    private Person personStub;

    @InjectMocks
    AddressBook addressBook;

    @Mock
    Person personMock;

    @BeforeEach
    public void setUp() {
        personStub = new Person("Homer", "Simpson", "1234 springfield", "Fort Myers",
                "FL", "12345","1234567890");
    }

    @Test
    void testStubAddPerson() {
        addressBook.add(personStub);
        Assertions.assertEquals(personStub, addressBook.get(0));
    }

    @Test
    void testMockAddPerson () {
        addressBook.add(personMock);
        Assertions.assertSame(personMock,addressBook.get(0));
    }

    @Test
    void testGetPersonsList() {
        // Arrange
        // Setup Array of type Person with 3 elements of Mocked person Objects.
        Person personMockArray[] = new Person[]{
                personMock,personMock,personMock
        };

        // Act
        //Create an address book with 3 mocked Person Objects
        addressBook.add(personMock);
        addressBook.add(personMock);
        addressBook.add(personMock);

        // Assert
        // Check to see if calling getPersons() within address book returns an Array[3] of Mocked Person Objects
        assertArrayEquals(addressBook.getPersons(), personMockArray);
    }

    @Test
    void testStubSetPerson() {

        Person personTestArray[] = new Person[] {
                personStub,personStub,personStub
        };

        // Adds personMock to Address Book then sets those indexes to Person stub
        addressBook.add(personMock);
        addressBook.add(personMock);
        addressBook.add(personMock);
        addressBook.set(0,personStub);
        addressBook.set(1,personStub);
        addressBook.set(2,personStub);

        // Verifies those original values have been set to new values of addressStubs
        Assertions.assertEquals(personStub, addressBook.get(0));
        Assertions.assertEquals(personStub, addressBook.get(1));
        Assertions.assertEquals(personStub, addressBook.get(2));

        // Verifies that the newly set address book array is equal to the expected array
        Assertions.assertArrayEquals(personTestArray, addressBook.getPersons());
    }

    @Test
    void testSetPerson() {
        Person personMockArray[] = new Person[]{
                personMock,personMock,personMock
        };

        // Creates address book table
        addressBook.add(personMock);
        addressBook.add(personMock);
        addressBook.add(personMock);
        // Sets new values to address book array
        addressBook.set(0,personMock);
        addressBook.set(1,personMock);
        addressBook.set(2,personMock);

        assertEquals(personMock, addressBook.get(0));
        assertEquals(personMock, addressBook.get(1));
        assertEquals(personMock, addressBook.get(2));
        assertArrayEquals(personMockArray, addressBook.getPersons());
    }

    @Test
    void testRemovePerson () {
        // Arrange
        // Create an address Book
        addressBook.add(personMock);
        addressBook.add(personMock);
        addressBook.add(personMock);

        // Act
        // Remove Person objects from address book
        addressBook.remove(1);

        assertEquals(addressBook.get(0), personMock);
        assertEquals(addressBook.get(1), personMock);

    }

    @Test
    void testGetStubPerson() {
        addressBook.add(personStub);
        addressBook.add(personStub);

        Assertions.assertEquals(personStub, addressBook.get(0));
        Assertions.assertEquals(personStub, addressBook.get(1));

    }

    @Test
    void testGetRowCount() throws Exception {
        addressBook.add(personMock);
        addressBook.add(personMock);
        Assertions.assertEquals(2, addressBook.getRowCount());
    }

    @Test
    void testGetColumnCount () {
        addressBook.add(personStub);

        Assertions.assertEquals(7, addressBook.getColumnCount());
    }

    @Test
    void testGetValueAt () {
        addressBook.add(personStub);
        addressBook.add(personStub);

        Assertions.assertEquals("Homer", addressBook.getValueAt(0,1));
        Assertions.assertEquals("Homer", addressBook.getValueAt(1,1));
        Assertions.assertEquals("Simpson", addressBook.getValueAt(0,0));
        Assertions.assertEquals("Simpson", addressBook.getValueAt(1,0));
    }

    @Test
    void testGetColumnName () {
        addressBook.add(personStub);

        Assertions.assertEquals("Last Name",addressBook.getColumnName(0));
        Assertions.assertEquals("First Name",addressBook.getColumnName(1));
        Assertions.assertEquals("Address",addressBook.getColumnName(2));
        Assertions.assertEquals("City",addressBook.getColumnName(3));
        Assertions.assertEquals("State",addressBook.getColumnName(4));
        Assertions.assertEquals("ZIP",addressBook.getColumnName(5));
        Assertions.assertEquals("Phone",addressBook.getColumnName(6));

    }
}