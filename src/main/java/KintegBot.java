import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KintegBot extends TelegramLongPollingBot {


    SimpleСommands simpleСommands;

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new KintegBot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            String strMessage = message.getText();
            System.out.println(message.getFrom().getFirstName() + ": " + strMessage);
            strMessage = SimpleСommands.getSimpleCommands(getMessage(strMessage), getCommand(strMessage, getMessage(strMessage)));
            if (!strMessage.equals("")) {
                sendMsg(message, strMessage);
            }
//            switch (message.getText()) {
//                case "/removeSpaces":
//                    deleteCommand()
////                    message = update.getMessage();
////                    sendMsg(message, "Введите текст: ");
////                    sendMsg(message, message.getText() + message.getFrom().getFirstName());
//
//                    break;
////                default:
////                    int length = message.getText().length();
////                    sendMsg(message,
////                            (length <= 5 ? "Маленький " :
////                             length <= 10 ? "Не очень большой " :
////                             length <= 15 ? "Средний " :
////                             length <= 20 ? "Относительно большой " :
////                             length <= 30 ? "Большой " :
////                             length <= 50 ? "Огромный " : "Как у коня ") + "хуй");
//            }
        }
    }

    private String getCommand(String command, String message) {
        return command.split(" ")[0];
    }

    private String getMessage(String message) {
        return Arrays.stream(message.split(" ")).skip(1).collect(Collectors.joining());
    }

    //Отправка сообщения
    private void sendMsg(Message message, String text) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setReplyToMessageId(message.getMessageId());
            sendMessage.setText(text);
            try {
                setButtons(sendMessage);
                execute(sendMessage);
            } catch (TelegramApiRequestException e) {
                sendMessage.setText("Вы ввели слишком много сообщений! Подождите пару минут");
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

    }

    public void setButtons(SendMessage sendMessage) {
        //Создаём клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //Разметка клавиатуры
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        //Вывод клавиатуры всем пользователям
        replyKeyboardMarkup.setSelective(true);
        //Подгонка под количество кнопок
        replyKeyboardMarkup.setResizeKeyboard(true);
        //Не скрывать клавиатуру
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        //Лист кнопок
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        //Первая строка
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        //Добавляем кнопки
        keyboardFirstRow.add(new KeyboardButton("/coin"));
        keyboardFirstRow.add(new KeyboardButton("/ok"));
        keyboardFirstRow.add(new KeyboardButton("/magicBall"));

        //Добавляем кнопки в массив
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }


    public String getBotUsername() {
        return "KintegBot";
    }

    public String getBotToken() {
        return "666716072:AAF8mrVIKYoRHEYWLsyjRqV7eT7jupa7zA0";
    }
}
