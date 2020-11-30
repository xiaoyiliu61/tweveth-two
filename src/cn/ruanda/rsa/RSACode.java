package cn.ruanda.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/*
* 该类用于实现RSA算法的操作，包括密钥生成，加解密，签名验签等操作
* */
public class RSACode {
    static String data = "南昌的冬天忒冷了";
    public static void main(String[] args){
        System.out.println("hello world");
        RSACode code =new RSACode();
        KeyPair keyPair = code.createKey(1024);
        byte[] cipherTxt=  code.encrypt(data.getBytes(),keyPair.getPublic());
        System.out.println(cipherTxt);
        //调用解密方法进行解密
        byte[] originalTxt= code.decrypt(cipherTxt,keyPair.getPrivate());
        System.out.println(originalTxt);
    }

    public PrivateKey readPriByPem(String file_name){
        // TODO: 2020/11/30
        return null;
    }

    public PublicKey readPubByPem(String file_name){
        return null;
    }
    //==========================通过读取密钥文件恢复公钥和私钥
    public PublicKey loadPubByDer(String file_name){
        try {
            byte[] pubBytes = Files.readAllBytes(Paths.get(file_name));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pubBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(spec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param file_name
     * @return
     */
    public PrivateKey loadPriByDer(String file_name){
        try {
            byte[] PriBytes=Files.readAllBytes(Paths.get(file_name));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(PriBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(spec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对原文数据进行MD5的hash计算
     * @param data 原文
     * @return MD5的hash值
     */
    public byte[] md5Hash(byte[] data){
        //hash算法：消息摘要，message digest
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //实现私钥签名，公钥验签======================================

    /**
     * 对签名进行验证
     * @param sign 要验证的签名数据
     * @param data
     * @param pub
     * @return
     */
    public boolean verify(byte[] sign,byte[] data,PublicKey pub){
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(pub);
            byte[] hash = md5Hash(data);
            signature.update(hash);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param data 原文
     * @param pri 私钥
     * @return 签名后的数据
     */
    public byte[] sign(byte[] data,PrivateKey pri){
        //crypto/rsa crypto/des
        //rsa.SignPKCS1v15(rand,pri,hash,hashed)
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(pri);//为签名工具设置私钥
            //对原文进行hash计算
            byte[] hash=md5Hash(data);
            signature.update(hash);//更新要签名的数据
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }



    //生成密钥对-------------------

    /**
     * 根据传入的密钥生成的长度生成RSA的密钥对
     *
     * @param size
     * @return
     */
    public static KeyPair createKey(int size){
        //工厂：可以根据需求产生不同的类的实例
        //generate：生成
        try {
            //密钥生成器
            KeyPairGenerator generator=KeyPairGenerator.getInstance("RSA");
            generator.initialize(size);//设置密钥生成的长度
            KeyPair keyPair=generator.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用RSA算法私钥对数据进行解密
     *
     * @param cipherTxt 要解密的密文
     * @param pri
     * @return
     */
    public byte[] decrypt(byte[] cipherTxt,PrivateKey pri){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,pri);
            return cipher.doFinal(cipherTxt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param data 要加密的数据
     * @param pub 公钥
     */
    public  byte[] encrypt(byte[] data, PublicKey pub){
        //执行加密
        //java中专门用于加密或者解密的类：Cipher
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,pub);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
