package com.Preorder.preorder;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class bycrypttest{

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        /*
         * // 비밀번호 "1234"를 사용하여 BCrypt로 해싱됐을 것이라 예상되는 해시된 값 String hashedPasswordFromDB
         * = "$2a$10$oBGpKVOZQ1Up72y31zCIRuTwPFGWDLdNmES/KQ.l/jh9Pqmz/.3k2";
         *
         * // "1234"와 해시된 값이 일치하는지 확인 boolean isPasswordMatch = encoder.matches("1234",
         * hashedPasswordFromDB);
         *
         * if (isPasswordMatch) { System.out.println("비밀번호 일치!"); } else {
         * System.out.println("비밀번호 불일치!"); }
         */
        String encryptedPassword = encoder.encode("12");
        System.out.println(encryptedPassword);
    }
}
