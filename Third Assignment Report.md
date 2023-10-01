![image-20230306161149995](https://s2.uupload.ir/files/shahid_beheshti_icon_euaa.png)

​										

# 												 					THIRD ASSIGNMENT REPORT								

## 			           												Mohammad Hossein Basouli		   401222020

​									





















## Introduction:

- #### **A brief description of the program**:

  -  Our program provides a **library** along with it's **books**, **librarians**, **users** and their functionalities. (for example adding a **book** to **library** by **librarian** and ...)

- #### Objectives of our program:

  1. Allow new people to register their account in the **library** and having **user** permissions.
  2. Allow registered **users** to easily login and borrow **book**, see a list of books and ... 
  3. Allow **librarians** to login and have some accessibilities such as adding a **new user**, adding a **new librarian** and ...
  4. Program should provide a elegant **menu** for all of the **users** (including **ordinary users** and **librarians**) to let them know and do what defined in their objects and show meaningful messages when any process finishes.  

- #### Bonus  Implemented Objectives:

  1.Input validation : Ensure the data or information entered by a **user** or system is correct, complete, and appropriate for the intended use. For instance, a user shouldn't be able to **borrow** the same **book** twice, or return a **book** they haven't borrowed yet.

2. Implement Encapsulation in your code to protect the data and ensure that it can only be accessed through the defined setter/getter functions. Describe the difference between the distinct Java access modifiers in your report.

- #### An overview of our approach to build this program

  1. At first we have to create a class for every single one of our **objects**. (such as **library**, **user**, **librarian** and **book**)

  2. try to implant our **UML** for every each one of the **objects**. (**UML** brought below)

     

     ![image-20230306222728139](https://s2.uupload.ir/files/first_uml_g19h.png)

![	](https://s2.uupload.ir/files/second_uml_rofs.png)	



3. then in the **Main class** we implant some methods to show **menu** and navigate trough user page or **librarian page** and make use of functionalities.









## Design and Implementation:

- #### A Description to our runMenu():

1. First we initialize a default **librarian** and add a **book** to **library** and run menu:

   

   ```java
   public Library(){
   ...
   ...
       //default librarian
       Librarian default_librarian = new Librarian("Mobin Nesari", "1381");
       this.librarians.add(default_librarian);
   }
   public static void main(String[] args) {
   
       Library library = new Library();
       library.addBook("bigjava", "Dr.Kherad Pisheh", "2023", "0110");
       library.addUser("reza mosavi", "1400");
       runMenu(library);
   
   }
   ```

2. Provide a primitive menu to start our work and ask the user to choose an option:

   

   ```java
   public static void runMenu(Library library){
   
       System.out.println("------------------WELCOME TO THE LIBRARY------------------\nPLEASE CHOOSE ONE OPTION:");
       System.out.println("1-Signing up a new user\n2-Login as a user\n3-Login as a librarian\n4-Exit the program\n");
       int menu_option = input.nextInt();
       input.nextLine();   //bug handling
   
       switch (menu_option) {
           case 1 -> {
               //Signing up a new user
               userSignUp(library);
               runMenu(library);
           }
           case 2 -> {
               //Login as a user
               userLogin(library);
           }
           case 3 -> {
               //Login as a librarian
               librarianLogin(library);
           }
       }
   }
   ```



![image-20230306230225682](https://s2.uupload.ir/files/first_diagram_bp2u.png)





- For the **Option 1** we provide a menu that **user** can easily register:

  

  ```java
  public static void userSignUp (Library library){
      //sign up a new user
      System.out.println("Enter the requested information --> 1-Username,  2-Password");
      String username = input.nextLine();
      String password = input.nextLine();
  
      if (!library.doesUserExist(username) && !library.doesLibrarianExist(username)){
          //checks if username doesn't exist among users and librarians then add it
          library.addUser(username, password);
      }
      else {
          //if user exists we don't have the permission to add it
          System.out.println("This user already exist.");
          runMenu(library);   //back to the main menu
      }
  }
  ```





- **Option 2** provides a menu that user should enter his/her **username** and **password** and login to enter the **userPage()**:

  

  

  ![image-20230306231041926](https://s2.uupload.ir/files/second_diagram_cphe.png) 

  

  - In **userPage()** we have some functionalities for the **users**. (such as **renting a book** and ...)

    

    ![image-20230306231600692](https://s2.uupload.ir/files/third_diagram_3ew0.png)

    ```java
    public static void userPage(Library library, User user){
    
        System.out.println("------------------------------------------USER PAGE------------------------------------------");
        System.out.println("Welcome back " + user.getUsername().toUpperCase());
        System.out.println("1- ُShow a list of books\n2- Borrow a book\n3- Show a list of borrowed books\n4- Return a book\n5- Back to Main menu\n");
        int option_menu = input.nextInt();
        input.nextLine();
    
        switch (option_menu) {
            case 1 -> {
                //Show a list of books
                library.searchBook();
                userPage(library, user);    //back to the user page
            }
            case 2 -> {
                //Borrow a book from library
                System.out.println("Enter name of the book:\n");
                String book_name = input.nextLine();
    
                if (library.doesBookExist(book_name) && !user.hasRentedBook(book_name)) {
                    //if book exists in library and user didn't borrow that yet:
                    library.decreaseBook(library.getSpecifiedBook(book_name).getISBN());
                    user.rentBook(library.getSpecifiedBook(book_name));
                } else {
                    //if books doesn't exist in library, or we borrowed that before we cannot rent it
                    System.out.println("Book doesn't exist in library or you have borrowed that before.");
                }
                userPage(library, user);    //back to the user page
            }
            case 3 -> {
                //Show a list of borrowed books
                user.showBorrowedBooks();
                userPage(library, user);    //back to the user's page
            }
            case 4 -> {
                //Return a book
                if (user.getRented_books().size() == 0) {
    
                    System.out.println("User " + user.getUsername() + " has not borrowed any books yet!");
                } else {
    
                    System.out.println("Until now you have borrowed this list of books : ");
                    user.showBorrowedBooks();
                    System.out.println("Which one do you want to return?");
                    String book_name = input.nextLine();
    
                    if (user.hasRentedBook(book_name)) {
                        //checks if we rented this book
                        user.returnBook(library.getSpecifiedBook(book_name));
                        library.increaseBook(library.getSpecifiedBook(book_name).getISBN());
                    } else {
                        //if we didn't borrow this book, yet we don't have permission to return it
                        System.out.println("You haven't rent this book yet, so you cannot return it!");
                    }
                }
                userPage(library, user);    //back to user's page
            }
            case 5 -> {
                //Back to main menu
                runMenu(library);
            }
        }
    }
    ```





- **Option 3** provides a menu for **librarians** to easily login through it and then enter to **librarianPage()** and their functionalities.

  

  ![image-20230306232542218](https://s2.uupload.ir/files/fourth_diagram_tzx7.png)

​			

```java
public static void librarianLogin (Library library){

    System.out.println("Enter requested information: 1-username, 2-password");
    String username = input.nextLine();
    String password = input.nextLine();

    if (library.isLibrarianValid(username, password)){
        //if username and password is valid we have the permission to login
        System.out.println("Librarian " + username + " has been successfully login");
        librarianPage(library, library.getSpecifiedLibrarian(username));    //login as this librarian
    }
    else {
        //if username or password is invalid we don't have the permission to log in
        System.out.println("Username or Password is invalid.");
        runMenu(library);   //back to main menu
    }
}
```



- in **librarianPage()** we have some functionalities for **librarians**. (such as adding a **user** and...)

  

  ![image-20230306234431428](https://s2.uupload.ir/files/fiveth_diagram_eug7.png)

```java
public static void librarianPage (Library library, Librarian librarian) {

    System.out.println("--------------------------------------------------LIBRARIAN PAGE--------------------------------------------------");
    System.out.println("Welcome back " + librarian.getUsername());
    System.out.println("1- ُShow a list of book related commands\n2- Show a list of user related commands\n3- Show a list of librarian related commands\n4- Back to main menu\n");
    int option_menu = input.nextInt();
    input.nextLine();

    switch (option_menu) {
        case 1 -> {
            //Show a list of book - related commands
            System.out.println("1- Show a list of books\n2- Add a book\n3- Remove a book\n4- Update a book\n");
            option_menu = input.nextInt();
            input.nextLine();

            switch (option_menu) {
                case 1 -> {
                    //show a list of books
                    library.searchBook();
                    librarianPage(library, librarian);  //back to librarian's page
               }
                case 2 -> {
                    //Add a book to library
                    System.out.println("Enter requested information --> 1-Name of the book,  2-Author,  3-Year of publish,  4-ISBN");

                    Book new_book = new Book(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine());
                    if (library.doesBookExist(new_book.getBook_name())) {
                        //if book already exists in library, just increase its amount
                        library.increaseBook(new_book.getISBN());
                    } else {
                        //if book doesn't exist we have to add it first
                        library.addBook(new_book.getBook_name(), new_book.getAuthor(), new_book.getPublish_year(), new_book.getISBN());
                    }
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 3 -> {
                    //remove a book from library
                    System.out.println("Enter name of the book:");
                    String book_name = input.nextLine();
                    if (library.doesBookExist(book_name)){
                        //checks if book exists then remove it
                        library.removeBook(book_name);
                    }
                    else {
                        //else book doesn't exist we cannot remove it
                        System.out.println("This book doesn't really exist, so you cannot remove it");
                    }
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 4 -> {
                    //Update a book
                    System.out.println("Enter required information --> 1-ISBN of the book, 2-New name of the book, 3-New author of the book,  3-New year of publish");
                    Book book = new Book(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine());
                    library.updateBook(book.getISBN(), book.getBook_name(), book.getAuthor(), book.getPublish_year());  //checks and update if possible
                    librarianPage(library, librarian);  //back to librarian's page
                }
            }
        }
        case 2 -> {
            //Show a list of user - related commands
            System.out.println("1- Show a list of users\n2- Add a user\n3- Remove a user\n4- Update a user\n");
            option_menu = input.nextInt();
            input.nextLine();

            switch (option_menu) {
                case 1 -> {
                    //show a list of users
                    library.searchUser();
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 2 -> {
                    //Add a user
                    System.out.println("Enter required information --> 1-Username,  2-Password");
                    User new_user = new User(input.nextLine(), input.nextLine());

                    if (library.doesUserExist(new_user.getUsername()) || library.doesLibrarianExist(new_user.getUsername())) {
                        //if this username already exist among users or librarians, so we cannot add him/her
                        System.out.println("This username exist already!");
                    } else {
                        //else we can add it
                        library.addUser(new_user.getUsername(), new_user.getPassword());
                    }
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 3 -> {
                    //Remove a user
                    System.out.println("Enter name of the user you want to remove: ");
                    String username = input.nextLine();

                    if (!library.doesUserExist(username)){
                        //if user doesn't really exist we cannot remove it
                        System.out.println("This user doesn't exist, so you cannot remove him/her!");
                    }
                    else {
                        //else we can remove it
                        library.removeUser(username);
                    }
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 4 -> {
                    //Update a user
                    System.out.println("Enter required information --> 1-Old username 2-New username 3-New password");
                    library.updateUser(input.nextLine(), input.nextLine(), input.nextLine());
                    librarianPage(library, librarian);  //back to librarian's page
                }
            }
        }
        case 3 -> {
            //Show a list of librarian - related commands
            System.out.println("1- Show a list of librarians\n2- Add a librarian\n3- Remove a librarian\n4- Update a librarian\n");
            option_menu = input.nextInt();
            input.nextLine();

            switch (option_menu) {
                case 1 -> {
                    //Show a list of librarians
                    library.searchLibrarian();
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 2 -> {
                    //Add a librarian
                    System.out.println("Enter required information --> 1-Username,  2-Password");
                    Librarian librarian1 = new Librarian(input.nextLine(), input.nextLine());

                    if (library.doesUserExist(librarian1.getUsername()) || library.doesLibrarianExist(librarian1.getUsername())) {
                        //if username exist in users or librarians we cannot register it
                        System.out.println("This username has already registered!");
                    } else {
                        //else we have the permission to add it
                        library.addLibrarian(librarian1.getUsername(), librarian1.getPassword());
                    }
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 3 -> {
                    //Remove a librarian
                    System.out.println("Enter name of the librarian you want to remove: ");
                    String username = input.nextLine();
                    if (!library.doesLibrarianExist(username)){
                        //if librarian doesn't really exist we cannot remove him/her
                        System.out.println("This librarian doesn't exist, so you cannot remove him/her!");
                    }
                    else {
                        //else we can remove him/her
                        library.removeLibrarian(username);
                    }
                    librarianPage(library, librarian);  //back to librarian's page
                }
                case 4 -> {
                    //Update a librarian
                    System.out.println("Enter required information --> 1-Old username,  2-New username,  3-New password");
                    library.updateLibrarian(input.nextLine(), input.nextLine(), input.nextLine());
                    librarianPage(library, librarian);  //back to librarian's page
                }
            }
        }
        case 4 -> {
            //Back to main menu
            runMenu(library);
        }
    }
}
```



- #### A Description of Input Validation:

  - We validate our input in this events:

    1. When **user** wants to **signUp** a new account: We check if user is entering a **username** that exists already.

       ```java
       public static void userSignUp (Library library){
          //taking input...
           if (!library.doesUserExist(username) && !library.doesLibrarianExist(username)){
               //checks if username doesn't exist among users and librarians then add it
               //do some stuff...
           }
           else {
               //if user exists we don't have the permission to add it
               //do some other stuff
           }
       }
       ```

    2. When **user** wants to **login** as a registered user: We check if that **username** and **password** entered is correct or not.

       ```java
       public static void userLogin (Library library){
          //taking input...
           if (library.isUserValid(user.getUsername(), user.getPassword())){
               //checks if username and password is valid then login
               //do some stuff...
           }
           else {
               //if username or password is invalid we cannot log in
               //do some other stuff
           }
       }
       ```

    3. When **user** wants to **borrow** a **book** from **library**: We check to see if user borrowed this **book** before or this **book** even exists or not.

       ```java
       case 2 -> {
           //Borrow a book from library
           //taking input...
           if (library.doesBookExist(book_name) && !user.hasRentedBook(book_name)) {
               //if book exists in library and user didn't borrow that yet:
               //do some stuff
           } else {
               //if books doesn't exist in library, or we borrowed that before we cannot rent
               //do some other stuff
         }
       ```

    4. When **user** wants to return a **book** to **library**: We check if **user** has borrowed this **book** or not.

       ```java
       case 4 -> {
           //Return a book
           if (user.getRented_books().size() == 0) {
           //users has not borrowed any books yet, so cannot return a book.
           } else {
           //else we have atLeast borrowed some
               //taking input...
               if (user.hasRentedBook(book_name)) {
                   //checks if we rented this book
                   //do some stuff
               } else {
                   //if we didn't borrow this book, yet we don't have permission to return it
                   //do some other stuff
               }
         }
       ```

    5. When a **librarian** wants to login: We check the **username** and **password**:

       ```java
       public static void librarianLogin (Library library){
       //taking input...
           if (library.isLibrarianValid(username, password)){
               //if username and password is valid we have the permission to login
               //do some stuff...
           }
           else {
               //if username or password is invalid we don't have the permission to log in
               //do some other stuff
           }
       }
       ```

    6. When a **librarian** wants to remove a **book** from **library**: We check if entered **book** does exist in **library** or not.

       ```java
       case 3 -> {
           //remove a book from library
           //taking input...
           if (library.doesBookExist(book_name)){
               //checks if book exists then remove it
               //do some stuff
           }
           else {
               //else book doesn't exist we cannot remove it
               //do some other stuff
           }
       }
       ```

    7. When a **librarian** wants to update information of a **book**: We check if entered **book** does exists or not.

       ```java
       case 4 -> {
           //Update a book
           //taking input...
           //here update book is called and we check condition in it self
           library.updateBook(input.nextLine(), input.nextLine(), input.nextLine(),input.nextLine());  	//checks and update if possible
               ....
       }
       ....
       public void updateBook(String ISBN, String new_book_name, String new_author, String new_publish_year){
           //Update information of a book
           for (Book book : this.books){
       
               if (book.getISBN().equals(ISBN)){
                   //do some stuff
               }
           }
           //else not
           //do some other stuff
       }
       ```

    8. When a **librarian** wants to add a **user**: We check if **username** already exists.

       ```java
       case 2 -> {
           //taking input...
           //Add a user
           if (library.doesUserExist(new_user.getUsername()) ||library.doesLibrarianExist(new_user.getUsername())) {
               //if this username already exist among users or librarians, so we cannot add him/her
               //do some stuff
           } else {
               //else we can add it
               //do some other stuff
           }
       }
       ```

    9. When a **librarian** wants to remove a **user**: We check if **user** exists or not.

       ```java
       case 3 -> {
           //Remove a user
           //taking input...
       
           if (!library.doesUserExist(username)){
               //if user doesn't really exist we cannot remove it
       		//do some stuff
           }
           else {
               //else we can remove it
       		//do some other stuff
           }
       }
       ```

    10. When a **librarian** wants to **update** a **user**: We check if **user** exists or not.

        ```java
        case 4 -> {
            //Update a user
            //taking input...
            //here updateUser Called and condition will be check in it self.
            library.updateUser(input.nextLine(), input.nextLine(), input.nextLine());
            librarianPage(library, librarian);  //back to librarian's page
        }
        ...
        ...
        public void updateUser(String old_username, String new_username, String new_password){
            //updates information of a special user
            for (User user : this.users){
        
                if (user.getUsername().equals(old_username)){
        			//do some stuff
                }
            }
        	//do some other stuff
        }
        ```

    11. When a **librarian** wants to add a **new librarian**: We check if **username** already exists.

        ```java
        case 2 -> {
            //Add a librarian
            //taking input...
            if (library.doesUserExist(librarian1.getUsername()) ||library.doesLibrarianExist(librarian1.getUsername())) {
                //if username exist in users or librarians we cannot register it
                //do some stuff
            } else {
                //else we have the permission to add it
                //do some other stuff
            }
        }
        ```

    12. When a **librarian** wants to remove another **librarian**: We check if entered **librarian** exists or not.

        ```java
        case 3 -> {
            //Remove a librarian
            //taking input...
            if (!library.doesLibrarianExist(username)) {
                //if librarian doesn't really exist we cannot remove him/her
                //do some stuff
            } else {
                //else we can remove him/her
                //do some other stuff
            }
        }
        ```

    13. When a **librarian** wants to update information of another **librarian**: We check if entered **librarians** exists or not.

        ```java
         case 4 -> {
            //Update a librarian
            //taking input...
            //here updateLibrarian called and condition will be check in it self.
            library.updateLibrarian(input.nextLine(), input.nextLine(), input.nextLine());
            librarianPage(library, librarian);  //back to librarian's page
        }
        ...
        ...
        public void updateLibrarian(String old_username, String new_username, String new_password) {
            //Updates information for a special librarian
            for (Librarian librarian : this.librarians) {
        
                if (librarian.getUsername().equals(old_username)) {
                    //do some stuff
                }
            }
            //do some other stuff
        }
        ```

- #### An Brief Description to Access Modifiers in Java:

  - We used only two different access modifiers in our program:

    1. **Private**:
       - An access modifier that could be used for classes, methods and attributes and restricts access somehow that we cannot make use of that out of it's block

    2. **Public**:
       - An access modifier that could be used for classes, methods and attributes and restricts access somehow that we can make use of that out of it's block

     

## Testing and Evaluation:

1. First test case assumes that we are a **librarian** called "**Mobin Nesari**" and want to login and do some works as a **librarian**:

   ![image-20230306234929978](https://s2.uupload.ir/files/1_1xw7.png)

- here we test some functionalities such as **updating a book**  and after that see a list of **books**:

  ![image-20230307000131817](https://s2.uupload.ir/files/2_g0a5.png)

  ![image-20230307000150219](https://s2.uupload.ir/files/3_ytim.png)

- now we test another functionality of **librarians**. we want to register a new **librarian** called "**Mohammad Hossein Basouli**".

  ![image-20230307000549716](https://s2.uupload.ir/files/4_gqjc.png)

  ![image-20230307000620635](https://s2.uupload.ir/files/5_q8wg.png)

- everything works great in this testcase let's test another testcase.

  

2. In the second test case we want to login as a **user** called "**reza mosavi**" and test some functionalities.

![image-20230307000920511](https://s2.uupload.ir/files/6_ld4w.png)

- here we logged in and requested to show a list of **books**. now let's **borrow a book** and see what happens.

  ![image-20230307001112206](https://s2.uupload.ir/files/7_baci.png)

- now let's see what happens if we want to return a **book** that we haven't borrowed yet.

  ![image-20230307001222934](https://s2.uupload.ir/files/8_p65e.png)

- now let's see what happened to the list of **books** when we borrowed the **book**. then we return the **book** back to library and see what happens to list of **books** again

  ![image-20230307001451778](https://s2.uupload.ir/files/9_ji3m.png)

  ![image-20230307001525554](https://s2.uupload.ir/files/10_94o5.png)

  ![image-20230307001540997](https://s2.uupload.ir/files/11_fk66.png)

- also we see every thing works alright in this test case.
