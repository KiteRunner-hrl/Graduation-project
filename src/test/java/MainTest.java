
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Random;

import javax.servlet.http.Cookie;

public class MainTest {
  @Test
  public void test() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("123456"));
    Cookie cookie = new Cookie("username", "root");
    System.out.println(cookie);
    String randomCode = generateRandomCode(8);
    System.out.println("随机生成的8位码是：" + randomCode);
  }
  private static String generateRandomCode(int length) {
    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(chars.length());
      sb.append(chars.charAt(index));
    }
    return sb.toString();
  }
}
