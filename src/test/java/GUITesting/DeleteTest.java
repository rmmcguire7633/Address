package GUITesting;
import AddressBook.AddressBook;
import AddressBook.AddressBookController;
import GUI.AddressBookGUI;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.swing.data.TableCell.row;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.junit.jupiter.api.Assertions.*;

// This is being used to automate manual testing
class DeleteTest {
    private FrameFixture window;

    private static final String firstName = "Ryan";
    private static final String lastName = "McGuire";
    private static final String address = "123 4th steet";
    private static final String city = "Fort Myers";
    private static final String state = "FL";
    private static final String ZIP = "33905";
    private static final String telephone = "8675309";

    @BeforeEach
    public void setUp() {
        AddressBook addressBook = new AddressBook();
        AddressBookController controller = new AddressBookController(addressBook);
        window = new FrameFixture(new AddressBookGUI(controller, addressBook));
        window.show();
    }
    @Test
    void deletePersonFromGUITest () {

        // Click the add button
        window.button("add").click();

        // makes sure all the text fields are present
        window.label("firstNameLabel").requireText("First name:");
        window.label("lastNameLabel").requireText("Last name:");
        window.label("addressLabel").requireText("Address:");
        window.label("cityLabel").requireText("City:");
        window.label("stateLabel").requireText("State:");
        window.label("zipLabel").requireText("ZIP code:");
        window.label("telephoneLabel").requireText("Telephone:");


        // enter a first name
        window.dialog().textBox("firstNameTextBox").enterText(firstName);
        window.dialog().textBox("lastNameTextBox").enterText(lastName);
        window.dialog().textBox("addressTextBox").enterText(address);
        window.dialog().textBox("cityTextBox").enterText(city);
        window.dialog().textBox("stateTextBox").enterText(state);
        window.dialog().textBox("zipTextBox").enterText(ZIP);
        window.dialog().textBox("phoneTextBox").enterText(telephone);

        // click ok button
        window.button("okButton").click();

        // validate the person was added to the address book
        assertEquals(lastName, window.table("tableView").valueAt(row(0).column(0)));
        assertEquals(firstName, window.table("tableView").valueAt(row(0).column(1)));
        assertEquals(address, window.table("tableView").valueAt(row(0).column(2)));
        assertEquals(city, window.table("tableView").valueAt(row(0).column(3)));
        assertEquals(state, window.table("tableView").valueAt(row(0).column(4)));
        assertEquals(ZIP, window.table("tableView").valueAt(row(0).column(5)));
        assertEquals(telephone, window.table("tableView").valueAt(row(0).column(6)));

        window.table("tableView").click(row(0).column(0), MouseButton.LEFT_BUTTON);
        window.button("delete").click();

        assertEquals(0, window.table("tableView").rowCount());
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }
}