# Library-Management-App
GUI Java app, based on Swing, which helps public libraries manage the clients, the books and their rental. People with personal library can also track the books they have and the people to which they lend books.

When you start the app you will go to the login page, where you can sign in new users (the maximum number is 10) or you can log in into the system
After that you go to the main menu. On the left there are the options which include book/client register, lend a book, add a book/client and log out.
On the right there is a simple log that shows what actions were taken in this "session" i.e. the run of the program
	(I plan to add a custom cell renderer to each entry on the list, and the option to select it to see more info about the action, for example "Added a new book",
	you click it and you see what book was added and eventually a cover photo. There will also be added an option to see past logs).

The differrent menu options:

1. Book and Client register.
	These are two pages where you can see respectively all the books and clients added to the system in the form of a table.
	Each book has id, title, author and marker to show if it is taken or not.
	Each client has id, name, age, email and telephone.
	You can click on each column to sort the table by it in ascending or descending order.
	On the bottom you have a search bar and options (add book/client or got back to the main menu).
	If you select an item, then on the bottom appear more item specific options (remove the item, edit it or cancel the selection)
  
2. Lend a book option (still working on it).
	When you choose it, it goes to a new page where on the left is a simplified view of all the clients and you can choose the client which will get books.
	After that the list will be swaped for a info card of the client (A photo, personal information and a list of the past and currently taken books).
	On the right there will be a simplified view of all the free books where you can choose the books to lend.
  
3. Add book/client.
	These are just shortcuts for the add book/client in the register pages. When you click on them a new window pops out, which prompts you to enter the information
		for the new item.
	For the books there are no restrictions on teh data entered.
	For the client there are restrictions which are as follows:
	The name must contain two words that start with upper case letter and after that have only lowercase letters. You can type in latin or cyrilic alphabet.
	The age must be a decimal number.
	The email must look like an email with @ symbol, .com or other type of modifier.
	The telephone respects teh bulgarian phone number pattern: starts with a zero or +359 and has 9 numbers after that
    
 4. Log out option
 	Pretty straightforward :)
  
The information about the user log in info, the books and clients that are added are stored in .txt files
