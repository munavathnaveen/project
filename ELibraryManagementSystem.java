import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Book {
    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}

public class ELibraryManagementSystem extends JFrame {
    private JTextField titleTextField, authorTextField;
    private ArrayList<Book> books;

    public ELibraryManagementSystem() {
        books = new ArrayList<>();

        setTitle("E Library Management System");
        setSize(600, 300);
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        titleTextField = new JTextField(20);
        authorTextField = new JTextField(20);

        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton displayButton = new JButton("Display Books");

        // Apply styles
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        titleTextField.setFont(textFont);
        authorTextField.setFont(textFont);

        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        displayButton.setFont(buttonFont);

        Color backgroundColor = new Color(173, 216, 230); // Light Sky Blue
        Color buttonColor = new Color(50, 150, 50);
        Color textColor = Color.BLACK;
        Color titleColor = Color.BLACK; // Set the title color to black

        getContentPane().setBackground(backgroundColor);
        addButton.setBackground(buttonColor);
        removeButton.setBackground(buttonColor);
        displayButton.setBackground(buttonColor);

        titleTextField.setBackground(Color.WHITE);
        authorTextField.setBackground(Color.WHITE);

        titleTextField.setForeground(textColor);
        authorTextField.setForeground(textColor);

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("E Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(titleColor); // Set the title color to black
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(backgroundColor);
        inputPanel.add(new JLabel("Title: ")).setFont(labelFont);
        inputPanel.add(titleTextField);
        inputPanel.add(new JLabel("Author: ")).setFont(labelFont);
        inputPanel.add(authorTextField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(inputPanel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(displayButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });
    }

    private void addBook() {
        String title = titleTextField.getText();
        String author = authorTextField.getText();

        if (!title.isEmpty() && !author.isEmpty()) {
            Book book = new Book(title, author);
            books.add(book);
            clearFields();
            JOptionPane.showMessageDialog(this, "Book added: " + book.title + " by " + book.author, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both title and author.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeBook() {
        String titleToRemove = titleTextField.getText();

        if (!titleToRemove.isEmpty()) {
            boolean removed = books.removeIf(book -> book.title.equals(titleToRemove));

            if (removed) {
                JOptionPane.showMessageDialog(this, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the title of the book to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayBooks() {
        // Create a table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Title", "Author"}, 0);

        // Add data to the table model
        for (Book book : books) {
            tableModel.addRow(new String[]{book.title, book.author});
        }

        // Create and configure the JTable
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create and configure the JScrollPane for the JTable
        JScrollPane scrollPane = new JScrollPane(table);

        // Show the JTable in a popup dialog
        JOptionPane.showMessageDialog(this, scrollPane, "Available Books", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        titleTextField.setText("");
        authorTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ELibraryManagementSystem().setVisible(true);
            }
        });
    }
}
