package com.df.ray.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtUtil
 *
 * @author ray
 * @date 2021/12/24
 */
public class JwtUtil {

    /**
     * token 过期时间, 单位: 秒. 这个值表示 30 天
     */
//    private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60;
    private static final long TOKEN_EXPIRED_TIME = 1;

    public static final String jwtId = "tokenId";

    /**
     * jwt 加密解密密钥(可自行填写)
     */
    private static final String JWT_SECRET = "1234567890";

    /**
     * 创建JWT
     */
    public static String createJWT(Map<String, Object> claims, Long time) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        Date now = new Date(System.currentTimeMillis());

        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(jwtId)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .signWith(signatureAlgorithm, secretKey);//设置签名使用的签名算法和签名使用的秘钥
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();
    }


    /**
     * 验证jwt
     */
    public static Claims verifyJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        return claims;

    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = JWT_SECRET;
        byte[] encodedKey = Base64.getDecoder().decode(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 根据userId和openid生成token
     */
    public static String generateToken(String openId, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("openId", openId);
        map.put("sub", openId);
        return createJWT(map, TOKEN_EXPIRED_TIME);
    }

    public static void main(String[] args) {
        // 生成token
        String s = generateToken("111", "1233");
        System.out.println(s);

        // 验证
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTEiLCJvcGVuSWQiOiIxMTEiLCJleHAiOjE2NDAyNzg0NDYsInVzZXJJZCI6IjEyMzMiLCJpYXQiOjE2NDAyNzg0NDYsImp0aSI6InRva2VuSWQifQ.mkTtOiu4cTVyq6nwGeXk41etXEuQWD2xgUJdjZ0fCyA";
        // 假如token过期的话, 解析的就为null值
        Claims claims = verifyJwt(token);
        String subject = claims.getSubject();
        String userId = (String)claims.get("userId");
        String openId = (String)claims.get("openId");
        String sub = (String)claims.get("sub");
        System.out.println("subject:" + subject);
        System.out.println("userId:" + userId);
        System.out.println("openId:" + openId);
        System.out.println("sub:" + sub);
    }
}
