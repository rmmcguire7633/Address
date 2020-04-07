package GUITesting;
import AddressBook.AddressBook;
import AddressBook.AddressBookController;
import GUI.AddressBookGUI;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

class Gui {
    private FrameFixture window;

    @Before
    public void setUp() {
        AddressBook addressBook = new AddressBook();
        AddressBookController controller = new AddressBookController(addressBook);
        window = new FrameFixture(new AddressBookGUI(controller, addressBook));
        window.show();
    }
    @Test
    void createPersonInAddressBook () {
        window.button("add").click();
    }


}
