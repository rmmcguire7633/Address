package GUI;

import AddressBook.Person;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;


public class PersonDialog extends JDialog {
    public enum Result {
        OK,
        CANCEL,
    }

    private Result result;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField address;
    private JTextField city;
    private JTextField state;
    private JTextField zip;
    private JTextField phone;

  
    public PersonDialog(Frame parent) {
        
        super(parent);

        
        JLabel l;
        AtomicReference<JPanel> p = new AtomicReference<>(new JPanel(new SpringLayout()));

        l = new JLabel("First name:", JLabel.TRAILING);
        l.setName("firstNameLabel");
        p.get().add(l);
        firstName = new JTextField(20);
        firstName.setName("firstNameTextBox");
        l.setLabelFor(firstName);
        p.get().add(firstName);

      
        JLabel llastName = new JLabel("Last name:", JLabel.TRAILING);
        p.get().add(llastName);
        llastName.setName("lastNameLabel");
        lastName = new JTextField(20);
        lastName.setName("lastNameTextBox");
        llastName.setLabelFor(lastName);
        p.get().add(lastName);

  
        JLabel lAddress = new JLabel("Address:", JLabel.TRAILING);
        p.get().add(lAddress);
        lAddress.setName("addressLabel");
        address = new JTextField(20);
        address.setName("addressTextBox");
        lAddress.setLabelFor(address);
        p.get().add(address);


        JLabel lCity = new JLabel("City:", JLabel.TRAILING);
        p.get().add(lCity);
        lCity.setName("cityLabel");
        city = new JTextField(20);
        city.setName("cityTextBox");
        lCity.setLabelFor(city);
        p.get().add(city);


        JLabel lState = new JLabel("State:", JLabel.TRAILING);
        p.get().add(lState);
        lState.setName("stateLabel");
        state = new JTextField(20);
        state.setName("stateTextBox");
        l.setLabelFor(state);
        p.get().add(state);

        JLabel lZIP = new JLabel("ZIP code:", JLabel.TRAILING);
        p.get().add(lZIP);
        lZIP.setName("zipLabel");
        zip = new JTextField(20);
        zip.setName("zipTextBox");
        lZIP.setLabelFor(zip);
        p.get().add(zip);


        JLabel lTelephone = new JLabel("Telephone:", JLabel.TRAILING);
        p.get().add(lTelephone);
        lTelephone.setName("telephoneLabel");
        phone = new JTextField(20);
        phone.setName("phoneTextBox");
        lTelephone.setLabelFor(phone);
        p.get().add(phone);

        
        SpringUtilities.makeCompactGrid(p.get(), 7, 2, 6, 6, 6, 6);

        // Set up the buttons
        JPanel buttons = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.setName("okButton");
        okButton.setMnemonic('O');
        okButton.addActionListener(e ->
        {
            result = Result.OK;
            setVisible(false);
        });
        buttons.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('C');
        cancelButton.addActionListener(e ->
        {
            result = Result.CANCEL;
            setVisible(false);
        });
        buttons.add(cancelButton);

        // Set window properties
        getContentPane().add(p.get(), BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.PAGE_END);
        pack();
        setTitle("Person Information");
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setLocation((parent.getWidth() - getWidth()) / 2, (parent.getHeight() - getHeight()) / 2);
    }

  
    public PersonDialog(Frame parent, @Nullable Person person) {
        this(parent);
        if (person == null)
            return;
        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        address.setText(person.getAddress());
        city.setText(person.getCity());
        state.setText(person.getState());
        zip.setText(person.getZip());
        phone.setText(person.getPhone());
    }

    
    public Result showDialog() {
        // Default to CANCEL if the user closes the dialog window
        result = Result.CANCEL;
        setVisible(true);
        return result;
    }

 
    public Person getPerson() {
        if (firstName != null && lastName != null && !firstName.getText().isEmpty() && !lastName.getText().isEmpty()) {
            return new Person(firstName.getText(),
                    lastName.getText(),
                    address.getText(),
                    city.getText(),
                    state.getText(),
                    zip.getText(),
                    phone.getText());
        } else {
            return null;
        }
    }
}