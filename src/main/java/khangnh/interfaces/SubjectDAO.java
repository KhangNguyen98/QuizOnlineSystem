/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import khangnh.subjects.SubjectDTO;
import khangnh.implementations.SubjectImpl;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author khang nguyen
 */
public interface SubjectDAO {
   List<SubjectDTO> getSubject() throws SQLException, NamingException;
   static SubjectImpl getInstance(){
       return new SubjectImpl();
   }
}
