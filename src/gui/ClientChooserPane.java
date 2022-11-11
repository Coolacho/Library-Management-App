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
	private JPanel clientPreviewCard;
	
	private final static String CLIENT_LIST_STRING = "Client List";
	private final static String CLIENT_PREVIEW_STRING = "Client Preview";
	
	private JList<String> clientList;
	private ClientModels.ClientListModel clientListModel;
	
	private Client selectedClient;
	
	private JLabel photoLabel;
	private JEditorPane clientInfoTextPane;
	private JTable takenBooksTable;
	private TakenBooksTableModel takenBooksTableModel;

	public ClientChooserPane() {
		
		setLayout(new CardLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setMaximumSize(new Dimension(350, 440));
		
		setClientListCard();
		setClientPreviewCard();
		
	}
	
	private void setClientListCard() {
		
		clientListCard = new JPanel();
		clientListCard.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Choose a client from the list below:");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		clientListModel = MainFrame.clientModels.listModel;
		
		clientList = new JList<String>();
		clientList.setModel(clientListModel);
		clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clientList.setLayoutOrientation(JList.VERTICAL);
		clientList.setVisibleRowCount(-1);
		JScrollPane listScrollPane = new JScrollPane(clientList);
		
		JPanel optionsPane = new JPanel();
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.LINE_AXIS));
		optionsPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
		
		JButton chooseButton = new JButton("Choose");
		chooseButton.setFocusPainted(false);
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, CLIENT_PREVIEW_STRING);
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
	
	private void setClientPreviewCard() {
		
		clientPreviewCard = new JPanel();
		clientPreviewCard.setLayout(new BoxLayout(clientPreviewCard, BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("Selected client:");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		photoLabel = new JLabel();
		photoLabel.setMinimumSize(new Dimension(125,150));
		photoLabel.setPreferredSize(new Dimension(125,150));
		photoLabel.setMaximumSize(new Dimension(125,150));
		photoLabel.setAlignmentY(BOTTOM_ALIGNMENT);
		photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		clientInfoTextPane = new JEditorPane();
		clientInfoTextPane.setEditable(false);
		clientInfoTextPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		clientInfoTextPane.setAlignmentY(BOTTOM_ALIGNMENT);
		
		JPanel clientInfoWrapper = new JPanel();
		clientInfoWrapper.setLayout(new BoxLayout(clientInfoWrapper, BoxLayout.LINE_AXIS));
		clientInfoWrapper.add(photoLabel);
		clientInfoWrapper.add(Box.createRigidArea(new Dimension(20, 0)));
		clientInfoWrapper.add(clientInfoTextPane);
		
		takenBooksTable = new JTable();
		takenBooksTable.setFillsViewportHeight(true);
		JScrollPane tableScrollPane = new JScrollPane(takenBooksTable);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, CLIENT_LIST_STRING);
			}
		});
		
		clientPreviewCard.add(title);
		clientPreviewCard.add(clientInfoWrapper);
		clientPreviewCard.add(Box.createRigidArea(new Dimension(0, 20)));
		clientPreviewCard.add(tableScrollPane);
		clientPreviewCard.add(Box.createRigidArea(new Dimension(0, 20)));
		clientPreviewCard.add(cancelButton);
		
		add(clientPreviewCard, CLIENT_PREVIEW_STRING);
		
	}
	
	private void setClientInfo() {
		
		setSelectedClient(clientListModel.getClient(clientList.getSelectedIndex()));
		
		photoLabel.setIcon(Client.ResizeImage(selectedClient.getPhotoPath(), photoLabel));
		
		clientInfoTextPane.setContentType("text/html");
		clientInfoTextPane.setText("<div style=\"margin:5 0 0 10;font-size:135%;\"><b>Id:</b> " + selectedClient.getId() +
									"<br><b>Name:</b> " + selectedClient.getName() +
									"<br><b>Age:</b> " + selectedClient.getAge() +
									"<br><b>Email:</b> " + selectedClient.getEmail() +
									"<br><b>Telephone:</b> " + selectedClient.getTelephone() + "</div>");
		
		takenBooksTableModel = new TakenBooksTableModel(selectedClient.getBooksTaken());
		takenBooksTable.setModel(takenBooksTableModel);
		
	}
	
	public void clearPane() {
		clientList.clearSelection();
		setSelectedClient(null);
		CardLayout cl = (CardLayout)contentPane.getLayout();
		cl.show(contentPane, CLIENT_LIST_STRING);
	}
	
	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	private static final long serialVersionUID = 1L;

}
