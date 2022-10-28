package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import fundamentals.BookModels;

public class LendingBookMenu extends JPanel{

	private JPanel centerPane;
	private JPanel optionsPane;
	
	private ClientChooserPane clientChooserPane;
	
	private JPanel bookChooserPane;
	private JList<String> bookList;
	
	public LendingBookMenu(JPanel contentPane) {
		
		//set up the panel
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setOpaque(true);
		setMinimumSize(new Dimension(960, 540));
		setPreferredSize(new Dimension(960, 540));
		
		centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.LINE_AXIS));
		
		clientChooserPane = new ClientChooserPane();
		
		setBookChooserPane();
		
		setOptionsPane(contentPane);
		
		//centerPane.add(Box.createHorizontalGlue());
		centerPane.add(clientChooserPane);
		centerPane.add(Box.createRigidArea(new Dimension(20, 0)));
		centerPane.add(new JSeparator(SwingConstants.VERTICAL));
		centerPane.add(Box.createRigidArea(new Dimension(20, 0)));
		centerPane.add(bookChooserPane);
		//centerPane.add(Box.createHorizontalGlue());
		
		add(centerPane, BorderLayout.CENTER);
		add(optionsPane, BorderLayout.PAGE_END);
	}
	
	private void setOptionsPane(JPanel contentPane) {
		
		optionsPane = new JPanel();
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.LINE_AXIS));
		
		JButton submitButton = new JButton("Lend");
		submitButton.setFocusPainted(false);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.MAIN_MENU_STRING);
				//TODO: change the status of the lent books to taken
				MainMenu.RECENT_ACTIVITY_PANE.newActivity("Lended a book!");
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.setFocusPainted(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.MAIN_MENU_STRING);
			}
		});
		
		optionsPane.add(Box.createHorizontalGlue());
		optionsPane.add(submitButton);
		optionsPane.add(Box.createRigidArea(new Dimension(20, 0)));
		optionsPane.add(backButton);
		optionsPane.add(Box.createHorizontalGlue());
		
	}
	
	private void setBookChooserPane() {
		
		bookChooserPane = new JPanel();
		bookChooserPane.setLayout(new BoxLayout(bookChooserPane, BoxLayout.PAGE_AXIS));
		
		BookModels.BookListModel listModel;
		
		listModel = MainFrame.bookModels.listModel;
		
		bookList = new JList<String>();
		bookList.setModel(listModel);
		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookList.setLayoutOrientation(JList.VERTICAL);
		bookList.setVisibleRowCount(-1);
		JScrollPane listScrollPane = new JScrollPane(bookList);
		
		bookChooserPane.add(listScrollPane);
	}
	
	private static final long serialVersionUID = 1L;

}
