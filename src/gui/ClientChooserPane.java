package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fundamentals.Client;
import fundamentals.ClientModels;
import fundamentals.TakenBooksTableModel;

public class ClientChooserPane extends JPanel{
	
	private JPanel contentPane = this;
	private JPanel clientListCard;
	private JPanel clientReviewCard;
	
	private final String CLIENT_LIST_STRING = "Client List";
	private final String CLIENT_REVIEW_STRING = "Client Review";
	
	private JList<String> clientList;
	private ClientModels.ClientListModel clientListModel;
	
	JLabel photoLabel;
	JEditorPane clientInfoTextPane;
	JTable takenBooksTable;
	TakenBooksTableModel takenBooksTableModel;

	public ClientChooserPane() {
		
		setLayout(new CardLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setMaximumSize(new Dimension(380, 540));
		
		setClientListCard();
		setClientReviewCard();
		
	}
	
	private void setClientListCard() {
		
		clientListCard = new JPanel();
		clientListCard.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Choose a client from the list below:");
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 8, 15));
		
		clientListModel = MainFrame.clientModels.listModel;
		
		clientList = new JList<String>();
		clientList.setModel(clientListModel);
		clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clientList.setLayoutOrientation(JList.VERTICAL);
		clientList.setVisibleRowCount(-1);
		JScrollPane listScrollPane = new JScrollPane(clientList);
		listScrollPane.setBorder(BorderFactory.createEmptyBorder(7, 15, 7, 15));
		
		JPanel optionsPane = new JPanel();
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.LINE_AXIS));
		optionsPane.setBorder(BorderFactory.createEmptyBorder(8, 15, 15, 15));
		
		JButton chooseButton = new JButton("Choose");
		chooseButton.setFocusPainted(false);
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, CLIENT_REVIEW_STRING);
				setClientInfo();
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				clientList.clearSelection();
			}
		});
		
		optionsPane.add(Box.createHorizontalGlue());
		optionsPane.add(chooseButton);
		optionsPane.add(Box.createRigidArea(new Dimension(20, 0)));
		optionsPane.add(cancelButton);
		optionsPane.add(Box.createHorizontalGlue());
		
		optionsPane.setVisible(false);
		
		clientList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if (clientList.isSelectionEmpty()) {
					optionsPane.setVisible(false);
				}
				else {
					optionsPane.setVisible(true);
				}
				
			}
			
		});
		
		clientListCard.add(title, BorderLayout.PAGE_START);
		clientListCard.add(listScrollPane, BorderLayout.CENTER);
		clientListCard.add(optionsPane, BorderLayout.PAGE_END);
		
		add(clientListCard, CLIENT_LIST_STRING);
		
	}
	
	private void setClientReviewCard() {
		
		clientReviewCard = new JPanel();
		
		photoLabel = new JLabel();
		photoLabel.setMinimumSize(new Dimension(125,150));
		photoLabel.setPreferredSize(new Dimension(125,150));
		photoLabel.setMaximumSize(new Dimension(125,150));
		
		clientInfoTextPane = new JEditorPane();
		
		takenBooksTable = new JTable();
		takenBooksTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		takenBooksTable.setFillsViewportHeight(true);
		JScrollPane tableScrollPane = new JScrollPane(takenBooksTable);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, CLIENT_LIST_STRING);
			}
		});
		
		clientReviewCard.add(photoLabel);
		clientReviewCard.add(clientInfoTextPane);
		clientReviewCard.add(tableScrollPane);
		clientReviewCard.add(cancelButton);
		
		add(clientReviewCard, CLIENT_REVIEW_STRING);
		
	}
	
	private void setClientInfo() {
		
		Client selectedClient = clientListModel.getClient(clientList.getSelectedIndex());
		
		photoLabel.setIcon(Client.ResizeImage(selectedClient.getPhotoPath(), photoLabel));
		
		clientInfoTextPane.setContentType("text/html");
		clientInfoTextPane.setText("<b>Id:</b> " + selectedClient.getId() +
									"<br><b>Name:</b> " + selectedClient.getName() +
									"<br><b>Age:</b> " + selectedClient.getAge() +
									"<br><b>Email:</b> " + selectedClient.getEmail() +
									"<br><b>Telephone:</b> " + selectedClient.getTelephone());
		
		takenBooksTableModel = new TakenBooksTableModel(selectedClient.getBooksTaken());
		takenBooksTable.setModel(takenBooksTableModel);
		
	}
	
	private static final long serialVersionUID = 1L;

}
