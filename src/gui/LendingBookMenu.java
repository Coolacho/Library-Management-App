package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LendingBookMenu extends JPanel{

	private JPanel centerPane;
	private JPanel optionsPane;
	
	private ClientChooserPane clientChooserPane;
	private BookChooserPane bookChooserPane;
	
	public LendingBookMenu(MainFrame mainFrame) {
		
		//set up the panel
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setOpaque(true);
		setMinimumSize(mainFrame.getWindowSize());
		setPreferredSize(mainFrame.getWindowSize());
		
		centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.LINE_AXIS));
		
		clientChooserPane = new ClientChooserPane(mainFrame);
		
		bookChooserPane = new BookChooserPane(mainFrame);
		
		setOptionsPane(mainFrame.getContentPane());
		
		centerPane.add(Box.createHorizontalGlue());
		centerPane.add(clientChooserPane);
		centerPane.add(Box.createHorizontalGlue());
		centerPane.add(new JSeparator(SwingConstants.VERTICAL));
		centerPane.add(Box.createHorizontalGlue());
		centerPane.add(bookChooserPane);
		centerPane.add(Box.createHorizontalGlue());
		
		add(centerPane, BorderLayout.CENTER);
		add(optionsPane, BorderLayout.PAGE_END);
	}
	
	private void setOptionsPane(JPanel contentPane) {
		
		optionsPane = new JPanel();
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.LINE_AXIS));
		
		JLabel wrongInputMessage = new JLabel("Please select client and books!");
		wrongInputMessage.setAlignmentX(CENTER_ALIGNMENT);
		wrongInputMessage.setVisible(false);
		
		JButton submitButton = new JButton("Lend");
		submitButton.setFocusPainted(false);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				if ((clientChooserPane.getSelectedClient() == null) || (bookChooserPane.getSelectedBooks() == null)) {
					wrongInputMessage.setVisible(true);
				}
				else {
					clientChooserPane.getSelectedClient().takeBook(bookChooserPane.getSelectedBooks());
					MainMenu.RECENT_ACTIVITY_PANE.newActivity("Lended a book!");
					clearPane();
				}
				
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.setFocusPainted(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.getMainMenuString());
			}
		});
		
		optionsPane.add(wrongInputMessage);
		optionsPane.add(Box.createHorizontalGlue());
		optionsPane.add(submitButton);
		optionsPane.add(Box.createRigidArea(new Dimension(20, 0)));
		optionsPane.add(backButton);
		optionsPane.add(Box.createHorizontalGlue());
		
	}
	
	public void clearPane() {
		clientChooserPane.clearPane();
		bookChooserPane.clearPane();
	}
	
	private static final long serialVersionUID = 1L;

}
