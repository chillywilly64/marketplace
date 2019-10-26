package com.epam.mentoring.springboot.hash;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashPassword {
    private static int workload = 12;

    public static String hashPassword(String password){
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String storedHash) {

        if(null == storedHash || !storedHash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison");

        return BCrypt.checkpw(password, storedHash);
    }
}
