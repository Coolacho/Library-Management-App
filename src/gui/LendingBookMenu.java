package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import fundamentals.BookListModel;
import fundamentals.ClientListModel;

public class LendingBookMenu extends JPanel{

	private JPanel clientChooserPane;
	private JList<String> clientList;
	
	private JPanel bookChooserPane;
	private JList<String> bookList;
	
	private JButton backButton;
	
	public LendingBookMenu(JPanel contentPane) {
		
		//set up the panel
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setOpaque(true);
		setMinimumSize(new Dimension(960, 540));
		setPreferredSize(new Dimension(960, 540));
		
		setClientChooserPane();
		
		setBookChooserPane();
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.MAIN_MENU_STRING);
				MainMenu.RECENT_ACTIVITY_PANE.newActivity("Lended a book!");
			}
		});
		
		add(clientChooserPane);
		add(new JSeparator(SwingConstants.VERTICAL));
		add(bookChooserPane);
		add(backButton);
	}
	
	private void setClientChooserPane() {
		
		clientChooserPane = new JPanel();
		clientChooserPane.setLayout(new BoxLayout(clientChooserPane, BoxLayout.PAGE_AXIS));
		
		ClientListModel listModel;
		
		if (MainFrame.clients != null) {
			listModel = new ClientListModel(MainFrame.clients);
		}
		else {
			listModel = new ClientListModel();
		}
		
		clientList = new JList<String>();
		clientList.setModel(listModel);
		clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clientList.setLayoutOrientation(JList.VERTICAL);
		clientList.setVisibleRowCount(-1);
		JScrollPane listScrollPane = new JScrollPane(clientList);
		
		clientChooserPane.add(listScrollPane);
	}
	
	private void setBookChooserPane() {
		
		bookChooserPane = new JPanel();
		//bookChooserPane.setLayout(new BoxLayout(clientChooserPane, BoxLayout.PAGE_AXIS));
		
		BookListModel listModel;
		
		if (MainFrame.books != null) {
			listModel = new BookListModel(MainFrame.books);
		}
		else {
			listModel = new BookListModel();
		}
		
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
