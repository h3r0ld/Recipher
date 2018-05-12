package hu.herold.mobsoft.recipher.utils.password;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.Executor;

import hu.herold.mobsoft.recipher.db.entity.PasswordEntity;

/**
 * Created by herold on 2018. 05. 12..
 */

public class PasswordService {

    public PasswordEntity hashPassword(String password) throws NoSuchAlgorithmException {

        String salt = generateSalt();

        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setSalt(salt);

        String hashedPassword = hashPassword(password, salt);

        passwordEntity.setPasswordHash(hashedPassword);

        return passwordEntity;
    }

    public String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = null;

        digest = MessageDigest.getInstance("SHA-256");

        String saltedPassword = password + salt;

        digest.update(saltedPassword.getBytes());

        String hash = bytesToHexString(digest.digest());

        Log.i("PasswordService", "Hash: " + hash);

        return hash;
    }

    private String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private String generateSalt() {
        return UUID.randomUUID().toString();
    }
}
