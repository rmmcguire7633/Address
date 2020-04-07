package AddressBook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    // these are the templates of persons we will be using to run test
    private static Person p1 = new Person("James", "Hood", "1234 springfield", "Fort Myers",
            "FL", "12345","1234567890");
    private static Person p2 = new Person("Homer", "Simpson", "1234 springfield", "Fort Myers",
            "FL", "12345","1234567890");
    private static Person p3 = new Person("Frank", "Simpson", "1234 springfield", "Fort Myers",
            "FL", "12345","1234567890");
    private AddressBook addressBook = new AddressBook();
    private static Person[]  personAr1 = new Person[] {p1};
    private static Person[]  personAr2 = new Person[] {p1, p2};
    private static Person[] personAr3 = new Person[] {p1,p2,p3};

    @Test
    void getPersons_Should_Return_All_Persons_In_AddressBook() {
        addressBook.add(p1);
        addressBook.add(p2);

        // make sure the correct persons are retrieved
        assertArrayEquals(personAr2,addressBook.getPersons());

        // make sure the incorrect persons are not retrieved
        assertNotEquals(personAr3, addressBook.getPersons());
    }

    @Test
    void getPersons_Should_Return_Null_If_Persons_Is_Null() {
        addressBook.add(null);

        Person[] actualPerson = addressBook.getPersons();

        assertNull(actualPerson[0]);
    }

    // here we are testing that the right person is being added
    @ParameterizedTest
    @MethodSource("GetValidInputForAdd")
    void add_Should_Add_Person_To_AddressBook(Person[] persons) {

        // add all persons to the address book
        for (int i = 0; i < persons.length; i++) {
            addressBook.add(persons[i]);
            // check if the address book increases in size when adding a person
            assertEquals(i + 1, addressBook.getPersons().length);
        }

        // check if each person was added to the address book
        for (int i = 0; i < persons.length; i++)
        {
            assertEquals(persons[i], addressBook.get(i));
        }
    }

    private static Stream<Arguments> GetValidInputForAdd() {
        return Stream.of(
                Arguments.of((Object) new Person[] {}),
                Arguments.of((Object) new Person[] {p1}),
                Arguments.of((Object) new Person[] {p1, p2}),
                Arguments.of((Object) new Person[] {p1, p2, p3}),
                Arguments.of((Object) new Person[] {p3, p2, p1}),
                Arguments.of((Object) new Person[] {p3, p1})
        );
    }

    @Test
    void add_Should_Add_Null_Person_When_Null_Is_Passed_In() {
        addressBook.add(null);

        Person actualPerson = addressBook.get(0);

        assertNull(actualPerson);
    }

    @ParameterizedTest
    @MethodSource("GetPersonsForSetTest")
    void set_Should_Add_A_Person_To_AddressBook_At_The_Index_passed_In(Person[] persons, int[] expectedIndex) {
        // here we add people to the address just to make sure there are 3 persons in the address book at all times
        addressBook.add(p1);
        addressBook.add(p2);
        addressBook.add(p3);

        // sets all the persons into the address book
        for (int i = 0; i < persons.length; i++) {
            addressBook.set(expectedIndex[i], persons[i]);
        }

        // here we check if the person was added to the correct index
        // we do this after setting all the persons so we can look at the address book as a hole
        for (int i = 0; i < persons.length; i++) {
            assertEquals(persons[i], addressBook.get(expectedIndex[i]));
        }
    }

    private static Stream<Arguments> GetPersonsForSetTest () {
        return Stream.of(
                Arguments.of((Object) new Person[] {null}, (Object) new int[] {1}),
                Arguments.of((Object) new Person[] {}, (Object) new int[] {1}),
                Arguments.of((Object) new Person[] {p1, p2, p3}, (Object) new int[]{1, 0, 2}),
                Arguments.of((Object) new Person[] {p2, p3}, (Object) new int[]{2, 1}),
                Arguments.of((Object) new Person[] {p2}, (Object) new int[] {0})
        );
    }

    @ParameterizedTest
    @MethodSource("GetValidIndexToRemoveForRemoveTest")
    void remove_Should_Remove_The_Person_From_AddressBook_At_Index_Passed_In (int[] index, Person[] expectedPersons) {
        // here we add person to the address book to make sure there are 3 persons in the address book to start
        addressBook.add(p1);
        addressBook.add(p2);
        addressBook.add(p3);

        int addressBookSize = 3;

        // remove each person from the address book
        for (int i = 0; i < index.length; i++) {
            addressBook.remove(index[i]);

            // check if the address book decreased in size
            assertEquals(--addressBookSize, addressBook.getPersons().length);
        }

        // check to see if the right persons were deleted from the address book
        assertArrayEquals(expectedPersons, addressBook.getPersons());
    }

    private static Stream<Arguments> GetValidIndexToRemoveForRemoveTest() {
        return Stream.of(
                Arguments.of((Object) new int[] {2,1,0}, (Object) new Person[] {}),
                Arguments.of((Object) new int[] {2, 0}, (Object) new Person[] {p2}),
                Arguments.of((Object) new int [] {1}, (Object) new Person[] {p1, p3})
        );
    }

    @Test
    void remove_Should_Remove_A_Null_Person() {
        addressBook.add(null);

        // check to see if the null person was added the address book
        assertNull(addressBook.get(0));
        assertEquals(1, addressBook.getPersons().length);

        addressBook.remove(0);

        // check to see if the null person was removed
        assertEquals(0, addressBook.getPersons().length);
    }

    @ParameterizedTest
    @MethodSource("GetValidPersonsForGetTest")
    void get_Should_Return_The_Specific_Person_In_The_AddressBook(int[] index, Person[] expectedPerson) {
        // here we add person to the address book to make sure there are 3 persons in the address book to start
        addressBook.add(p1);
        addressBook.add(p2);
        addressBook.add(p3);

        // get each person
        for (int i = 0; i < index.length; i++) {
            Person person = addressBook.get(index[i]);

            // check to see if the person retrieved is the right person
            assertEquals(person, expectedPerson[i]);
        }
    }

    private static Stream<Arguments> GetValidPersonsForGetTest() {
        return Stream.of(
                Arguments.of((Object) new int[] {}, (Object) new Person[] {}),
                Arguments.of((Object) new int[] {0}, (Object) new Person[] {p1}),
                Arguments.of((Object) new int[] {0, 1}, (Object) new Person[] {p1, p2}),
                Arguments.of((Object) new int[] {0, 1, 2}, (Object) new Person[] {p1, p2, p3})
        );
    }

    //note you will never get full branch coverage from the clear method because persons can not be both null and empty
    @ParameterizedTest
    @MethodSource("GetPersonsForClearTest")
    void clear_Should_erase_All_Persons_From_AddressBook(Person[] persons) {
        // set the address book
        for (int i = 0; i < persons.length; i++) {
            addressBook.add(persons[i]);
        }

        // clear the address book and check if there are no persons in it
        addressBook.clear();
        int personInAddressBook = addressBook.getPersons().length;

        assertEquals(0, personInAddressBook);
    }

    private static Stream<Arguments> GetPersonsForClearTest() {
        return Stream.of(
                Arguments.of((Object) new Person[] {null}),
                Arguments.of((Object) new Person[] {}),
                Arguments.of((Object) new Person[] {p1}),
                Arguments.of((Object) new Person[] {p1, p2}),
                Arguments.of((Object) new Person[] {p1, p2, p3})
        );
    }

    @ParameterizedTest
    @MethodSource("GetValidPersonsForRowCountTest")
    void getRowCount_Should_Return_The_Number_Of_Persons_In_AddressBook (Person[] persons, int expectedCount) {
        // add all persons to the address book
        for (int i = 0; i < persons.length; i++) {
            addressBook.add(persons[i]);
        }

        int actualRowCount = addressBook.getRowCount();

        assertEquals(expectedCount, actualRowCount);
    }

    private static Stream<Arguments> GetValidPersonsForRowCountTest () {
        return Stream.of(
                Arguments.of((Object) new Person[] {null}, 1),
                Arguments.of((Object) new Person[] {}, 0),
                Arguments.of((Object) new Person[] {p1}, 1),
                Arguments.of((Object) new Person[] {p1, p2}, 2),
                Arguments.of((Object) new Person[] {p1, p2, p3}, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("GetValidPersonsForColumnCountAndColumnName")
    void getColumnCount_Should_Return_The_Number_Of_Columns_In_The_AddressBook (Person[] persons) {
        // add all persons to the address book
        for (int i = 0; i < persons.length; i++) {
            addressBook.add(persons[i]);
        }

        int actualColumnCount = addressBook.getColumnCount();

        // the column count should be the same regardless of how many persons are in the address book
        assertEquals(7, actualColumnCount);
    }

    private static Stream<Arguments> GetValidPersonsForColumnCountAndColumnName() {
        return Stream.of(
                Arguments.of((Object) new Person[] {null}),
                Arguments.of((Object) new Person[] {}),
                Arguments.of((Object) new Person[] {p1}),
                Arguments.of((Object) new Person[] {p1, p2}),
                Arguments.of((Object) new Person[] {p1, p2, p3})
        );
    }

    @ParameterizedTest
    @MethodSource("GetValidPersonsForGetValueTest")
    void getValueAt_Should_Return_The_Value_Of(Person[] persons, String[][] expectedValues) {
        // add all persons to the address book
        for (int i = 0; i < persons.length; i++) {
            addressBook.add(persons[i]);
        }

        // check each row and column to make sure its the correct value in the address book
        for (int i = 0; i < addressBook.getRowCount(); i++) {
            for (int j = 0; j < addressBook.getColumnCount(); j++) {
                String actualValue = addressBook.getValueAt(i, j).toString();
                assertEquals(expectedValues[i][j], actualValue);
            }
        }
    }

    private static Stream<Arguments> GetValidPersonsForGetValueTest() {
        return Stream.of(
                Arguments.of((Object) new Person[] {}, (Object) new String[][] {{""}}),
                Arguments.of((Object) new Person[] {p1}, (Object) new String[][] {{"Hood", "James",
                        "1234 springfield", "Fort Myers", "FL", "12345","1234567890"}}),
                Arguments.of((Object) new Person[] {p1, p2}, (Object) new String[][] {{"Hood", "James",
                        "1234 springfield", "Fort Myers", "FL", "12345","1234567890"}, {
                        "Simpson", "Homer", "1234 springfield", "Fort Myers",
                        "FL", "12345","1234567890"}
                })
        );
    }

    @Test
    void getValueAt_Should_Throw_Index_Out_of_Bound_Exception_When_Accessing_A_Value_That_Is_Not_Present() {
        // checks to if the exception is thrown
        // this will only return an exception if its an IndexOutOfBoundsException
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            addressBook.getValueAt(0,0);
        });


        // if the exception is not null it means it an index out of bound exception
        assertNotNull(exception);
    }

    @ParameterizedTest
    @MethodSource("GetValidPersonsForColumnCountAndColumnName")
    void getColumnName_Should_Return_The_Name_of_The_Column_of_The_Index_passed_In(Person[] persons) {
        // add all persons to the address book
        for (int i = 0; i < persons.length; i++) {
            addressBook.add(persons[i]);
        }

        String[] expectedResults = new String[] {"Last Name", "First Name", "Address", "City", "State", "ZIP", "Phone"};

        // check that each column is the correct name
        for (int i = 0; i < 7; i++) {
            String actualColumnName = addressBook.getColumnName(i);
            assertEquals(expectedResults[i], actualColumnName);
        }
    }

    @ParameterizedTest
    @MethodSource("GetInvalidInputForGetColumnNameTest")
    void getColumnName_Should_Throw_Index_Out_Of_Bounds_When_Passing_Less_Than_and_More_Than_7(int index) {
        // checks to if the exception is thrown
        // this will only return an exception if its an IndexOutOfBoundsException
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            addressBook.getColumnName(index);
        });

        // checks if the exception was thrown, if not the exception is null
        assertNotNull(exception);
    }

    private static Stream<Arguments> GetInvalidInputForGetColumnNameTest() {
        return Stream.of(
                Arguments.of(-2),
                Arguments.of(-1),
                Arguments.of(8),
                Arguments.of(9)
        );
    }
}