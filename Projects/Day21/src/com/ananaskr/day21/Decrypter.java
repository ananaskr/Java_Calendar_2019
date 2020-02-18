package com.ananaskr.day21;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.servlet.http.*;

@Controller
public class Decrypter {
    @RequestMapping("/decrypt")
    public void decrypt(HttpServletRequest req, HttpServletResponse res) throws IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, NoSuchPaddingException, DecoderException, InvalidKeySpecException {
        // Payload to decrypt: 699c99a4f27a4e4c310d75586abe8d32a8fc21a1f9e400f22b1fec7b415de5a4

        char[] c = req.getParameter("c").toCharArray();
        byte[] cipher = Hex.decodeHex(c);
        byte[] salt = new byte[]{(byte)0x12,(byte)0x34,(byte)0x56,(byte)0x78,(byte)0x9a,(byte)0xbc,(byte)0xde};
        // Extract IV.
        byte[] iv = new byte[16];
        System.arraycopy(cipher, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        byte[] encryptedBytes = new byte[cipher.length - 16];
        System.arraycopy(cipher, 16, encryptedBytes, 0, cipher.length - 16);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        // Of course the password is not known by the attacker - just for testing purposes
        KeySpec spec = new PBEKeySpec("SuperSecurePassword".toCharArray(), salt, 65536, 128);
        SecretKey key = factory.generateSecret(spec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getEncoded(), "AES");
        // Decrypt.
        try {
            Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);
            res.getWriter().println(decrypted);
            // Do something.
        } catch (BadPaddingException e) {
            res.getWriter().println("Invalid Padding!!");
        }
    }
}

