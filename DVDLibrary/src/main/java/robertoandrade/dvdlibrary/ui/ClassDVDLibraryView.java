/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.dvdlibrary.ui;
import java.util.List;
import robertoandrade.dvdlibrary.ClassDvdDaoException ;
import robertoandrade.dvdlibrary.dto.Dvd ;
import java.util.*;

/**
 *
 * @author  Roberto Andrade
 */
public class ClassDVDLibraryView {
    private UserIO io;
    
    public void displayErrorMessage(String errorMsg) {
    io.print("============ ERROR ============");
    io.print(errorMsg);
}
    
    public ClassDVDLibraryView(UserIO io) {
    this.io = io;
}
    
    public void displayRemoveTitleBanner () {
    io.print("============ Remove Title ============");
}
    
    public void displayEditDVDBanner () {
    io.print("============ Edit Title ============");
}
    
    public void displayExitBanner() {
    io.print("     Good Bye!!!    ");
}
    
public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}

public void displayNoSuchTitleBanner() {
    io.print("There are no titles with that name in the Collection");
}

public void editDVD(Dvd ToEdit){
    io.print("===== Edit Info from this DVD =====");

     String option;
     String value;
     int rate;
     option = io.readString("Do you want to Edit the Title for this DVD ??  (yes/no)");
     if(option.equalsIgnoreCase("yes")){       
        value = io.readString("Please enter new Title for this DVD");
        ToEdit.setTitle(value);
     }else if(!option.equalsIgnoreCase("no")){
         io.print(" That we will take it as NO");
     }
     
     option = io.readString("Do you want to Edit the Release Date for this DVD ??  (yes/no)");
     if(option.equalsIgnoreCase("yes")){       
        value = io.readString("Please enter new Release Date for this DVD");
        ToEdit.setReleaseDate(value);  
     }else if(!option.equalsIgnoreCase("no")){
         io.print(" That we will take it as NO");
     }
     
     option = io.readString("Do you want to Edit the MPAA rating for this DVD ??  (yes/no)");
     if(option.equalsIgnoreCase("yes")){       
        rate = io.readInt("Please enter new Release Date for this DVD");
        ToEdit.setRating(rate);   
     }else if(!option.equalsIgnoreCase("no")){
         io.print(" That we will take it as NO");
     }
     
     option = io.readString("Do you want to Edit the Director's name for this DVD ??  (yes/no)");
     if(option.equalsIgnoreCase("yes")){       
        value = io.readString("Please enter new Director's name for this DVD");
        ToEdit.setDirectorsName(value);   
     }else if(!option.equalsIgnoreCase("no")){
         io.print(" That we will take it as NO");
     }
     
     option = io.readString("Do you want to Edit the Studio for this DVD ??  (yes/no)");
     if(option.equalsIgnoreCase("yes")){       
        value = io.readString("Please enter new Studio for this DVD");
        ToEdit.setStudio(option);    
     }else if(!option.equalsIgnoreCase("no")){
         io.print(" That we will take it as NO");
     }
     
     option = io.readString("Do you want to Edit the User rating for this DVD ??  (yes/no)");
     if(option.equalsIgnoreCase("yes")){       
        rate = io.readInt("Please enter new User rating for this DVD");
        ToEdit.setUserRating(rate);     
     }else if(!option.equalsIgnoreCase("no")){
         io.print(" That we will take it as NO");
     }
     
     option = io.readString("Do you want to Edit the notes for this DVD ??  (yes/no)");
     if(option.equalsIgnoreCase("yes")){       
        value = io.readString("Please enter new notes for this DVD");
        ToEdit.setnote(value);     
     }else if(!option.equalsIgnoreCase("no")){
         io.print(" That we will take it as NO");
     }
     
     io.print("====== DVD Title successfully Edited. ======");
    
}

public void displayRemoveResult(Dvd DVDRecord) {
    if(DVDRecord != null){
      io.print("===== DVD Title successfully removed. =====");
    }else{
      io.print("No such Title.");
    }
    io.readString("Please hit enter to continue.");
}
    
    public void displayDisplayDVDBanner () {
    io.print("==========  Display DVD Title ==========");
}

public String getDVDTitleChoice() {
    return io.readString("Please enter the DVD Title.");
}

public void displayDVD(Dvd CD) {
    if (CD != null) {
        io.print("====== DVD INFO  ======");
        io.print("Title: "+CD.getTitle());
        io.print("Release Date: "+CD.getReleaseDate());
        io.print("Rating: "+Integer.toString(CD.getRating()));
        io.print("Directors Name: "+CD.getDirectorsName());
        io.print("Studio: "+CD.getStudio());
        io.print("User Rating: "+Integer.toString(CD.getUserRating()));
        io.print("Notes: "+CD.getnote());
        io.print("=======================");
    } else {
        io.print("****  No such DVD Title.  *** ");
    }
    io.readString("Please hit enter to continue.");
}

