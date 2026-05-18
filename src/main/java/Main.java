import java.time.Instant;

public class Main {
    static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(Instant.now().equals(now));
    }
}
