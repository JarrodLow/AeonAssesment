The API should allow API user to perform the following actions:
1. Register a new borrower to the library. ->add new borrower (POST)
2. Register a new book to the library. ->  add new book (POST)
3. Get a list of all books in the library. -> retrieve list of book (GET)

   The API should allow API user to perform the following actions on behalf of a borrower:
1. Borrow a book with a particular book id (
refer Book in Data Models). -> borrowing API
query -> check 
2. Return a borrowed book. -> Return book

ISBN validator
Valid ISBN is valid or nah
Since there are condition if the 
ISBN generated is same in db need to verify 
whether the author name and title is same or nah if not then generate new one


if generated ISBN is found in DB
-> verify req title and author is same with db value 
-> if is true then proceed with save
-> else proceed with regenerated value then save

title and author 
ISBN

//if author and title is exist and ISBN is exist 
-> update to exist ISBN and save as same
//if author and title is exist and ISBN not exist
-> proceed save 
// if author and title not exist and ISBN is exist
-> generate a new one and proceed save
// if author and title not exist and ISBN not exist
->  proceed save

//BookCount and CurrentBorrowedBook
BookCount = 0 
-> throw error (No book to borrow)
BookCount >0 && CurrentBorrowedBook >= BookCount
-> throw error (No Book is available to borrow)
BookCount >0 && CurrentBorrowedBook < BookCount
-> Proceed to borrow