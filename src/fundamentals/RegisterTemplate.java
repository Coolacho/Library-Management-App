package fundamentals;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import gui.MainFrame;

public abstract class RegisterTemplate extends JPanel implements ListSelectionListener{
	//Table variables
	protected JTable table;
	protected TableRowSorter<?> tableSorter;
	private JScrollPane scrollPane;
	protected int currentRow;
	
	//Option menu variables
	private JPanel optionsMenu;
	final static String REGISTER_OPTIONS = "Register options";
	final static String SELECTION_OPTIONS = "Selection options";
	
	//Variables for Register options panel in Option menu
	private JPanel registerOptions;
	private JTextField filterTextField;
	private JButton backButton;
	private JButton addButton;
	
	//Variables for Book options panel in Option menu
	private JPanel selectionOptions;
	private JButton removeButton;
	private JButton editButton;
	private JButton cancelSelectionButton;

	abstract protected void setTableModel();
	abstract protected void setTableSorter();
	abstract protected void invokeAddFrame();
	abstract protected void removeSelection();
	
	//Class constructor that puts the whole Book register panel together (Table + Options menu)
	public RegisterTemplate(JPanel contentPane) {
		setOpaque(true);
		setMinimumSize(new Dimension(960, 540));
		setPreferredSize(new Dimension(960, 540));
		setLayout(new BorderLayout());
		
		setTable();
		setOptionsMenu(contentPane);
		
		add(scrollPane, BorderLayout.CENTER);
		add(optionsMenu, BorderLayout.PAGE_END);
	}
	
	
	//Function to set the Table
	private void setTable() {
		
		//set the table
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		
		//create and set table model
		setTableModel();
		
		//set the row sorter
		setTableSorter();
		
		//Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(table);
        
	}
	
	//set the options menu at the bottom which has two different panels that depend on whether a book is selected or not
	private void setOptionsMenu(JPanel contentPane) {
		optionsMenu = new JPanel();
		optionsMenu.setLayout(new CardLayout());
		optionsMenu.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		
		setRegisterOptions(contentPane);
		setSelectionOptions();
		
		optionsMenu.add(registerOptions, REGISTER_OPTIONS);
		optionsMenu.add(selectionOptions, SELECTION_OPTIONS);
	}
	
	//Set the panel at the bottom with options when you enter the register (no book is selected)
	private void setRegisterOptions(JPanel contentPane) {
		registerOptions = new JPanel();
		registerOptions.setLayout(new BoxLayout(registerOptions, BoxLayout.LINE_AXIS));
		
		filterTextField = new JTextField("Search...");
		filterTextField.setForeground(Color.GRAY);
		filterTextField.setAlignmentX(LEFT_ALIGNMENT);
		filterTextField.setMinimumSize(new Dimension(250,30));
		filterTextField.setPreferredSize(new Dimension(250,30));
		filterTextField.setMaximumSize(new Dimension(250,30));
		filterTextField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent event) {
				filterTextField.setText("");
				filterTextField.setForeground(Color.BLACK);
			}
			
			public void focusLost(FocusEvent event) {
				if (filterTextField.getText().isEmpty()) {
					filterTextField.setForeground(Color.GRAY);
					filterTextField.setText("Search...");
				}
			}
		});
		filterTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
                filterFunc();
            }
            public void insertUpdate(DocumentEvent e) {
            	filterFunc();
            }
            public void removeUpdate(DocumentEvent e) {
            	filterFunc();
            }
		});
		
		backButton = new JButton("Back");
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		backButton.setFocusPainted(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, MainFrame.MAIN_MENU_STRING);
			}
		});
		
		addButton = new JButton("Add");
		addButton.setAlignmentX(CENTER_ALIGNMENT);
		addButton.setFocusPainted(false);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						invokeAddFrame();
					}
				});
			}
		});
		
		registerOptions.add(filterTextField);
		registerOptions.add(Box.createRigidArea(new Dimension(125,0)));
		//Not needed but an idea for future improvement
		//registerOptions.add(Box.createHorizontalGlue());
		registerOptions.add(addButton);
		registerOptions.add(Box.createRigidArea(new Dimension(5,0)));
		registerOptions.add(backButton);
		registerOptions.add(Box.createHorizontalGlue());
	}
	
	//Set the panel at the bottom with options when you select a book
	private void setSelectionOptions() {
		selectionOptions = new JPanel();
		
		//REMOVE BOOK BUTTON
		removeButton = new JButton("Remove");
		removeButton.setFocusPainted(false);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				removeSelection();
			}
		});
		
		//EDIT BOOK BUTTON
		editButton = new JButton("Edit");
		editButton.setFocusPainted(false);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			//TODO: create the edit feature	
			}
		});
		
		//CANCEL SELECTION BUTTON
		cancelSelectionButton = new JButton("Cancel");
		cancelSelectionButton.setFocusPainted(false);
		cancelSelectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				table.clearSelection();
			}
		});
		
		//Add everything to the book options panel
		selectionOptions.add(removeButton);
		selectionOptions.add(editButton);
		selectionOptions.add(cancelSelectionButton);
	}
	
	
	//function to control the selection of rows
	@Override
	public void valueChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting() == false) {
        	
        	currentRow = table.getSelectedRow();
        	
            if (currentRow == -1) {
            //No selection, options for the register (add book or exit to main menu).
            	CardLayout cl = (CardLayout)optionsMenu.getLayout();
				cl.show(optionsMenu, REGISTER_OPTIONS);
				
            } else {
            	currentRow = table.convertRowIndexToModel(currentRow);
            //Selection, options for the current book(table row) which are remove, edit or cancel selection.
            	CardLayout cl = (CardLayout)optionsMenu.getLayout();
				cl.show(optionsMenu, SELECTION_OPTIONS);
            }
        }
    }
	
	
	private void filterFunc() {
		RowFilter<Object, Object> rf = null;
		String pattern = filterTextField.getText();
		//If the field is empty and only the search prompt is there
		if (pattern.equals("Search...")) pattern = "";

		//If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(pattern);
        } catch (PatternSyntaxException e) {
            return;
        }
        tableSorter.setRowFilter(rf);
	}
	
	private static final long serialVersionUID = 1L;
}
