package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryManagement extends JFrame implements ActionListener {

    private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
    private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
    private ArrayList<Book> books = new ArrayList<>();

    public LibraryManagement() {
        setTitle("Library Management System");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Book ID");
        JLabel label2 = new JLabel("Book Title");
        JLabel label3 = new JLabel("Author");
        JLabel label4 = new JLabel("Publisher");
        JLabel label5 = new JLabel("Year of Publication");
        JLabel label6 = new JLabel("ISBN");
        JLabel label7 = new JLabel("Number of Copies");

        textField1 = new JTextField(10);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(10);
        textField6 = new JTextField(20);
        textField7 = new JTextField(10);

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(label1); panel.add(textField1);
        panel.add(label2); panel.add(textField2);
        panel.add(label3); panel.add(textField3);
        panel.add(label4); panel.add(textField4);
        panel.add(label5); panel.add(textField5);
        panel.add(label6); panel.add(textField6);
        panel.add(label7); panel.add(textField7);
        panel.add(addButton); panel.add(viewButton);
        panel.add(editButton); panel.add(deleteButton);
        panel.add(clearButton); panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            Book book = new Book(
                    textField1.getText(),
                    textField2.getText(),
                    textField3.getText(),
                    textField4.getText(),
                    textField5.getText(),
                    textField6.getText(),
                    textField7.getText()
            );
            books.add(book);
            JOptionPane.showMessageDialog(this, "Book added successfully");
            clearFields();
        }
        else if (e.getSource() == viewButton) {
            String[] columns = {"Book ID", "Book Title", "Author", "Publisher", "Year", "ISBN", "Copies"};
            Object[][] data = new Object[books.size()][7];
            for (int i = 0; i < books.size(); i++) {
                data[i] = books.get(i).toArray();
            }
            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);
            JFrame frame = new JFrame("View Books");
            frame.add(scrollPane);
            frame.setSize(800, 400);
            frame.setVisible(true);
        }
        else if (e.getSource() == editButton) {
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to edit:");
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(bookID)) {
                    Book updatedBook = new Book(
                            bookID,
                            textField2.getText(),
                            textField3.getText(),
                            textField4.getText(),
                            textField5.getText(),
                            textField6.getText(),
                            textField7.getText()
                    );
                    books.set(i, updatedBook);
                    JOptionPane.showMessageDialog(this, "Book edited successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        }
        else if (e.getSource() == deleteButton) {
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to delete:");
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(bookID)) {
                    books.remove(i);
                    JOptionPane.showMessageDialog(this, "Book deleted successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        }
        else if (e.getSource() == clearButton) {
            clearFields();
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
    }
}
