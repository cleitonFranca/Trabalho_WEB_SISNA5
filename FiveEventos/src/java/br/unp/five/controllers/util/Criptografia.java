/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.controllers.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cleiton França
 */
public class Criptografia {

    public static String MD5(String pass) throws NoSuchAlgorithmException {

        String s = pass;

        MessageDigest m = MessageDigest.getInstance("MD5");

        m.update(s.getBytes(), 0, s.length());

        return new BigInteger(1, m.digest()).toString(16);
    }

}
