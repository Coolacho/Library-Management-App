package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class LoginMenu extends JPanel{

	private JTextPane title;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel buttonMessage;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private FocusListener fieldFocusListener;
	private JButton loginButton;
	private JButton signInButton;
	private JPanel labelPane;
	private JPanel fieldPane;
	private JPanel formPane;
	private JPanel buttonPane;
	
	private static final long serialVersionUID = 1L;
	
	public LoginMenu(MainFrame mainFrame) {
		
		//set up the panel
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setOpaque(true);
		setMinimumSize(mainFrame.getWindowSize());
		setPreferredSize(mainFrame.getWindowSize());
		
		//set up the title
		title = new JTextPane();
		title.setText("Welcome to Library Management.\nEnter username and password to Log in or Sign in:\n(Current number of users is " + mainFrame.getUserSystem().getUsers().size() + "/10)");
		title.setOpaque(false);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setEditable(false);
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setMaximumSize(new Dimension(960, 100));
		
		//set center alignment to the text in the title.
		StyledDocument docStyle = title.getStyledDocument();
		SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
		docStyle.setParagraphAttributes(0, docStyle.getLength(), centerAttribute, false);
		
		//Initiate the labels
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		buttonMessage = new JLabel("");
		
		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		
		fieldFocusListener = new FocusListener() {
			public void focusGained(FocusEvent event) {
				buttonMessage.setText("");
			}
			
			public void focusLost(FocusEvent event) {} //Do nothing if it loses focus
		};
		
		usernameField.addFocusListener(fieldFocusListener);
		passwordField.addFocusListener(fieldFocusListener);
		
		usernameLabel.setLabelFor(usernameField);
		passwordLabel.setLabelFor(passwordField);
		buttonMessage.setLabelFor(loginButton);
		
		//set up the sign in button
		signInButton = new JButton("Sign In");
		signInButton.setFocusPainted(false);
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!(usernameField.getText().isEmpty() || passwordField.getPassword().length == 0)) {
					if (mainFrame.getUserSystem().addUser(mainFrame.getDatabase().getConnection(), usernameField.getText(), passwordField.getPassword())) {
						usernameField.setText("");
						passwordField.setText("");
						buttonMessage.setText("New account created successfully!");
						title.setText("Welcome to Library Management.\nEnter username and password to Log in or Sign in:\n(Current number of users is " + mainFrame.getUserSystem().getUsers().size() + "/10)");
					}
					else {
						buttonMessage.setText("Sorry, the account limit has been reached or the account already exists!");
					}
				}
				else {
					buttonMessage.setText("Please enter valid information!");
				}
			}
		});
		
		//set up the login button
		loginButton = new JButton("Log in");
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (mainFrame.getUserSystem().checkUserInfo(usernameField.getText(), passwordField.getPassword())) {
					//if true go to main menu
					CardLayout cl = (CardLayout)mainFrame.getContentPane().getLayout();
					cl.show(mainFrame.getContentPane(), MainFrame.getMainMenuString());
					usernameField.setText("");
					passwordField.setText("");
					buttonMessage.setText("");
					MainMenu.RECENT_ACTIVITY_PANE.newActivity("Logged in!");
				}
				//else type user info again
				else {
					buttonMessage.setText("Username or password is incorrect. Please try again!");
				}
			}
		});
		
		//Layout the labels in a panel.
        labelPane = new JPanel(new GridLayout(0, 1, 0, 20));
        labelPane.add(usernameLabel);
        labelPane.add(passwordLabel);
        labelPane.setAlignmentX(CENTER_ALIGNMENT);
        
        //Layout the text fields in a panel.
        fieldPane = new JPanel(new GridLayout(0, 1, 0, 20));
        fieldPane.add(usernameField);
        fieldPane.add(passwordField);
        fieldPane.setAlignmentX(CENTER_ALIGNMENT);
        
        //Combine the labels and fields in one panel.
        formPane = new JPanel();
        formPane.add(labelPane);
        formPane.add(fieldPane);
        formPane.setAlignmentX(CENTER_ALIGNMENT);
        formPane.setMaximumSize(new Dimension(960, 100));

        buttonMessage.setAlignmentX(CENTER_ALIGNMENT);
        
        //Layout the button and the message in a panel.
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(signInButton);
        buttonPane.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPane.add(loginButton);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.setAlignmentX(CENTER_ALIGNMENT);
        signInButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(formPane);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(buttonMessage);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(buttonPane);
        add(Box.createVerticalGlue());
	}
}