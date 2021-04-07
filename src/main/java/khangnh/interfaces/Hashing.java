/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import khangnh.hashing.PasswordHashing;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author khang nguyen
 */
public interface Hashing {
    String hash(String password) throws NoSuchAlgorithmException;
    static PasswordHashing getInstance() {
        return new PasswordHashing();
    }
}
