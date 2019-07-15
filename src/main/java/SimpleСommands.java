import java.util.Random;

public class SimpleСommands {

    public static String getSimpleCommands(String message, String command) {


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
                return "Ваша цифра: " + new Random().nextInt(Integer.parseInt(message.split(" ")[0], Integer.parseInt(message.split(" ")[1])));
            case "/magicBall":
                return AlinaLoh.magicBall.get(new Random().nextInt(AlinaLoh.magicBall.size()));
            case "/fuck":
                return "Сам иди в жопу";

        }
        return "";
    }



}
