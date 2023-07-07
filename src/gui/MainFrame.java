package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import database.Database;
import database.DatabaseLoad;
import fundamentals.BookModels;
import fundamentals.ClientModels;
import fundamentals.FilesLoad;
import fundamentals.FilesSave;
import fundamentals.UserSystem;

public class MainFrame extends JFrame{
	
	private Dimension windowSize;
	private Point windowPosition;
	
	private JPanel contentPane;
	
	private Database database;
	private UserSystem userSystem;
	private ClientModels clientModels;
	private BookModels bookModels;
	
	//Different panes in the application
	private LoginMenu loginMenu;
	private MainMenu mainMenu;
	private BookRegister bookRegister;
	private ClientRegister clientRegister;
	private LendingBookMenu lendingBookMenu;
	
	//String names for the panes for the card layout
	private final static String LOGIN_MENU_STRING = "Login Menu";
	private final static String MAIN_MENU_STRING = "Main Menu";
	private final static String BOOK_REGISTER_STRING = "Book Register";
	private final static String CLIENT_REGISTER_STRING = "Client Register";
	private final static String LENDING_BOOK_MENU_STRING = "Lending Book Menu";
	
	private static final long serialVersionUID = 1L;
	
	private MainFrame() {
		
		calculateWindow();
	
		setTitle("Library Management");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(windowSize);
		setPreferredSize(windowSize);
		setLocation(windowPosition);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				FilesSave.saveUsers(userSystem.getUsers());
				FilesSave.saveBooks(bookModels.getTableModel().getBooks());
				FilesSave.saveClients(clientModels.getClients());
				//database.backUp();
			}
		});
		
		setDatabase(new Database());
		
		if (database.initConnection()) {
			
			setUserSystem(new UserSystem(DatabaseLoad.loadUsers(database.getConnection())));
			setBookModels(new BookModels(DatabaseLoad.loadBooks(database.getConnection())));
			setClientModels(new ClientModels(DatabaseLoad.loadClients(database.getConnection())));
			
		}
		else {
			
			setUserSystem(new UserSystem(FilesLoad.loadUsers()));
			setBookModels(new BookModels(FilesLoad.loadBooks()));
			setClientModels(new ClientModels(FilesLoad.loadClients()));
			
		}
		
		contentPane = new JPanel();
		contentPane.setLayout(new CardLayout());

		setLoginMenu(new LoginMenu(this));
		setMainMenu(new MainMenu(this));
		setBookRegister(new BookRegister(this));
		setClientRegister(new ClientRegister(this));
		setLendingBookMenu(new LendingBookMenu(this));
		
		contentPane.add(getLoginMenu(), LOGIN_MENU_STRING);
		contentPane.add(getMainMenu(), MAIN_MENU_STRING);
		contentPane.add(getBookRegister(), BOOK_REGISTER_STRING);
		contentPane.add(getClientRegister(), CLIENT_REGISTER_STRING);
		contentPane.add(getLendingBookMenu(), LENDING_BOOK_MENU_STRING);
		
		setContentPane(contentPane);
		pack();
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}
	
	private void calculateWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setWindowSize(new Dimension(screenSize.width/2, screenSize.height/2));
		setWindowPosition(new Point(screenSize.width/4, screenSize.height/4));
	}

	public Dimension getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(Dimension windowSize) {
		this.windowSize = windowSize;
	}

	public Point getWindowPosition() {
		return windowPosition;
	}

	public void setWindowPosition(Point windowPosition) {
		this.windowPosition = windowPosition;
	}

	public JPanel getContentPane() {
		return contentPane;
	}
	
	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public UserSystem getUserSystem() {
		return userSystem;
	}

	public void setUserSystem(UserSystem userSystem) {
		this.userSystem = userSystem;
	}

	public ClientModels getClientModels() {
		return clientModels;
	}

	public void setClientModels(ClientModels clientModels) {
		this.clientModels = clientModels;
	}

	public BookModels getBookModels() {
		return bookModels;
	}

	public void setBookModels(BookModels bookModels) {
		this.bookModels = bookModels;
	}

	public LoginMenu getLoginMenu() {
		return loginMenu;
	}

	public void setLoginMenu(LoginMenu loginMenu) {
		this.loginMenu = loginMenu;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public BookRegister getBookRegister() {
		return bookRegister;
	}

	public void setBookRegister(BookRegister bookRegister) {
		this.bookRegister = bookRegister;
	}

	public ClientRegister getClientRegister() {
		return clientRegister;
	}

	public void setClientRegister(ClientRegister clientRegister) {
		this.clientRegister = clientRegister;
	}

	public LendingBookMenu getLendingBookMenu() {
		return lendingBookMenu;
	}

	public void setLendingBookMenu(LendingBookMenu lendingBookMenu) {
		this.lendingBookMenu = lendingBookMenu;
	}

	public static String getLoginMenuString() {
		return LOGIN_MENU_STRING;
	}

	public static String getMainMenuString() {
		return MAIN_MENU_STRING;
	}

	public static String getBookRegisterString() {
		return BOOK_REGISTER_STRING;
	}

	public static String getClientRegisterString() {
		return CLIENT_REGISTER_STRING;
	}

	public static String getLendingBookMenuString() {
		return LENDING_BOOK_MENU_STRING;
	}
}