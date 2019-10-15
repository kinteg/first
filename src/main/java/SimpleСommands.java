import java.util.Arrays;
import java.util.Random;

public class SimpleСommands {

    public static String getSimpleCommands(String message, String command) {

        message = message.trim();
        switch (command) {
            case "/start":
                break;
            case "/removeSpaces":
                return message.replaceAll(" ", "");


            case "/ok":
                return HardCommands.ok.get(new Random().nextInt(HardCommands.ok.size()));
            case "/coin":
                return "И выпадает: " + (new Random().nextInt(2) == 0 ? "Орёл" : "Решка");
            case "/random":
                int max = Arrays.stream(message.split(" ")).mapToInt(Integer::parseInt).max().getAsInt(),
                        min = Arrays.stream(message.split(" ")).mapToInt(Integer::parseInt).min().getAsInt();
                int rand = new Random().nextInt(max - min + 1) + min;
                return "Случайный результат между " + min + " и " + max + ": " + rand;
            case "/magicBall":
                return HardCommands.magicBall.get(new Random().nextInt(HardCommands.magicBall.size()));
            case "/help":
                return String.join("\n", HardCommands.help);


        }
        return "";
    }


}
