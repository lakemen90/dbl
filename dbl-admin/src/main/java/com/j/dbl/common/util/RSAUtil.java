package com.j.dbl.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.SortedMap;
import java.util.TreeMap;

public class RSAUtil {

    public static final String ALGORITHM = "RSA";
    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    public static final String pubilc_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx20ZnWbwE7Avain0KupP" +
            "YGA/YKRO/Bi/LJpr8xkkswDbshlgvaMd3LDw7ig64OTe4X4iH5NZDAQQCZEVafE9" +
            "0wHngTJLHeOrTA7bTVo1V0K9pBZu28ZQfpz12wWtfM+1Ov5ESpd4N//PhVDt4Txb" +
            "kn+q9Y+W5X6xDnMY3UVOomENWRKMFk+/H0rdtVBotsq0D66OMYmRH8OcbAx0SjbK" +
            "PLwpWgYhFKkmCOR7UlPE9QEFCmnvTmIfIylN3yhbU2vXzfpWb/IyeM9sKgx5o08U" +
            "vY0rWs8n3sqfnXxbCgkCAWqEhoUkwb8hs30IqxsvVx+SVByxFBBuF89EvBhg/xPt" +
            "/QIDAQAB";
    public static final String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHbRmdZvATsC9q" +
            "KfQq6k9gYD9gpE78GL8smmvzGSSzANuyGWC9ox3csPDuKDrg5N7hfiIfk1kMBBAJ" +
            "kRVp8T3TAeeBMksd46tMDttNWjVXQr2kFm7bxlB+nPXbBa18z7U6/kRKl3g3/8+F" +
            "UO3hPFuSf6r1j5blfrEOcxjdRU6iYQ1ZEowWT78fSt21UGi2yrQPro4xiZEfw5xs" +
            "DHRKNso8vClaBiEUqSYI5HtSU8T1AQUKae9OYh8jKU3fKFtTa9fN+lZv8jJ4z2wq" +
            "DHmjTxS9jStazyfeyp+dfFsKCQIBaoSGhSTBvyGzfQirGy9XH5JUHLEUEG4Xz0S8" +
            "GGD/E+39AgMBAAECggEAZo9NJQ3TWcy6FbcNwyRNPhtBfMoDdNKlPqML8TF1FjnS" +
            "wn0aq2w/50d4IILABPivRvg/RE3a9bsBJGpgwOM4EFRiYqciynRTnAHn9pf15rKE" +
            "JOcEjDIpI3RFXCZHWnDXm0c4h8PuJ55/cuhz7enEjkrp+O+ngs7s76lxmBoUnpyy" +
            "LtbcBTopStZi+Fu+OYD3d5vPjZe37ZwUR0xdmFeLKPV+8SWwwzrX25m2ZKKdoZpn" +
            "iVvKbR8qHmjjatq8YCf4QgUNQ6/eikbqdXmbK6GiHACwaokpRIj04KVaKfQgwV08" +
            "rMcEqCJvIAOcNOlHLjZ3dUqBXPQPdTjAlxAhxKP6YQKBgQDuKtunoI3CLA40DqgW" +
            "9zuByznqX0d9WynHlCRcKxmr2lVmL9EcWtL5ZlDw/8rAQOLE6Oy9KwEqGJxGCO0q" +
            "IcbCGxIHnEOvRVAPr0TdieLN6ucmvTwNOhVDJwKnesbcCYWEEROjH/a2o6HXHFTS" +
            "UzptIAYAvVE9Yiq82Yc0X2AauwKBgQDWW6h//E3B2WohjGrGnnLu/zhePEwfRdDu" +
            "KAzCOc+eG2x5lvCk5MUD709JcE1Uoyom0wReH+cw+SZDqUeYC8F1YzSDBI7WXwYp" +
            "SGAP/3CrOTKIRGvh2KUgWs+KPsI4CM1hHpVJIKpoAVAScUMz8UIh5Ma7tLVAjy1j" +
            "fRXRBAqapwKBgQDjD8cklAQyd6/+325PntuDzc3kbXwJvi0h0lGr8Ydi+R/r8XCK" +
            "eOikz3Aho6yYCARquKpjWymcCaSLfJ0nStZkXkydJtS8t/cxTOb50yiBKw+/NjCX" +
            "Z6W3xOxZ4crPIuDA3Ur8fU/IJk74d1FCyAhu21X9xIA8ZmfabgTh4IAOWwKBgDnV" +
            "jQbK+ee9UycuDXj9MyFMdtbmCdu+KHq5D4gvtNaBq6i6kr7KgmVnYcr8o8CcLTlA" +
            "YcNLdEMAVdSXjZt2QIH9fCOFTUyFYtIsls56KtnnmrytSTYsD5FFLw2rlRctOMxl" +
            "Rf3u9EsPX506NdmD1EX0xbHLNcPvXV2kWAiGwJ2tAoGBANpRYv10vuXYPmRZTyXW" +
            "+08rouGftFiLwHy2HQRvUyfHHguaDi7z4JOCYmyGMo07CyybUwTpoyADipF2CbHQ" +
            "IDG6xF+eltwAR/Ngef8SQLPaGOvvP6WgB3cJw3m+H4cjbB5fbbVrPCth5YhGAele" +
            "g1uaoTQ/0rzQW6sDCNCqKYfn";

