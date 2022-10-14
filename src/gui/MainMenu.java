package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel{
	
	//menu panels
	private JPanel menuOptionsPane;
	public static RecentActivityPane RECENT_ACTIVITY_PANE;
	
	//menu options variables
	private JButton bookRegisterButton;
	private JButton clientRegisterButton;
	private JButton lendABookButton;
	private JButton addBookButton;
	private JButton addClientButton;
	private JButton logOutButton;
	private static final String[] BUTTON_LABELS = {"Book Register", "Client Register", "Lend a Book", "Add Book", "Add Client", "Log out"};
	
	public MainMenu(JPanel contentPane) {
		createMenuOptions(contentPane);
		RECENT_ACTIVITY_PANE = new RecentActivityPane();
		
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setOpaque(true);
		setMinimumSize(new Dimension(960, 540));
		setPreferredSize(new Dimension(960, 540));
		
		add(menuOptionsPane);
		add(RECENT_ACTIVITY_PANE);
	}
	
	private void createMenuOptions(JPanel contentPane) {
		menuOptionsPane = new JPanel();
		menuOptionsPane.setLayout(new GridLayout(0, 1));
		menuOptionsPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		menuOptionsPane.setPreferredSize(new Dimension(450, 500));
		menuOptionsPane.setOpaque(true);
		
		//TODO: initialize the buttons with for loop if you can
		bookRegisterButton = new JButton(BUTTON_LABELS[0]);
		bookRegisterButton.setAlignmentX(CENTER_ALIGNMENT);
		bookRegisterButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		bookRegisterButton.setContentAreaFilled(false);
		bookRegisterButton.setFocusPainted(false);
		
		clientRegisterButton = new JButton(BUTTON_LABELS[1]);
		clientRegisterButton.setAlignmentX(CENTER_ALIGNMENT);
		clientRegisterButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		clientRegisterButton.setContentAreaFilled(false);
		clientRegisterButton.setFocusPainted(false);
		
		lendABookButton = new JButton(BUTTON_LABELS[2]);
		lendABookButton.setAlignmentX(CENTER_ALIGNMENT);
		lendABookButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lendABookButton.setContentAreaFilled(false);
		lendABookButton.setFocusPainted(false);
		
		addBookButton = new JButton(BUTTON_LABELS[3]);
		addBookButton.setAlignmentX(CENTER_ALIGNMENT);
		addBookButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		addBookButton.setContentAreaFilled(false);
		addBookButton.setFocusPainted(false);
		
		addClientButton = new JButton(BUTTON_LABELS[4]);
		addClientButton.setAlignmentX(CENTER_ALIGNMENT);
		addClientButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		addClientButton.setContentAreaFilled(false);
		addClientButton.setFocusPainted(false);
		
		logOutButton = new JButton(BUTTON_LABELS[5]);
		logOutButton.setAlignmentX(CENTER_ALIGNMENT);
		logOutButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		logOutButton.setContentAreaFilled(false);
		logOutButton.setFocusPainted(false);
		
		bookRegisterButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.BOOK_REGISTER_STRING);
			}
		});
		
		clientRegisterButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.CLIENT_REGISTER_STRING);
			}
		});
		
		lendABookButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.LENDING_BOOK_MENU_STRING);
			}
		});
		
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new AddBookFrame();
					}
				});
			}
		});
		
		addClientButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new AddClientFrame();
					}
				});
			}
		});
		
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.LOGIN_MENU_STRING);
				RECENT_ACTIVITY_PANE.newActivity("Logged out!");
			}
		});
		
		menuOptionsPane.add(bookRegisterButton);
		menuOptionsPane.add(clientRegisterButton);
		menuOptionsPane.add(lendABookButton);
		menuOptionsPane.add(addBookButton);
		menuOptionsPane.add(addClientButton);
		menuOptionsPane.add(logOutButton);
	}
	
	private static final long serialVersionUID = 1L;

}