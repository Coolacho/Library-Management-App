package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fundamentals.Book;
import fundamentals.BookModels;

public class BookChooserPane extends JPanel{
	
	private JPanel contentPane = this;
	private JPanel bookListCard;
	private JPanel bookPreviewCard;
	
	private final static String BOOK_LIST_STRING = "Book List";
	private final static String BOOK_PREVIEW_STRING = "Book Preview";
	
	private JList<String> bookList;
	private BookModels.BookListModel listModel;
	
	private ArrayList<Book> selectedBooks;
	
	public BookChooserPane() {
		
		setLayout(new CardLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setMaximumSize(new Dimension(350, 440));
		
		setBookListCard();
		setBookPreviewCard();
		
	}
	
	private void setBookListCard() {
		
		bookListCard = new JPanel();
		bookListCard.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Choose book(s) from the list below:");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		listModel = MainFrame.bookModels.listModel;
		
		bookList = new JList<String>();
		bookList.setModel(listModel);
		bookList.setLayoutOrientation(JList.VERTICAL);
		bookList.setVisibleRowCount(-1);
		JScrollPane listScrollPane = new JScrollPane(bookList);
		
		JPanel optionsPane = new JPanel();
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.LINE_AXIS));
		optionsPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
		
		JButton chooseButton = new JButton("Choose");
		chooseButton.setFocusPainted(false);
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, BOOK_PREVIEW_STRING);
				setBooksInfo();
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				bookList.clearSelection();
			}
		});
		
		optionsPane.add(Box.createHorizontalGlue());
		optionsPane.add(chooseButton);
		optionsPane.add(Box.createRigidArea(new Dimension(20, 0)));
		optionsPane.add(cancelButton);
		optionsPane.add(Box.createHorizontalGlue());
		
		optionsPane.setVisible(false);
		
		bookList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if (bookList.isSelectionEmpty()) {
					optionsPane.setVisible(false);
				}
				else {
					optionsPane.setVisible(true);
				}
				
			}
			
		});
		
		bookListCard.add(title, BorderLayout.PAGE_START);
		bookListCard.add(listScrollPane, BorderLayout.CENTER);
		bookListCard.add(optionsPane, BorderLayout.PAGE_END);
		
		add(bookListCard, BOOK_LIST_STRING);
		
	}
	
	private void setBookPreviewCard() {
		
		bookPreviewCard = new JPanel();
		
		JLabel title = new JLabel("Selected book(s):");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, BOOK_LIST_STRING);
			}
		});
		
		bookPreviewCard.add(title);
		bookPreviewCard.add(cancelButton);
		
		add(bookPreviewCard, BOOK_PREVIEW_STRING);
		
	}
	
	private void setBooksInfo() {
		
		setSelectedBooks(listModel.getSelectedBooks(bookList.getSelectedIndices()));
		
	}
	
	public void clearPane() {
		bookList.clearSelection();
		setSelectedBooks(null);
		CardLayout cl = (CardLayout)contentPane.getLayout();
		cl.show(contentPane, BOOK_LIST_STRING);
	}

	public ArrayList<Book> getSelectedBooks() {
		return selectedBooks;
	}

	public void setSelectedBooks(ArrayList<Book> selectedBooks) {
		this.selectedBooks = selectedBooks;
	}

	private static final long serialVersionUID = 1L;
}
