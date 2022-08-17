/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package robertoandrade.dvdlibrary;
import robertoandrade.dvdlibrary.ClassDvdDaoException ;
import robertoandrade.dvdlibrary.dto.Dvd ;
import robertoandrade.dvdlibrary.ui.ClassDVDLibraryView;
import robertoandrade.dvdlibrary.ui.UserIOConsoleImple;
import robertoandrade.dvdlibrary.ui.UserIO;
import dvdlibrary.dao.ClassDvdDao;
import dvdlibrary.dao.ClassDvdlibraryDaoFileImpl;

import robertoandrade.dvdlibrary.controller.ClassDVDLibraryController;



import dvdlibrary.dao.ClassDvdDao ;

/**
 *
 * @author  Roberto Andrade
 */
public class App {

    public static void main(String[] args) {
    UserIO myIo = new UserIOConsoleImple();
    ClassDVDLibraryView myView = new ClassDVDLibraryView(myIo);
    ClassDvdDao myDao = new ClassDvdlibraryDaoFileImpl();
    ClassDVDLibraryController controller = new ClassDVDLibraryController(myDao, myView);
    controller.run();
        
    }
}
