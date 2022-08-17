/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import robertoandrade.Dto.Product;

/**
 *
 * @author Roberto
 */
public class DAOFileImpl implements DaoInterface{
    private final String PRODUCTS_FILE;
    //public static final String PRODUCTS_FILE = "Products.txt";
    public static final String DELIMITER = "::";
    
//-------- Constructors added to junit testing------
    public DAOFileImpl(){
    PRODUCTS_FILE = "Products.txt";
}

public DAOFileImpl(String ProductsTextFile){
    PRODUCTS_FILE = ProductsTextFile;
}
//---------------------------------------------------
    
    private List< Product> DBproducts = new ArrayList<Product>();
    
    private void writeProducts() throws ClassDAOPersistenceException {
    PrintWriter out;
    try {
        out = new PrintWriter(new FileWriter(PRODUCTS_FILE));
    } catch (IOException e) {
        throw new ClassDAOPersistenceException(
                "Could not save Products data.", e);
    }
    String ProductAsText;
    List<Product> ProductList = this.getAllProducts();
    for (Product currentProduct : ProductList) {
        // turn a Student into a String
        ProductAsText = marshallProduct(currentProduct);
        // write the Student object to the file
        out.println(ProductAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}
    
private String marshallProduct(Product product){
    // ID
    String productAsText = product.getID() + DELIMITER;
    // Name
    productAsText = product.getName() + DELIMITER;
    // Price
    productAsText += product.getPrice() + DELIMITER;
    // Quantity
    productAsText += product.getQuanity() + DELIMITER;
    // We have now turned a student to text! Return it!
    return productAsText;
}

private void loadRoster() throws ClassDAOPersistenceException {
Scanner scanner;
DBproducts.removeAll(DBproducts);
try {
        // Create Scanner for reading the file
        scanner = new Scanner( new BufferedReader(new FileReader(PRODUCTS_FILE)));
    } catch (FileNotFoundException e) {
        throw new ClassDAOPersistenceException(
                "-_- Could not load roster data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentStudent holds the most recent student unmarshalled
    Product currentProduct;
    // Go through PRODUCTS_FILE line by line, decoding each line into a 
    // Product object by calling the unmarshallStudent method.
    // Process while we have more lines in the file
    // ID IA ALWAYS THE SAME BECAUSE FILE ORDER DOESN'T CHANGE.
  int id = 1;
    while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a product
        currentProduct = unmarshallProduct(currentLine);
        currentProduct.setID(id++);     
        DBproducts.add(currentProduct); 
    }
    // close scanner
    scanner.close();
    
}

 private Product unmarshallProduct(String productAsText){
    // productAsText is expecting a line read in from our file.
    
    //
    // We then split that line on our DELIMITER - which we are using as ::
    // Leaving us with an array of Strings, stored in studentTokens.
    // Which should look like this:
    
    String[] ProductTokens = productAsText.split(DELIMITER);

    // Given the pattern above, the student Id is in index 0 of the array.
    //String ProducttId = studentTokens[0];

    // Which we can then use to create a new Student object to satisfy
    // the requirements of the Student constructor.
    Product productFromFile = new Product();
    // Index 1 - FirstName
    productFromFile.setName(ProductTokens[0]);

    // Index 2 - LastName
    productFromFile.setPrice(ProductTokens[1]) ;

    // Index 3 - Cohort
    productFromFile.setQuantity(Integer.parseInt(ProductTokens[2])); 

    // We have now created a student! Return it!
    return productFromFile;
}
 
  @Override
 public List<Product> getAllProducts() throws ClassDAOPersistenceException {
    loadRoster();
   // LAMBDA FUNCTION WITH FILTER FOR PRODUCTS WITH INVENTORY  > 0
    List<Product> Inventory = DBproducts.stream()
    .filter((p) -> p.getQuanity() >= 0)
    .collect(Collectors.toList());

    return new ArrayList<Product>(Inventory);
}
 
 @Override
 public Product getProduct(int ID) throws ClassDAOPersistenceException{
     loadRoster();
     for (Product currentProduct : DBproducts) {
        if(currentProduct.getID() == ID){
            return currentProduct;
        }

     }
     return null;
     
 }
 
 
 @Override
 public void ChangeInventory(int ProducCode )throws ClassDAOPersistenceException {
     loadRoster();
     PrintWriter out;
    try {
        out = new PrintWriter(new FileWriter(PRODUCTS_FILE));
    } catch (IOException e) {
        throw new ClassDAOPersistenceException(
                "Could not save Transaction.", e);
    }
    String ProductAsText;
    for (Product currentProduct : DBproducts) {
        if(currentProduct.getID() == ProducCode){
            currentProduct.setQuantity(currentProduct.getQuanity() -1);
            
        }
        // turn a Product into a String
        ProductAsText = marshallProduct(currentProduct);
        // write the Product object to the file
        out.println(ProductAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
    
 }

 
}