public int getDVDSelection(List<Integer> parameterList){
    int num = -1;
        io.print("======================================================");
        //io.print("From the List Above enter the code# of the DVD you want to select ");
        num = io.readInt("From the List Above enter the code# of the DVD you want to select");
        while(!parameterList.contains(num)){
           num = io.readInt("*** Please enter a valid code from the list above *** ");
        }
        return num;

}

public void  DisplayOnlyOneDVD(){
    io.print("======================================================");
    io.print("       Lists of DVDs with the same Title");
    String DVDInfo = String.format("#%s : %s : %s : %s : %s : %s : %s : %s",
              "Code",
              "Title",
              "Release Date",
              "Rating",
              "Director's Name",
              "Studio",
              "User Rating",
              "Note");
        io.print(DVDInfo);
        
}



public int printMenuAndGetSelection() {
        io.print("---------------------------------------------------------------");
        io.print("Main Menu");
        io.print("1. List the DVDs in the collection");
        io.print("2. Add a DVD to the collection");
        io.print("3. Search and view  a DVD  by Title");
        io.print("4. Remove a DVD from the collection");
        io.print("5. Edit the information of an existing DVD in the collection");
        io.print("6. Exit");
        io.print("---------------------------------------------------------------");
        return io.readInt("Please select from the above choices.", 1, 6);
    }

public Dvd getNewDVDInfo() {
    String title = io.readString("Please enter DVD  Title");
    String releasedate = io.readString("Please enter Release date");
    int rating = io.readInt("Please enter MPAA rating");
    String directorsName = io.readString("Please enter The Director's name");
    String studio = io.readString("Please enter The Studio's name");
    int UserRating;
    String optionRating = io.readString("Do you want to input a Rating for this Title ??  (yes/no)");
    while(!optionRating.equalsIgnoreCase("yes") && !optionRating.equalsIgnoreCase("no")){
         optionRating = io.readString("WRONG INPUT - Do you want to input a Rating for this Title ??  (yes/no)");
    }
    if(optionRating.equalsIgnoreCase("yes")){
             UserRating = io.readInt("Please enter User rating");

    }else{
        UserRating = 0;
    }
    String note; 
    String optionNotes = io.readString("Do you want to input a Comments/Notes for this Title ??  (yes/no)");
    while(!optionNotes.equalsIgnoreCase("yes") && !optionNotes.equalsIgnoreCase("no")){
         optionNotes = io.readString(" WRONG INPUT - Do you want to input a Comments/Notes for this Title ??  (yes/no)");
    }
    if(optionNotes.equalsIgnoreCase("yes")){
             note  = io.readString("Please enter Note Comments");

    }else{
        note = "N/A";
    }
    Dvd currentDVD = new Dvd(title);
    currentDVD.setReleaseDate(releasedate);
    currentDVD.setRating(rating);
    currentDVD.setDirectorsName(directorsName);
    currentDVD.setStudio(studio);
    currentDVD.setUserRating(UserRating);
    currentDVD.setnote(note);
        
    return currentDVD;
}

public void displayCreateDVDBanner() {
    io.print("======= Create A  DVD =======");
}

public void displayviewDVDBanner(){
    io.print("======= View a DVD  =======");
}

public void displayCreateSuccessBanner() {
    io.readString(
            "====== DVD successfully created. ======= '\n'  Please hit enter to continue");
}

public void displayDVDbyLine(Dvd CD , int index){
    if (CD != null) {
        String DVDInfo = String.format("#%s : %s : %s : %s : %s : %s : %s : %s",
               index,
              CD.getTitle(),
              CD.getReleaseDate(),
              CD.getRating(),
              CD.getDirectorsName(),
              CD.getStudio(),
              CD.getUserRating(),
              CD.getnote()
              );
        io.print(DVDInfo);
    } else {
        io.print("****  No such DVD Title.  *** ");
    }
    //io.readString("Please hit enter to continue.");
}

public void displayDVDList(List<Dvd> Collection) {
    int k=0;
    for (Dvd currentDvd : Collection) {
        String DVDInfo = String.format("#%s : %s : %s : %s : %s : %s : %s : %s",
               k,
              currentDvd.getTitle(),
              currentDvd.getReleaseDate(),
              currentDvd.getRating(),
              currentDvd.getDirectorsName(),
              currentDvd.getStudio(),
              currentDvd.getUserRating(),
              currentDvd.getnote()
              
              );
        io.print(DVDInfo);
        k ++;
    }
    io.print("========================================");
    io.readString("Please hit enter to continue.");
}

public void displayDisplayAllDVDSBanner() {
    io.print("========== Display All DVD Titles ==========");
}

}
