/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.hashing;

import khangnh.interfaces.Hashing;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author khang nguyen
 */
public class PasswordHashing implements Hashing {

    @Override
    public String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
        byte[] contain = msgDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        String result = DatatypeConverter.printHexBinary(contain);
        return result;
    }
}
