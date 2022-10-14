package gui;

import java.awt.*;
import javax.swing.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class RecentActivityPane extends JPanel{

	private JLabel title;
	private JList<String> actionsList;
	private DefaultListModel<String> listModel;
	private JScrollPane listScrollPane;
	
	public RecentActivityPane() {
		setPreferredSize(new Dimension(450, 500));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		setOpaque(true);
		
		title = new JLabel("Recent Activity");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		listModel = new DefaultListModel<String>();
		
		actionsList = new JList<String>(listModel);
		actionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actionsList.setVisibleRowCount(-1);
		actionsList.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		listScrollPane = new JScrollPane(actionsList);
		
		add(title);
		add(listScrollPane);
		
	}
	
	public void newActivity(String action) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		listModel.add(0, action+" at "+dtf.format(now));
		actionsList.setSelectedIndex(listModel.getSize());
		actionsList.ensureIndexIsVisible(listModel.getSize());
	}
	
	//TODO: Add custom cell renderer
	
	private static final long serialVersionUID = 1L;

}