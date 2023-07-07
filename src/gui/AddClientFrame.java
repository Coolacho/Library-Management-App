package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import fundamentals.Client;

import java.util.regex.*;

public class AddClientFrame extends JFrame{
	
	//Panel variables
	private JPanel contentPane;
	private JLabel title;
	
	//Form panel variables
	private JPanel formPanel;
	private static final String[] FIELDS_NAMES = {"Name:", "Age:", "Email:", "Telephone:"};
	private static final String[] PATTERNS = {"^[A-ZА-Я][a-zа-я]+\\h[A-ZА-Я][a-zа-я]+$", "^[1-9][0-9]$", "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", "^(0|\\+359)\\d{9}$"}; //Regex patterns for name, age, email and telephone respectively
	private JTextField[] textFields;
	
	//Submit button panel variables
	private JPanel submitPanel;
	private JLabel submitLabel;
	private JButton submitButton;
	
	//Photo panel variables
	private JPanel photoPanel;
	private JLabel photoLabel;
	private JButton photoButton;
	private String photoPath = "";

	public AddClientFrame(MainFrame mainFrame) {
		
		setTitle("Add New Client");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocation(660, 315);
		setMinimumSize(new Dimension(600, 450));
		setPreferredSize(new Dimension(600,450));
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,15));
		contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		contentPane.setOpaque(true);
		
		title = new JLabel("Enter client information:", SwingConstants.CENTER);
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
		setFormPanel();
		
		setSubmitPanel(mainFrame);
		
		setPhotoChooser();
		
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.LINE_AXIS));
		wrapper.add(formPanel);
		wrapper.add(Box.createHorizontalGlue());
		wrapper.add(new JSeparator(SwingConstants.VERTICAL));
		wrapper.add(Box.createHorizontalGlue());
		wrapper.add(photoPanel);
		wrapper.add(Box.createHorizontalGlue());
		
		contentPane.add(title, BorderLayout.PAGE_START);
		contentPane.add(wrapper, BorderLayout.CENTER);
		contentPane.add(submitPanel, BorderLayout.PAGE_END);
		
		setContentPane(contentPane);
		pack();
	}
	
	private void setFormPanel() {
		
		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(0, 2, 15, 30));
		formPanel.setPreferredSize(new Dimension(350, 175));
		formPanel.setMaximumSize(new Dimension(350, 175));
		
		textFields = new JTextField[FIELDS_NAMES.length];
		
		for (int i = 0; i < FIELDS_NAMES.length; ++i) {
			JLabel label = new JLabel(FIELDS_NAMES[i], JLabel.TRAILING);
			label.setLabelFor(textFields[i]);
			
			textFields[i] = new JTextField(20);
			textFields[i].setAlignmentX(LEFT_ALIGNMENT);
			
			formPanel.add(label);
			formPanel.add(textFields[i]);
		}
		
	}
	
	private void setPhotoChooser() {
		
		photoPanel = new JPanel();
		photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.PAGE_AXIS));
		photoPanel.setOpaque(true);
		
		photoLabel = new JLabel("Add photo here", SwingConstants.CENTER);
		photoLabel.setMinimumSize(new Dimension(125,150));
		photoLabel.setPreferredSize(new Dimension(125,150));
		photoLabel.setMaximumSize(new Dimension(125,150));
		photoLabel.setBackground(Color.GRAY);
		photoLabel.setOpaque(true);
		photoLabel.setLabelFor(photoButton);
		photoLabel.setAlignmentX(CENTER_ALIGNMENT);
		photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		photoButton = new JButton("Browse");
		photoButton.setAlignmentX(CENTER_ALIGNMENT);
		photoButton.setFocusPainted(false);
		photoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter extFilter = new FileNameExtensionFilter("*.jpg, *.png, *.gif", "jpg", "png", "gif");
				chooser.setFileFilter(extFilter);
				int result = chooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					photoPath = selectedFile.getAbsolutePath();
					photoLabel.setIcon(Client.ResizeImage(photoPath, photoLabel));
					photoLabel.setText(null);
					submitLabel.setText("");
				}
			}
		});
		
		photoPanel.add(Box.createVerticalGlue());
		photoPanel.add(photoLabel);
		photoPanel.add(Box.createRigidArea(new Dimension(0,15)));
		photoPanel.add(photoButton);
		photoPanel.add(Box.createVerticalGlue());
	}
	
	private void setSubmitPanel(MainFrame mainFrame) {
		
		submitPanel = new JPanel();
		submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
		
		submitLabel = new JLabel();
		submitLabel.setLabelFor(submitButton);
		submitLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
		submitLabel.setAlignmentX(CENTER_ALIGNMENT);
		submitLabel.setVisible(true);
		
		submitButton = new JButton("Submit");
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setFocusPainted(false);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				boolean isInfoValid = true;
				for (int i = 0; i < textFields.length; ++i) {
					//Validate each text field
					if(!validateData(PATTERNS[i], textFields[i].getText())) {
						textFields[i].setForeground(Color.RED);
						textFields[i].addFocusListener(new FocusListener() {
							
							public void focusGained(FocusEvent event) {
								event.getComponent().setForeground(Color.BLACK);
								if (!submitLabel.getText().isEmpty()) submitLabel.setText("");
							}
							
							public void focusLost(FocusEvent e) {
								//Do nothing if focus lost
							}
						});
						if (submitLabel.getText().isEmpty()) submitLabel.setText("Please enter valid information");
						isInfoValid = false;
					}
					
				}
				
				if (isInfoValid ) {
					if (!photoPath.isEmpty()) {
						Client client = new Client(textFields[0].getText(), Byte.parseByte(textFields[1].getText()), textFields[2].getText(), textFields[3].getText(), photoPath);
						if (mainFrame.getClientModels().addClient(mainFrame.getDatabase().getConnection(), client)) {
							dispose();
							MainMenu.RECENT_ACTIVITY_PANE.newActivity("Added a new client!");	
						}
						else {
							submitLabel.setText("Can't add client! Please try again!");
						}
					}
					else {
						submitLabel.setText("Please choose a photo!");
					}
				}
				
			}
		});
		
		submitPanel.add(submitLabel);
		submitPanel.add(submitButton);
	}
	
	private boolean validateData(final String dataPattern, final String data) {
		
		final Pattern pattern = Pattern.compile(dataPattern);
		Matcher matcher = pattern.matcher(data);
		
		if (matcher.matches()) return true;
		
		return false;
	}
	
	private static final long serialVersionUID = 1L;

}