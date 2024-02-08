import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber;
    }
}

class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void deleteContact(String name) {
        contacts.removeIf(contact -> contact.getName().equals(name));
    }

    public void updateContact(String name, String newPhoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setPhoneNumber(newPhoneNumber);
                break;
            }
        }
    }

    public ArrayList<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}

public class ContactManagerGUI extends JFrame {
    private ContactManager contactManager;
    private DefaultListModel<Contact> contactListModel;
    private JList<Contact> contactList;
    private JButton deleteButton;

    public ContactManagerGUI() {
        contactManager = new ContactManager();
        contactListModel = new DefaultListModel<>();

        // Initialize the deleteButton
        deleteButton = new JButton("Delete Contact");

        // Initialize other components (nameLabel, nameField, phoneLabel, phoneField, addButton, updateButton, reportButton, scrollPane)...
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        JButton addButton = new JButton("Add Contact");
        JButton updateButton = new JButton("Update Contact");
        JButton reportButton = new JButton("Generate Report");
        contactList = new JList<>(contactListModel);
        JScrollPane scrollPane = new JScrollPane(contactList);

        // Set layout manager
        setLayout(new GridLayout(6, 2));

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(phoneLabel);
        add(phoneField);
        add(addButton);
        add(deleteButton);
        add(updateButton);
        add(reportButton);
        add(scrollPane);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                Contact newContact = new Contact(name, phone);
                contactManager.addContact(newContact);
                updateContactList();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Contact selectedContact = contactListModel.getElementAt(selectedIndex);
                    contactManager.deleteContact(selectedContact.getName());
                    updateContactList();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a contact to delete.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Contact selectedContact = contactListModel.getElementAt(selectedIndex);
                    String newName = JOptionPane.showInputDialog("Enter new Name:");
                    String newPhoneNumber = JOptionPane.showInputDialog("Enter new phone number:");

                    if (newPhoneNumber != null) {
                        contactManager.updateContact(selectedContact.getName(), newPhoneNumber);
                        updateContactList();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a contact to update.");
                }
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder report = new StringBuilder("Contact Report:\n");
                for (Contact contact : contactManager.getAllContacts()) {
                    report.append(contact.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, report.toString());
            }
        });

        // Set frame properties
        setTitle("Contact Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateContactList() {
        contactListModel.clear();
        for (Contact contact : contactManager.getAllContacts()) {
            contactListModel.addElement(contact);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManagerGUI();
            }
        });
    }
}
