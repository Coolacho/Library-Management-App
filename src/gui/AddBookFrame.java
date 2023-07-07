package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fundamentals.Book;

public class AddBookFrame extends JFrame{

	private JLabel titleLabel;
	private JLabel authorLabel;
	private JLabel buttonMessage;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JButton addBookButton;
	private JPanel contentPane;
	private JPanel labelPane;
	private JPanel fieldPane;
	private JPanel formPane;
	private JPanel buttonPane;

	public AddBookFrame(MainFrame mainFrame) {
		setTitle("Add New Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(350, 200));
		setPreferredSize(new Dimension(350,200));
		setLocation(785, 440);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		contentPane.setOpaque(true);
		
		//Create the labels.
		titleLabel = new JLabel("Title: ");
		authorLabel = new JLabel("Author: ");
		buttonMessage = new JLabel("Please enter valid information!");
		buttonMessage.setVisible(false);
		
		//Create the text fields and set them up.
		titleTextField = new JTextField(20);
		
		authorTextField = new JTextField(20);
		
		//Tell accessibility tools about label/text field pairs.
		titleLabel.setLabelFor(titleTextField);
		authorLabel.setLabelFor(authorTextField);
		buttonMessage.setLabelFor(addBookButton);
		
		//Create Add button and set it up.
		addBookButton = new JButton("Add");
		addBookButton.setFocusPainted(false);
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String title = titleTextField.getText();
				String author = authorTextField.getText();
				setMinimumSize(new Dimension(350,210));
				setPreferredSize(new Dimension(350,210));
				if (!(title.isEmpty()) &&  !(author.isEmpty())) {
					Book book = new Book(title, author);
					if (mainFrame.getBookModels().addBook(mainFrame.getDatabase().getConnection(), book)) {
						dispose();
						MainMenu.RECENT_ACTIVITY_PANE.newActivity("Added a new book!");
					}
				}
				else {
					buttonMessage.setVisible(true);
				}
			}
		});
		
		//Layout the labels in a panel.
        labelPane = new JPanel(new GridLayout(0,1,0,15));
        labelPane.add(titleLabel);
        labelPane.add(authorLabel);
        
        //Layout the text fields in a panel.
        fieldPane = new JPanel(new GridLayout(0,1,0,15));
        fieldPane.add(titleTextField);
        fieldPane.add(authorTextField);
        
        //Combine the labels and fields in one panel.
        formPane = new JPanel();
        formPane.add(labelPane);
        formPane.add(fieldPane);
        
        //Layout the button and the message in a panel.
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));
        buttonPane.add(buttonMessage);
        buttonPane.add(Box.createRigidArea(new Dimension(0,15)));
        buttonPane.add(addBookButton);       
        buttonMessage.setAlignmentX(CENTER_ALIGNMENT);
        addBookButton.setAlignmentX(CENTER_ALIGNMENT);
        
        //Put the panels in this panel, labels on left,
        //text fields on right and button on the bottom.
		contentPane.add(formPane, BorderLayout.PAGE_START);
		contentPane.add(buttonPane, BorderLayout.CENTER);
		
		
		setContentPane(contentPane);
		pack();
	}
	
	private static final long serialVersionUID = 1L;

}