/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.dvdlibrary.dto;

/**
 *
 * @author  Roberto Andrade
 */
public class Dvd {
    private String Title;
    private String ReleaseDate ;
    private int Rating;
    private String DirectorsName ;
    private String Studio ;
    private int UserRating;
    private String note;
    
    public Dvd(String Title , String ReleaseDate ,int Rating , String DirectorsName , String Studio  , int UserRating ,String note) {
        this.Title = Title;
        this.ReleaseDate = ReleaseDate;
        this.Rating = Rating;
        this.DirectorsName = DirectorsName;
        this.Studio = Studio;
        this.UserRating = UserRating;
        this.note = note; 
    }
    
    public Dvd(String Title){
        this.Title = Title;
        this.ReleaseDate = "";
        this.Rating = 0;
        this.DirectorsName = "";
        this.Studio = "";
        this.UserRating = 0;
        this.note = ""; 
    }

    // ****** GETTERS AND SETTERS 
    public String getTitle() {
        return Title;
    }
     public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getReleaseDate() {
        return ReleaseDate;
    }
     public void setReleaseDate(String ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }
     public int getRating() {
        return Rating;
    }
     public void setRating(int Rating) {
        this.Rating = Rating;
    }
     public String getDirectorsName() {
        return DirectorsName;
    }
     public void setDirectorsName(String DirectorsName) {
        this.DirectorsName = DirectorsName;
    }
     public String getStudio() {
        return Studio;
    }
     public void setStudio(String Studio) {
        this.Studio = Studio;
    }
     public int getUserRating() {
        return UserRating;
    }
     public void setUserRating(int UserRating) {
        this.UserRating = UserRating;
    }
     public String getnote() {
        return note;
    }
     public void setnote(String note) {
        this.note = note;
    }
    // ******   END  GETTERS AND SETTERS 




}
