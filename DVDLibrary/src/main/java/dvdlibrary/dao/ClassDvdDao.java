/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dvdlibrary.dao;
import java.util.ArrayList;
import robertoandrade.dvdlibrary.dto.Dvd ;
import robertoandrade.dvdlibrary.ClassDvdDaoException ;

import java.util.List;

/**
 *
 * @author Roberto Andrade
 */
public interface ClassDvdDao {
    Dvd addDVD( String title ,Dvd movie)
     throws ClassDvdDaoException;

    /**
     * Returns a List of all Students on the roster. 
     * 
     * @return Student List containing all students on the roster.
     * @throws ClassRosterDaoException
     */
    List<Dvd> getAllDVDS()
     throws ClassDvdDaoException;

    /**
     * Returns the student object associated with the given student id.
     * Returns null if no such student exists
     * 
     * @param studentId ID of the student to retrieve
     * @return the Student object associated with the given student id,  
     * null if no such student exists
     * @throws ClassRosterDaoException
     */
    List<Integer> getDVDS(String title )
     throws ClassDvdDaoException;
    
    Dvd getDVD(int index )
     throws ClassDvdDaoException;

    /**
     * Removes from the roster the student associated with the given id. 
     * Returns the student object that is being removed or null if 
     * there is no student associated with the given id
     * 
     * @param studentId id of student to be removed
     * @return Student object that was removed or null if no student 
     * was associated with the given student id
     * @throws ClassRosterDaoException
     */
    Dvd removeDVD(int index)
     throws ClassDvdDaoException;
    
    
    void WriteToCollection()
     throws ClassDvdDaoException;
}


