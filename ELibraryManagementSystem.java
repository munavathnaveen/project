import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class EBook {
    String title;
    String author;
    double price; // Added field for book price
    boolean isAvailable; // Added field to track book availability
    String username; // Added field for the username of the person who rented the book

    public EBook(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = true; // Initialize as available
        this.username = ""; // Initialize as an empty string
    }
}

public class EBookStationaryManagementSystem extends JFrame {
    private JTextField titleTextField, authorTextField, priceTextField;
    private ArrayList<EBook> eBooks;
    private Map<EBook, String> rentedBooks; // Added to track rented books and associated usernames

    public EBookStationaryManagementSystem() {
        eBooks = new ArrayList<>();
        rentedBooks = new HashMap<>();

        setTitle("E-Book Stationary Management System");
        setSize(600, 300);
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        titleTextField = new JTextField(20);
        authorTextField = new JTextField(20);
        priceTextField = new JTextField(10);

        JButton addButton = new JButton("Add E-Book");
        JButton removeButton = new JButton("Remove E-Book");
        JButton viewBooksButton = new JButton("View Books");
        JButton purchaseButton = new JButton("Purchase E-Book");
        JButton rentButton = new JButton("Rent E-Book");
        JButton returnButton = new JButton("Return E-Book");

        // Apply styles
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        titleTextField.setFont(textFont);
        authorTextField.setFont(textFont);
        priceTextField.setFont(textFont);

        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        viewBooksButton.setFont(buttonFont);
        purchaseButton.setFont(buttonFont);
        rentButton.setFont(buttonFont);
        returnButton.setFont(buttonFont);

        Color backgroundColor = new Color(173, 216, 230); // Light Sky Blue
        Color buttonColor = new Color(50, 150, 50);
        Color textColor = Color.BLACK;
        Color titleColor = Color.BLACK; // Set the title color to black

        getContentPane().setBackground(backgroundColor);
        addButton.setBackground(buttonColor);
        removeButton.setBackground(buttonColor);
        viewBooksButton.setBackground(buttonColor);
        purchaseButton.setBackground(buttonColor);
        rentButton.setBackground(buttonColor);
        returnButton.setBackground(buttonColor);

        titleTextField.setBackground(Color.WHITE);
        authorTextField.setBackground(Color.WHITE);
        priceTextField.setBackground(Color.WHITE);

        titleTextField.setForeground(textColor);
        authorTextField.setForeground(textColor);
        priceTextField.setForeground(textColor);

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("E-Book Stationary Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(titleColor); // Set the title color to black
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(backgroundColor);
        inputPanel.add(new JLabel("Title: ")).setFont(labelFont);
        inputPanel.add(titleTextField);
        inputPanel.add(new JLabel("Author: ")).setFont(labelFont);
        inputPanel.add(authorTextField);
        inputPanel.add(new JLabel("Price: $")).setFont(labelFont);
        inputPanel.add(priceTextField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(inputPanel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewBooksButton);
        buttonPanel.add(purchaseButton);
        buttonPanel.add(rentButton);
        buttonPanel.add(returnButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEBook();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEBook();
            }
        });

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBooks();
            }
        });

        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseEBook();
            }
        });

        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentEBook();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnEBook();
            }
        });
    }

    private void addEBook() {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        double price;

        try {
            price = Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!title.isEmpty() && !author.isEmpty() && price >= 0) {
            EBook eBook = new EBook(title, author, price);
            eBooks.add(eBook);
            clearFields();
            JOptionPane.showMessageDialog(this, "E-Book added: " + eBook.title + " by " + eBook.author, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter valid title, author, and price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeEBook() {
        String titleToRemove = titleTextField.getText();

        if (!titleToRemove.isEmpty()) {
            boolean removed = eBooks.removeIf(eBook -> eBook.title.equals(titleToRemove));

            if (removed) {
                JOptionPane.showMessageDialog(this, "E-Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "E-Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the title of the E-Book to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewBooks() {
        // Create a table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Title", "Author", "Price", "Availability", "Username"}, 0);

        // Add data to the table model for available books
        for (EBook eBook : eBooks) {
            String availability = eBook.isAvailable ? "Available" : "Not Available";
            tableModel.addRow(new String[]{eBook.title, eBook.author, String.valueOf(eBook.price), availability, ""});
        }

        // Add data to the table model for rented books
        for (Map.Entry<EBook, String> entry : rentedBooks.entrySet()) {
            EBook eBook = entry.getKey();
            String username = entry.getValue();
            tableModel.addRow(new String[]{eBook.title, eBook.author, String.valueOf(eBook.price), "Rented", username});
        }

        // Create and configure the JTable
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create and configure the JScrollPane for the JTable
        JScrollPane scrollPane = new JScrollPane(table);

        // Show the JTable in a popup dialog
        JOptionPane.showMessageDialog(this, scrollPane, "E-Book Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void purchaseEBook() {
        String titleToPurchase = titleTextField.getText();

        if (!titleToPurchase.isEmpty()) {
            boolean found = false;
            for (EBook eBook : eBooks) {
                if (eBook.title.equals(titleToPurchase)) {
                    found = true;
                    JOptionPane.showMessageDialog(this, "E-Book purchased: " + eBook.title + " by " + eBook.author, "Success", JOptionPane.INFORMATION_MESSAGE);
                    eBooks.remove(eBook);
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "E-Book not available for purchase.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the title of the E-Book to purchase.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rentEBook() {
        String titleToRent = titleTextField.getText();

        if (!titleToRent.isEmpty()) {
            String username = JOptionPane.showInputDialog(this, "Enter username for renting:");
            if (username != null) { // Check if user pressed cancel
                boolean found = false;
                for (EBook eBook : eBooks) {
                    if (eBook.title.equals(titleToRent) && eBook.isAvailable) {
                        found = true;
                        JOptionPane.showMessageDialog(this, "E-Book rented by " + username + ": " + eBook.title + " by " + eBook.author + " for $" + eBook.price, "Success", JOptionPane.INFORMATION_MESSAGE);
                        rentedBooks.put(eBook, username);
                        eBook.isAvailable = false; // Mark as not available
                        break;
                    } else if (eBook.title.equals(titleToRent) && !eBook.isAvailable) {
                        found = true;
                        JOptionPane.showMessageDialog(this, "E-Book is not available for rent.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(this, "E-Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the title of the E-Book to rent.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnEBook() {
        String titleToReturn = titleTextField.getText();

        if (!titleToReturn.isEmpty()) {
            boolean found = false;
            for (EBook eBook : rentedBooks.keySet()) {
                if (eBook.title.equals(titleToReturn)) {
                    found = true;
                    JOptionPane.showMessageDialog(this, "E-Book returned: " + eBook.title + " by " + eBook.author, "Success", JOptionPane.INFORMATION_MESSAGE);
                    rentedBooks.remove(eBook);
                    eBook.isAvailable = true; // Mark as available
                    eBooks.add(eBook);
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "E-Book not found in rented books.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the title of the E-Book to return.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        titleTextField.setText("");
        authorTextField.setText("");
        priceTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EBookStationaryManagementSystem().setVisible(true);
            }
        });
    }
}
