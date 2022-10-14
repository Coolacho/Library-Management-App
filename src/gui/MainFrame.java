package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import fundamentals.Book;
import fundamentals.Client;
import fundamentals.LoginSystemUtils;

public class MainFrame extends JFrame{

	private JPanel contentPane;
	static ArrayList<Client> clients;
	static ArrayList<Book> books;
	
	//Different panes in the application
	public static LoginMenu LOGIN_MENU;
	public static MainMenu MAIN_MENU;
	public static BookRegister BOOK_REGISTER;
	public static ClientRegister CLIENT_REGISTER;
	public static LendingBookMenu LENDING_BOOK_MENU;
	
	//String names for the panes for the card layout
	public final static String LOGIN_MENU_STRING = "Login Menu";
	public final static String MAIN_MENU_STRING = "Main Menu";
	public final static String BOOK_REGISTER_STRING = "Book Register";
	public final static String CLIENT_REGISTER_STRING = "Client Register";
	public final static String LENDING_BOOK_MENU_STRING = "Lending Book Menu";
	
	private MainFrame() {
	
		setTitle("Library Management");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(960, 540));
		setPreferredSize(new Dimension(960, 540));
		setLocation(480, 270);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				LoginSystemUtils.saveUsers();
				BOOK_REGISTER.tableModel.saveBooks();
				CLIENT_REGISTER.tableModel.saveClients();
			}
		});
		
		clients = LoginSystemUtils.loadClients();
		books = LoginSystemUtils.loadBooks();
		
		contentPane = new JPanel();
		contentPane.setLayout(new CardLayout());

		LOGIN_MENU = new LoginMenu(contentPane);
		MAIN_MENU = new MainMenu(contentPane);
		BOOK_REGISTER = new BookRegister(contentPane);
		CLIENT_REGISTER = new ClientRegister(contentPane);
		LENDING_BOOK_MENU = new LendingBookMenu(contentPane);
		
		contentPane.add(LOGIN_MENU, LOGIN_MENU_STRING);
		contentPane.add(MAIN_MENU, MAIN_MENU_STRING);
		contentPane.add(BOOK_REGISTER, BOOK_REGISTER_STRING);
		contentPane.add(CLIENT_REGISTER, CLIENT_REGISTER_STRING);
		contentPane.add(LENDING_BOOK_MENU, LENDING_BOOK_MENU_STRING);
		
		setContentPane(contentPane);
		pack();
	}
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}
}