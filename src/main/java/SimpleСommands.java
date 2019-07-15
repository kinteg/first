import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class SimpleСommands {

    public static String getSimpleCommands(String message, String command) {

        message = message.trim();
        switch (command) {
            case "/start":
                break;
            case "/removeSpaces":
                return message.replaceAll(" ", "");
            case "/alina":
                return AlinaLoh.list.get(new Random().nextInt(AlinaLoh.list.size()));
            case "/Photo":
                return AlinaLoh.photoAlina.get(new Random().nextInt(AlinaLoh.photoAlina.size()));
            case "/ok":
                return AlinaLoh.ok.get(new Random().nextInt(AlinaLoh.ok.size()));
            case "/coin":
                return "И выпадает: " + (new Random().nextInt(2) == 0 ? "Орёл" : "Решка");
            case "/random":
                int max = Arrays.stream(message.split(" ")).mapToInt(Integer::parseInt).max().getAsInt(),
                        min = Arrays.stream(message.split(" ")).mapToInt(Integer::parseInt).min().getAsInt();
                int rand = new Random().nextInt(max - min + 1) + min;
                return "Случайный результат между " + min + " и " + max + ": " + rand;
            case "/magicBall":
                return AlinaLoh.magicBall.get(new Random().nextInt(AlinaLoh.magicBall.size()));
            case "/help":
                return String.join("\n", AlinaLoh.help);
            case "/fuck":
                return "Сам иди в жопу";
            case "/hi":
                return "И тебе привет!";

        }
        return "";
    }


}
