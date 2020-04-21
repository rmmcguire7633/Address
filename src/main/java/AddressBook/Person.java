package AddressBook;

import java.util.regex.Pattern;


public class Person {
  
    public static final String[] fields =
            {
                    "Last Name",
                    "First Name",
                    "Address",
                    "City",
                    "State",
                    "ZIP",
                    "Phone",
            };

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;


    // here we had a few test failed
    //
    public Person(String firstName, String lastName, String address, String city, String state, String zip, String phone) {
        if (firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
        if (lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("Last name cannot be empty");
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;

        // This bug was found we ran a test where the person zip code was letters
        // checks to see if the zip is null, if it is set it to empty string
        if (zip == null) {
            this.zip = null;
        }
        else {
            // checks to see if the zip code is numbers and 5 characters long
            if (!zip.matches("^[0-9]{5}") && !zip.isEmpty()) {
                throw new IllegalArgumentException("Zip code must be numbers and must be 5 charters long");
            } else {
                this.zip = zip;
            }
        }

        // This bug was found when we ran a test where we passed in letters for the phone number
        // checks to see if phone is null
        if (phone == null) {
            this.phone = null;
        } else {
            // checks to make sure phone is a number and is 10 characters long
            if (!phone.matches("^[0-9]{10}") && !phone.isEmpty()) {
                throw new IllegalArgumentException("Phone must be numbers and must be 10 characters long");
            } else {
                this.phone = phone;
            }
        }
    }


    public String getFirstName() {
        return firstName;
    }

  
    public String getLastName() {
        return lastName;
    }

    
    public String getAddress() {
        return address;
    }

  
    public String getCity() {
        return city;
    }

   
    public String getState() {
        return state;
    }

    /**
     * Returns this Person's ZIP code.
     *
     * @return ZIP code of this Person
     */
    public String getZip() {
        return zip;
    }

    /**
     * Returns this Person's telephone number.
     *
     * @return Telephone number of this Person.
     */
    public String getPhone() {
        return phone;
    }

   
    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
   
    public String getField(int field) {
        switch (field) {
            case 0:
                return lastName;
            case 1:
                return firstName;
            case 2:
                return address;
            case 3:
                return city;
            case 4:
                return state;
            case 5:
                return zip;
            case 6:
                return phone;
            default:
                throw new IllegalArgumentException("Field number out of bounds");
        }
    }
}