    public static KeyPair generateKey(int keysize) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGen.initialize(keysize);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }

    public static KeyPair generateKey() throws Exception {
        return generateKey(1024);
    }

    public static PublicKey generatePublicKey(BigInteger modulus, BigInteger exponent) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey generatePrivateKey(BigInteger modulus, BigInteger exponent) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, exponent);
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey generatePublicKey(String modulus, String exponent) throws Exception {
        return generatePublicKey(new BigInteger(modulus), new BigInteger(exponent));
    }

    public static PrivateKey generatePrivateKey(String modulus, String exponent) throws Exception {
        return generatePrivateKey(new BigInteger(modulus), new BigInteger(exponent));
    }

    public static PublicKey generatePublicKey(byte[] key) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey generatePrivateKey(byte[] key) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        return keyFactory.generatePrivate(keySpec);
    }

    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    public static byte[] encrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public static byte[] decrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * RSA签名
     *
     * @param content       待签名数据
     * @param privateKey    商户私钥
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String input_charset) {
        try {
            PrivateKey priKey = generatePrivateKey(Base64.decodeBase64(privateKey));
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes(input_charset));
            byte[] signed = signature.sign();
            return Base64.encodeBase64String(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签
     * @param content       待签名数据
     * @param sign          签名值
     * @param public_key    公钥
     * @param input_charset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String public_key, String input_charset) {
        try {
            PublicKey pubKey = generatePublicKey(Base64.decodeBase64(public_key));
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(input_charset));
            boolean bverify = signature.verify(Base64.decodeBase64(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("mobile", "13860123fdfdsfdsfdsg456");
        params.put("password", "123gfdddddddddddddddddgfdddddg456");
        params.put("password1", "12ggggggggggggggggggggggggggggggggggggggggggggggggggggdf3456");
        params.put("password2", "123gffffffgfgfggf456");
        params.put("password3", "1234ggggggggggggggggggggggggggg56");
        String aaa = "飞机的交付的书给的非官方的广告费个梵蒂冈的非官方的鬼地方法家欧式的减1";
        //System.out.println(params.toString());
        //KeyPair keyPair = generateKey();
        //PublicKey publicKey = keyPair.getPublic();
        //PrivateKey privateKey = keyPair.getPrivate();
        //System.out.println(publicKey.toString());
        //System.out.println(privateKey.toString());
        //String pubilc_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTF6Hj5i2Fvb+kZEVHfkT2xmaVSrbmpjXR5d+ZU10c9NXa7NHh5BTlCc4DNA8tbv5M7rrrOmDjLhjtYzWcuOyzk7odePm3XuC+mCAS7Ir5YQhuXjoawzcRG6T+A7cp9CrtqjbmGsU0wFMwFYgNkXGItxs0jNMApEQ+VPk4FrjqfwIDAQAB";
        //String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANMXoePmLYW9v6RkRUd+RPbGZpVKtuamNdHl35lTXRz01drs0eHkFOUJzgM0Dy1u/kzuuus6YOMuGO1jNZy47LOTuh14+bde4L6YIBLsivlhCG5eOhrDNxEbpP4Dtyn0Ku2qNuYaxTTAUzAViA2RcYi3GzSM0wCkRD5U+TgWuOp/AgMBAAECgYEAo0H9xQJ6ZptQIlYidp6ju6/KGuCEAHK/4SxF+JFOzFxXIVBTKr95oYIpwLR8oIjdSKbCDbdCw7EdOkumZSVoejp35dmFT2Wo3CP2pu8tcbBxLShj1Z7yLc724ixtaYy3D4czlOh+y/eR4VPZS1/ox2dYIFK/hUNghPqkHMcjK1ECQQDyozjwVRURBHcfcabK06nX2bqAJrbpW+nIeXIzBStGtQiLXiJMJ19apqv+WHDX8g4XtHIRhqc9B0k2aZhaFEWrAkEA3res25R0/pK978NJkMd1XcCJ5fhUDGePM7X4WSWvttd2mYM8VjL6ggTrQfPD6XgB5m1Bryt1gDhRs/m+ASayfQJBANfa62mrV313Kn6XvhmM6fk0Ip2lU24RLc8Cc9z9yT0zCnHqAbsb/9GGm1Y/2cvS9ESUbDI5R4icdb5pfHcOt9cCQCOtBHGADGWjF+3KOISKjtBfwTcnWj9Kb3P3GDsfgFAnn4XjscyVCe+93fmIlL9XS+afZcArk/zgCKZtwPPqQPkCQQCI5Ghkc6qxiDrW5/V46kX9zkWc4Lk6eDDanodlDgcccMun3FEpBo4LNva1NS2/vs5erHaNlqc+OrlvPh5K5ptF";
        PublicKey publicKey = generatePublicKey(Base64.decodeBase64(pubilc_key));
        PrivateKey privateKey = generatePrivateKey(Base64.decodeBase64(private_key));
        //公钥
        //System.out.println("public key:" + Base64.encodeBase64String(publicKey.getEncoded()));
        //私钥
        //System.out.println("private key:" + Base64.encodeBase64String(privateKey.getEncoded()));
        //用公钥加密后的Base64字符串
        String str = Base64.encodeBase64String(encrypt(aaa.getBytes(), publicKey));
        //System.out.println(str);
        //System.out.println(new String(decrypt(Base64.decodeBase64(str), privateKey)));
//        System.out.println(Arrays.toString(encrypt("明文长度(bytes) <= 密钥长度(bytes)-11;密文长度等于密钥长度.".getBytes(), privateKey)));
//        System.out.println(new String(decrypt(encrypt("明文长度(bytes) <= 密钥长度(bytes)-11;密文长度等于密钥长度.".getBytes(), privateKey), publicKey)));
        System.out.println(sign(aaa,private_key,"UTF-8"));
        System.out.println(verify(aaa,sign(aaa,private_key,"UTF-8"),pubilc_key,"UTF-8"));
    }
}
