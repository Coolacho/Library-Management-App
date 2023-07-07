package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fundamentals.Book;
import fundamentals.BookListModel;

public class BookChooserPane extends JPanel{
	
	private JPanel contentPane = this;
	private JPanel bookListCard;
	private JPanel bookPreviewCard;
	
	private final static String BOOK_LIST_STRING = "Book List";
	private final static String BOOK_PREVIEW_STRING = "Book Preview";
	
	private JList<String> bookList;
	private BookListModel listModel;
	private JScrollPane listScrollPane;
	
	private ArrayList<Book> selectedBooks;
	
	private static final long serialVersionUID = 1L;
	
	public BookChooserPane(MainFrame mainFrame) {
		
		setLayout(new CardLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setMaximumSize(new Dimension(350, 440));
		
		setBookListCard(mainFrame);
		setBookPreviewCard();
		
	}
	
	private void setBookListCard(MainFrame mainFrame) {
		
		bookListCard = new JPanel();
		bookListCard.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Choose book(s) from the list below:");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		listModel = mainFrame.getBookModels().getListModel();
		
		bookList = new JList<String>();
		bookList.setModel(listModel);
		bookList.setLayoutOrientation(JList.VERTICAL);
		bookList.setVisibleRowCount(-1);
		listScrollPane = new JScrollPane(bookList);
		
		JPanel optionsPane = setOptionsPane();
		
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
	
	private JPanel setOptionsPane() {
		
		JPanel optionsPane = new JPanel();
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.LINE_AXIS));
		optionsPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
		
		JButton chooseButton = new JButton("Choose");
		chooseButton.setFocusPainted(false);
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, BOOK_PREVIEW_STRING);
				selectedBooks = listModel.getSelectedBooks(bookList.getSelectedIndices());
				bookPreviewCard.add(listScrollPane, BorderLayout.CENTER);
				bookList.clearSelection();
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
		
		return optionsPane;
	}
	
	private void setBookPreviewCard() {
		
		bookPreviewCard = new JPanel();
		bookPreviewCard.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Selected book(s):");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, BOOK_LIST_STRING);
				listModel.getAllBooks();
				bookListCard.add(listScrollPane, BorderLayout.CENTER);
			}
		});
		
		bookPreviewCard.add(title, BorderLayout.PAGE_START);
		bookPreviewCard.add(cancelButton, BorderLayout.PAGE_END);
		
		add(bookPreviewCard, BOOK_PREVIEW_STRING);
		
	}
	
	public void clearPane() {
		bookList.clearSelection();
		setSelectedBooks(null);
		listModel.updateAllBooks();
		bookListCard.add(listScrollPane, BorderLayout.CENTER);
		CardLayout cl = (CardLayout)contentPane.getLayout();
		cl.show(contentPane, BOOK_LIST_STRING);
	}

	public ArrayList<Book> getSelectedBooks() {
		return selectedBooks;
	}

	public void setSelectedBooks(ArrayList<Book> selectedBooks) {
		this.selectedBooks = selectedBooks;
	}

}
