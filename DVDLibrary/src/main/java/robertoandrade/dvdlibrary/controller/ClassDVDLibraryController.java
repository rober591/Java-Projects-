/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.dvdlibrary.controller;
import robertoandrade.dvdlibrary.ClassDvdDaoException ;
import robertoandrade.dvdlibrary.dto.Dvd ;
import robertoandrade.dvdlibrary.ui.ClassDVDLibraryView ;
import dvdlibrary.dao.ClassDvdDao ;



import java.util.*;
/**
 *
 * @author  Roberto Andrade
 */
public class ClassDVDLibraryController {
    private ClassDVDLibraryView view ;
    private ClassDvdDao dao ;
    
    public ClassDVDLibraryController(ClassDvdDao dao, ClassDVDLibraryView view) {
    this.dao = dao;
    this.view = view;
    }
    
    private Dvd LookDVD() throws ClassDvdDaoException {
    //view.displayDisplayDVDBanner();
    String title = view.getDVDTitleChoice();
    List<Integer> resultsList  = dao.getDVDS(title);
    if(resultsList.size() > 1){
        view.DisplayOnlyOneDVD();
        int i=0;
        while (i < resultsList.size()) {
        view.displayDVDbyLine(dao.getDVD(resultsList.get(i)), resultsList.get(i));
        i++;
        }
        return dao.getDVD(view.getDVDSelection(resultsList));
    }else if(resultsList.size() == 1){
          return   dao.getDVD(resultsList.get(0));
    }else{
        return null;
    }
    }
    
    private int LookDVDIndex() throws ClassDvdDaoException {
    //view.displayDisplayDVDBanner();
    String title = view.getDVDTitleChoice();
    List<Integer> resultsList  = dao.getDVDS(title);
    if(resultsList.size() > 1){
        view.DisplayOnlyOneDVD();
        int i=0;
        while (i < resultsList.size()) {
        view.displayDVDbyLine(dao.getDVD(resultsList.get(i)), resultsList.get(i));
        i++;
        }
        return view.getDVDSelection(resultsList);
    }else if(resultsList.size() == 1){
          return   resultsList.get(0);
    }else{
        return -1;
    }
    }
    
    private void viewDVD()throws ClassDvdDaoException {
       /// view.displayDisplayDVDBanner();
       view.displayviewDVDBanner();
        view.displayDVD(LookDVD());
    }
    
    private void removeDVD()throws ClassDvdDaoException {
        view.displayRemoveTitleBanner();
        int index = LookDVDIndex();
        while(index < 0){
            view.displayNoSuchTitleBanner();
            index = LookDVDIndex();
            
        }
        Dvd toRemove = dao.removeDVD(index);
        view.displayDVD(toRemove);
        view.displayRemoveResult(toRemove);
    }
    
    private void AddDVD() throws ClassDvdDaoException {
    view.displayCreateDVDBanner();
    Dvd newDVD = view.getNewDVDInfo();
    dao.addDVD(newDVD.getTitle(), newDVD);
    view.displayCreateSuccessBanner();
}
    
    private void listDVDS() throws ClassDvdDaoException {
    view.displayDisplayAllDVDSBanner();
    List<Dvd> DvdList = dao.getAllDVDS();
    view.displayDVDList(DvdList); 
}
    
private void editDVD() throws ClassDvdDaoException {
    view.displayEditDVDBanner();
    int index = LookDVDIndex();
        if(index < 0){
            view.displayNoSuchTitleBanner();
            
        }else{
           Dvd toEdit = dao.getDVD(index); 
           view.editDVD(toEdit);
           dao.WriteToCollection();
        }
}
private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}
    
    private int getMenuSelection() {
    return view.printMenuAndGetSelection();
}
    
    public void run() {
    boolean keepGoing = true;
    int menuSelection = 0;
    try {
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    //listStudents();
                    listDVDS();
                    break;
                case 2:
                    AddDVD();
                    break;
                case 3:
                    viewDVD();
                    break;
                case 4:
                    removeDVD();
                    break;
                case 5:
                    editDVD();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    } catch (ClassDvdDaoException e) {
        view.displayErrorMessage(e.getMessage());
    }
}
    
    
    
    
}
