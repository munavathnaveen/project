import javax.swing.*;
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
    private JTextArea displayArea;
    private ArrayList<Book> books;

    public ELibraryManagementSystem() {
        books = new ArrayList<>();

        setTitle("E Library Management System");
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        titleTextField = new JTextField(20);
        authorTextField = new JTextField(20);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton displayButton = new JButton("Display Books");

        // Apply styles
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Font displayFont = new Font("Arial", Font.PLAIN, 12);

        titleTextField.setFont(textFont);
        authorTextField.setFont(textFont);
        displayArea.setFont(displayFont);

        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        displayButton.setFont(buttonFont);

        Color backgroundColor = new Color(240, 240, 240);
        Color buttonColor = new Color(50, 150, 50);
        Color textColor = Color.BLACK;

        getContentPane().setBackground(backgroundColor);
        addButton.setBackground(buttonColor);
        removeButton.setBackground(buttonColor);
        displayButton.setBackground(buttonColor);

        titleTextField.setBackground(Color.WHITE);
        authorTextField.setBackground(Color.WHITE);
        displayArea.setBackground(Color.WHITE);

        titleTextField.setForeground(textColor);
        authorTextField.setForeground(textColor);
        displayArea.setForeground(textColor);

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("E Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
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

        // Display Area
        JScrollPane scrollPane = new JScrollPane(displayArea);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scrollPane, gbc);

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
            displayArea.append("Book added: " + book.title + " by " + book.author + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both title and author.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeBook() {
        String titleToRemove = titleTextField.getText();
        boolean removed = false;

        if (!titleToRemove.isEmpty()) {
            for (Book book : new ArrayList<>(books)) {
                if (book.title.equals(titleToRemove)) {
                    books.remove(book);
                    removed = true;
                    displayArea.append("Book removed: " + book.title + " by " + book.author + "\n");
                    break;
                }
            }

            if (!removed) {
                JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the title of the book to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayBooks() {
        displayArea.setText("");
        if (books.isEmpty()) {
            displayArea.append("No books in the library.\n");
        } else {
            for (Book book : books) {
                displayArea.append("Title: " + book.title + ", Author: " + book.author + "\n");
            }
        }
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
