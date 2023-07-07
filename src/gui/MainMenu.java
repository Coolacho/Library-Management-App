package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel{
	
	//menu panels
	private JPanel menuOptionsPane;
	public static RecentActivityPane RECENT_ACTIVITY_PANE;
	
	//menu options variables
	private JButton[] buttons;
	private static final String[] BUTTON_LABELS = {"Book Register", "Client Register", "Lend a Book", "Add Book", "Add Client", "Log out"};
	
	public MainMenu(MainFrame mainFrame) {
		createMenuOptions(mainFrame);
		RECENT_ACTIVITY_PANE = new RecentActivityPane();
		
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setOpaque(true);
		setMinimumSize(mainFrame.getWindowSize());
		setPreferredSize(mainFrame.getWindowSize());
		
		add(menuOptionsPane);
		add(RECENT_ACTIVITY_PANE);
	}
	
	private void createMenuOptions(MainFrame mainFrame) {
		menuOptionsPane = new JPanel();
		menuOptionsPane.setLayout(new GridLayout(0, 1));
		menuOptionsPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		menuOptionsPane.setPreferredSize(new Dimension(450, 500));
		menuOptionsPane.setOpaque(true);
		
		ActionListener buttonActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CardLayout cl = (CardLayout)mainFrame.getContentPane().getLayout();
				JButton sourceButton = (JButton)event.getSource();
				if (sourceButton.getText().equals(BUTTON_LABELS[0])) {
					cl.show(mainFrame.getContentPane(), MainFrame.getBookRegisterString());
				}
				else if (sourceButton.getText().equals(BUTTON_LABELS[1])) {
					cl.show(mainFrame.getContentPane(), MainFrame.getClientRegisterString());
				}
				else if (sourceButton.getText().equals(BUTTON_LABELS[2])) {
					mainFrame.getLendingBookMenu().clearPane();
					cl.show(mainFrame.getContentPane(), MainFrame.getLendingBookMenuString());
				}
				else if (sourceButton.getText().equals(BUTTON_LABELS[3])) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							new AddBookFrame(mainFrame);
						}
					});
				}
				else if (sourceButton.getText().equals(BUTTON_LABELS[4])) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							new AddClientFrame(mainFrame);
						}
					});
				}
				else if (sourceButton.getText().equals(BUTTON_LABELS[5])) {
					cl.show(mainFrame.getContentPane(), MainFrame.getLoginMenuString());
					RECENT_ACTIVITY_PANE.newActivity("Logged out!");
				}
				
			}
		};
		
		buttons = new JButton[BUTTON_LABELS.length];
		
		for (int i = 0; i < BUTTON_LABELS.length; ++i) {
			buttons[i] = new JButton(BUTTON_LABELS[i]);
			buttons[i].setAlignmentX(CENTER_ALIGNMENT);
			buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 25));
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].addActionListener(buttonActionListener);
			menuOptionsPane.add(buttons[i]);
		}
		
	}
	
	private static final long serialVersionUID = 1L;

}