/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dvdlibrary.dao;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import robertoandrade.dvdlibrary.ClassDvdDaoException ;
import robertoandrade.dvdlibrary.dto.Dvd ;



/**
 *
 * @author  Roberto Andrade
 */
public class ClassDvdlibraryDaoFileImpl implements ClassDvdDao{
    
        //private Map<String, Dvd> DVDLibrary = new HashMap<>();
        private List<Dvd> DVDLibrary = new ArrayList<Dvd>();

        public static final String DVDLIBRARY_FILE = "DVDlibrary.txt";
            public static final String DELIMITER = "::";



    
    
    private void writeLibrary() throws ClassDvdDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
    // we are translating it to an application specific exception and 
    // then simple throwing it (i.e. 'reporting' it) to the code that
    // called us.  It is the responsibility of the calling code to 
    // handle any errors that occur.
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
    } catch (IOException e) {
        throw new ClassDvdDaoException(
                "Could not save student data.", e);
    }

    // Write out the Student objects to the roster file.
    // NOTE TO THE APPRENTICES: We could just grab the student map,
    // get the Collection of Students and iterate over them but we've
    // already created a method that gets a List of Students so
    // we'll reuse it.
    String DVDAsText;
    //List<Student> studentList = this.getAllStudents();
    for (Dvd currentDvd : DVDLibrary) {
        // turn a Student into a String
        DVDAsText = marshallStudent(currentDvd);
        // write the Student object to the file
        out.println(DVDAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
    }
    
    private String marshallStudent(Dvd Dvdtomarshall){
        // We need to turn a Student object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // 4321::Charles::Babbage::Java-September1842

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the dvd title, since that's supposed to be first.
    String DVDAsText = Dvdtomarshall.getTitle() + DELIMITER;

    // add the rest of the properties in the correct order:

    // release date
    DVDAsText += Dvdtomarshall.getReleaseDate() + DELIMITER;

    // rating
    DVDAsText += Dvdtomarshall.getRating() + DELIMITER;
    // directors name
    DVDAsText += Dvdtomarshall.getDirectorsName() + DELIMITER;
     // studio
    DVDAsText += Dvdtomarshall.getStudio() + DELIMITER;
    // user rating
    DVDAsText += Dvdtomarshall.getUserRating() + DELIMITER;
    //  - don't forget to skip the DELIMITER here.
    DVDAsText += Dvdtomarshall.getnote();

    // We have now turned a student to text! Return it!
    return DVDAsText;
        
    }
    
    
    
    private void loadLibrary() throws ClassDvdDaoException {
    Scanner scanner;
    DVDLibrary.removeAll(DVDLibrary);

    try {
        // Create Scanner for reading the file
        scanner = new Scanner( new BufferedReader(new FileReader(DVDLIBRARY_FILE)));
    } catch (FileNotFoundException e) {
        throw new ClassDvdDaoException(
                "-_- Could not load library data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentStudent holds the most recent student unmarshalled
    Dvd currentDVD;
    // Go through DVDLIBRARY_FILE line by line, decoding each line into a 
    // Dvd  object by calling the unmarshallStudent method.
    // Process while we have more lines in the file
    while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a Student
        currentDVD = unmarshallDVD(currentLine);

        // We are going to use the student id as the map key for our student object.
        // Put currentStudent into the map using student id as the key
        DVDLibrary.add(currentDVD);
    }
    // close scanner
    scanner.close();
}
    
    private Dvd unmarshallDVD(String DVDAsText){
        // studentAsText is expecting a line read in from our file.
    // For example, it might look like this:
    // 1234::Ada::Lovelace::Java-September1842
    //
    // We then split that line on our DELIMITER - which we are using as ::
    // Leaving us with an array of Strings, stored in studentTokens.
    // Which should look like this:
    // ______________________________________
    // |    |   |        |                  |
    // |1234|Ada|Lovelace|Java-September1842|
    // |    |   |        |                  |
    // --------------------------------------
    //  [0]  [1]    [2]         [3]
    String[] DVDTokens = DVDAsText.split(DELIMITER);
    

    // Given the pattern above, the student Id is in index 0 of the array.
    String title = DVDTokens[0];

    // Which we can then use to create a new Student object to satisfy
    // the requirements of the Student constructor.
    Dvd DVDFromFile = new Dvd(title);

    // However, there are 3 remaining tokens that need to be set into the
    // new student object. Do this manually by using the appropriate setters.

    // Index 1 - release date
    DVDFromFile.setReleaseDate(DVDTokens[1]);

    // Index 2 - rating
    DVDFromFile.setRating(Integer.parseInt(DVDTokens[2]));

    // Index 3 - directors name
    DVDFromFile.setDirectorsName(DVDTokens[3]);
    
    // Index 4 - studio
    DVDFromFile.setStudio(DVDTokens[4]);
    
     // Index 5 - user rating
    DVDFromFile.setUserRating(Integer.parseInt(DVDTokens[5]));
    
    // Index 6 - notes
    DVDFromFile.setnote(DVDTokens[6]);

    // We have now created a student! Return it!
    return DVDFromFile;
    }
    
    @Override
    public Dvd addDVD(String title, Dvd movie) throws ClassDvdDaoException {  
        loadLibrary();
        DVDLibrary.add(movie);
        writeLibrary();
        return movie;
     }
    
    @Override
     public ArrayList<Dvd> getAllDVDS() throws ClassDvdDaoException {
    loadLibrary();
    //List<Dvd> cloned_list = new ArrayList<Dvd>(DVDLibrary);
    //return cloned_list;
    return new ArrayList<Dvd>(DVDLibrary);
     }
     
    @Override
    public ArrayList<Integer> getDVDS(String title  ) throws ClassDvdDaoException {
    loadLibrary();
    ArrayList<Integer> results = new ArrayList<Integer>();
    int index = 0;
    for (Dvd movie : DVDLibrary) {
        if (movie.getTitle().equalsIgnoreCase(title)) {
            //System.out.println("index mach "+index);
            results.add(index);
        }
        index ++;
    }
    return results;
    }
    
    @Override
    public Dvd getDVD(int index)throws ClassDvdDaoException{
    loadLibrary();
    return DVDLibrary.get(index);
    }
    
   
    @Override
    public Dvd removeDVD(int  index)throws ClassDvdDaoException{ // LOOK FOR INDEX
        loadLibrary();
        Dvd removedTitle = DVDLibrary.remove(index);
        writeLibrary();
        return removedTitle;
    }
    
    
    @Override
    public void WriteToCollection()throws ClassDvdDaoException{
        writeLibrary();
    }
    
   
    
     
    
    
}
