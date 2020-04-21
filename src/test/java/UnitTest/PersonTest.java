package UnitTest;

import AddressBook.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private static Person p1 = new Person("Homer", "Simpson", "1234 springfield", "Fort Myers",
            "FL", "12345","2391111111");

    private static Person p2 = new Person("119", "879", "58999", "6778",
            "589", "85789","2391111112");

    private static Person p3 = new Person("###", "####", "#12#", "#239#",
            "#$", "56324", "2391111113");

    private static Person personWithNullValues = new Person("Ryan", "McGuire", null, null,
            null, null, null);

    private static Person personWithEmptyValues = new Person("Ryan", "Bob", "", "", "",
            "", "");
    @Test
    void should_throw_exception_when_first_name_is_null()
    {
        // checks to if the exception is thrown
        // this will only return an exception if its an IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person noFirstName = new Person(null, "McGuire", null, null, null, null, null);
        });

        String expectedException = "First name cannot be empty";
        String actualException = exception.getMessage();

        // checks to see if the exception is the wright message
        assertTrue(actualException.contains(expectedException));
    }

    @Test
    void should_throw_exception_when_first_name_is_empty()
    {
        // checks to if the exception is thrown
        // this will only return an exception if its an IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person noFirstName = new Person("", "McGuire", null, null, null, null, null);
        });

        String expectedException = "First name cannot be empty";
        String actualException = exception.getMessage();

        // checks to see if the exception is the wright message
        assertTrue(actualException.contains(expectedException));
    }

    @Test
    void should_throw_exception_when_last_name_is_null()
    {
        // checks to if the exception is thrown
        // this will only return an exception if its an IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person noFirstName = new Person("Ryan", null, null, null, null, null, null);
        });

        String expectedException = "Last name cannot be empty";
        String actualException = exception.getMessage();

        // checks to see if the exception is the wright message
        assertTrue(actualException.contains(expectedException));
    }

    @Test
    void should_throw_exception_when_last_name_is_empty()
    {
        // checks to if the exception is thrown
        // this will only return an exception if its an IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person noFirstName = new Person("Ryan", "", null, null, null, null, null);
        });

        String expectedException = "Last name cannot be empty";
        String actualException = exception.getMessage();

        // checks to see if the exception is the wright message
        assertTrue(actualException.contains(expectedException));
    }

    // this test is using boundary testing by testing at the boundary of 5 to make sure it fails
    // also test negative input that should throw an exception
    @ParameterizedTest
    @MethodSource("GetInvalidDataForPersonZipTest")
    void Person_Should_Throw_IllegalArgumentException_If_Zip_Is_Not_Five_Character_Long_is_Negative(String zip) {
        assertThrows(IllegalArgumentException.class, () -> new Person("CoolKid", "Ryan", "",
                "", "", zip, ""));
    }

    private static Stream<Arguments> GetInvalidDataForPersonZipTest() {
        return Stream.of(
                Arguments.of("1234"),
                Arguments.of("123456"),
                Arguments.of("-1")
        );
    }

    @ParameterizedTest
    @MethodSource("GetInvalidDataForPersonPhoneTest")
    void Person_Should_Throw_IllegalArgument_If_Phone_Is_Not_Ten_Characters_Long_Or_Is_Negative(String phone) {
        assertThrows(IllegalArgumentException.class, () -> new Person("CoolKid", "McGuire", ""
        , "", "", "", phone));
    }

    private static Stream<Arguments> GetInvalidDataForPersonPhoneTest() {
        return Stream.of(
                Arguments.of("123456789"),
                Arguments.of("12345678911"),
                Arguments.of("-1")
        );
    }

    // since we all ready checked if the first name is null in our previous test,
    // we just need to see if this method returns the right input
    @ParameterizedTest
    @MethodSource("getValidNamesForGetFirstName")
    void getFirstName_Should_Return_First_Name_When_There_Is_A_FirstName(Person person, String expectedFirstName) {
        String firstName = person.getFirstName();

        assertEquals(expectedFirstName, firstName);
    }

    private static Stream<Arguments> getValidNamesForGetFirstName() {
        return Stream.of(
                Arguments.of(p1, "Homer"),
                Arguments.of(p2, "119"),
                Arguments.of(p3, "###")
        );
    }

    @ParameterizedTest
    @MethodSource("getValidNamesForGetLastLame")
    void getLastName_Should_Return_Last_Name_When_There_Is_A_LastName(Person person, String expectedLastName) {
        String lastName = person.getLastName();

        assertEquals(expectedLastName, lastName);
    }

    private static Stream<Arguments> getValidNamesForGetLastLame() {
        return Stream.of(
                Arguments.of(p1, "Simpson"),
                Arguments.of(p2, "879"),
                Arguments.of(p3, "####")
        );
    }

    @ParameterizedTest
    @MethodSource("getValidNamesForAddressThatAreNotEmptyOrNull")
    void getAddress_Should_Return_Address_When_There_Is_An_Address(Person person, String expectedAddress) {
        String address = person.getAddress();

        assertEquals(expectedAddress, address);
    }

    private static Stream<Arguments> getValidNamesForAddressThatAreNotEmptyOrNull() {
        return Stream.of(
                Arguments.of(p1, "1234 springfield"),
                Arguments.of(p2, "58999"),
                Arguments.of(p3, "#12#")
        );
    }

    @Test
    void getAddress_Should_Return_Null_If_Address_Is_Null() {
        String address = personWithNullValues.getAddress();

        assertNull(address);
    }

    @Test
    void getAddress_Should_Return_Empty_String_If_Address_IsEmpty() {
        String address = personWithEmptyValues.getAddress();

        assertEquals("", address);
    }

    @ParameterizedTest
    @MethodSource("GetValidNamesThatAreNotEmptyOrNullForCity")
    void getCity_Should_return_City_When_There_Is_A_City(Person person, String expected) {
        String city = person.getCity();

        assertEquals(expected, city);
    }

    private static Stream<Arguments> GetValidNamesThatAreNotEmptyOrNullForCity(){
        return Stream.of(
                Arguments.of(p1, "Fort Myers"),
                Arguments.of(p2, "6778"),
                Arguments.of(p3, "#239#")
        );
    }

    @Test
    void getCity_Should_Return_Null_If_City_Is_Null() {
        String city = personWithNullValues.getCity();

        assertNull(city);
    }

    @Test
    void getCity_Should_Return_Empty_String_If_City_IsEmpty() {
        String city = personWithEmptyValues.getCity();

        assertEquals("", city);
    }

    @ParameterizedTest
    @MethodSource("GetValidNamesThatAreNotEmptyOrNullForState")
    void getCity_Should_return_State_When_There_Is_A_State(Person person, String expected) {
        String state = person.getState();

        assertEquals(expected, state);
    }

    private static Stream<Arguments> GetValidNamesThatAreNotEmptyOrNullForState(){
        return Stream.of(
                Arguments.of(p1, "FL"),
                Arguments.of(p2, "589"),
                Arguments.of(p3, "#$")
        );
    }

    @Test
    void getState_Should_Return_Null_If_State_Is_Null() {
        String state = personWithNullValues.getState();

        assertNull(state);
    }

    @Test
    void getState_Should_Return_Empty_String_If_State_IsEmpty() {
        String state = personWithEmptyValues.getState();

        assertEquals("", state);
    }

    @ParameterizedTest
    @MethodSource("GetValidNamesThatAreNotEmptyOrNullForZip")
    void getZip_Should_return_Zip_When_There_Is_A_Zip(Person person, String expected) {
        String zip = person.getZip();

        assertEquals(expected, zip);
    }

    private static Stream<Arguments> GetValidNamesThatAreNotEmptyOrNullForZip(){
        return Stream.of(
                Arguments.of(p1, "12345"),
                Arguments.of(p2, "85789"),
                Arguments.of(p3, "56324")
        );
    }

    @Test
    void getZip_Should_Return_Null_If_Zip_Is_Null() {
        String zip = personWithNullValues.getZip();

        assertNull(zip);
    }

    @Test
    void getZip_Should_Return_Empty_String_If_Zip_IsEmpty() {
        String zip = personWithEmptyValues.getZip();

        assertEquals("", zip);
    }

    @Test
    void getZip_Should_Throw_IllegalArguemntException_If_Letters_are_In_Zip_Code() {
        assertThrows(IllegalArgumentException.class, () -> new Person("Ryan", "McGuire", "",
                "", "", "abcde", ""));
    }

    @ParameterizedTest
    @MethodSource("GetValidNamesThatAreNotEmptyOrNullForPhone")
    void getPhone_Should_return_Phone_When_There_Is_A_Phone(Person person, String expected) {
        String phone = person.getPhone();

        assertEquals(expected, phone);
    }

    private static Stream<Arguments> GetValidNamesThatAreNotEmptyOrNullForPhone(){
        return Stream.of(
                Arguments.of(p1, "2391111111"),
                Arguments.of(p2, "2391111112"),
                Arguments.of(p3, "2391111113")
        );
    }

    @Test
    void getPhone_Should_Return_Null_If_Phone_Is_Null() {
        String phone = personWithNullValues.getPhone();

        assertNull(phone);
    }

    @Test
    void getPhone_Should_Return_Empty_String_If_Phone_IsEmpty() {
        String phone = personWithEmptyValues.getPhone();

        assertEquals("", phone);
    }

    @ParameterizedTest
    @MethodSource("GetValidInputForgetField")
    void getField_Should_Return_The_Value_Of_The_Column(int column, String expected) {
        String field = p1.getField(column);

        assertEquals(expected, field);
    }

    private static Stream<Arguments> GetValidInputForgetField() {
        return Stream.of(
                Arguments.of(0, "Simpson"),
                Arguments.of(1, "Homer"),
                Arguments.of(2, "1234 springfield"),
                Arguments.of(3, "Fort Myers"),
                Arguments.of(4, "FL"),
                Arguments.of(5, "12345"),
                Arguments.of(6, "2391111111")
        );
    }

    // Here we are testing the bounds of what will throw an exception
    @ParameterizedTest
    @MethodSource("GetFieldsThatWillThrowException")
    void getField_Should_Throw_An_Exception_Out_of_Bounds(int field) {
        // checks to if the exception is thrown
        // this will only return an exception if its an IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String column = p1.getField(field);
        });

        String expectedException = "Field number out of bounds";
        String actualException = exception.getMessage();

        // checks to see if the exception is the wright message
        assertTrue(actualException.contains(expectedException));
    }

    private static Stream<Arguments> GetFieldsThatWillThrowException() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(-2),
                Arguments.of(7),
                Arguments.of(8)
        );
    }
}
