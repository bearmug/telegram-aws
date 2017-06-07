package org.bearmug.aws.actions;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private final Logger logger = Logger.getLogger(CommandParser.class);
    private final Pattern pattern = Pattern.compile("/([a-z]+).*");
    private final Message message;

    public CommandParser(Message message) {
        this.message = message;
    }

    public Action getCommand() {
        Matcher matcher = pattern.matcher(message.getText());
        if (matcher.find()) {
            try {
                switch (InputCommand.valueOf(matcher.group(1).toUpperCase())) {
                    case START:
                        return new TextAction(
                                message,
                                "Приступим! Кто ты, гид или посетитель???",
                                "/guide     ->  Я гид!!!",
                                "/visitor   ->  Посетитель я, пришел экскурсию посмотреть",
                                "/help      ->  Что все это значит?");
                    case HELP:
                        return new TextAction(
                                message,
                                "Это справочка по приложению. Здесь предложены подсказки по различным вопросам " +
                                        "и доступным командам. Управляйте ботом посредством команд, видных ниже. " +
                                        "Смело жмите /start, чтобы начать сначала",
                                "/visitor ->  Понятно, я посетитель.",
                                "/guide   ->  Нет, я гид!!!!",
                                "/metro   ->  Я передумал! где здесь метро?",
                                "/start   ->  Пожалуйста, в начало!"
                        );
                    case GUIDE:
                        return new TextAction(
                                message,
                                "Теперь вы гид, экскурсия запущена!",
                                "/extras            ->  Дополнить экскурсию",
                                "/info              ->  Поделиться полезной информацией",
                                "/share_location    ->  Поискать потерявшихся посетителей",
                                "/start             ->  Перестартовать"
                        );
                    case VISITOR:
                        return new TextAction(
                                message,
                                "Добро пожаловать на нашу замечательную экскурсию!",
                                "/info  ->  Что интересненького расскажете?",
                                "/metro ->  Я передумал, где метро?",
                                "/lost  ->  Я не вижу гида, где он?",
                                "/start ->  Перестартовать",
                                "/help  ->  Как этим пользоваться. /help"
                        );
                    case METRO:
                        return new TextAction(
                                message,
                                "А вот и схема вашего маршрута до метро. Совсем близко!",
                                "/lost  ->  Нет! Отведите меня к гиду!",
                                "/start ->  Перестартовать",
                                "/help  ->  Как этим пользоваться? /help"
                        );
                    case LOST:
                        return new TextAction(
                                message,
                                "Вот ваш маршрут до гида. Тут совсем недалеко!",
                                "/metro ->  Далековато! А где метро?",
                                "/start ->  Перезапустись!",
                                "/help  ->  Показать справку"
                        );
                    case EXTRAS:
                        return new TextAction(
                                message,
                                "Поделитесь дополнительными материалами с участниками экскурсии. " +
                                        "Вся информация строгопривязана к маршруту",
                                "/post  ->  Информация по точкам",
                                "/post  ->  Уникальные фото",
                                "/post  ->  Байки, связанные с объектами",
                                "/post  ->  Что-то еще на ваш выбор",
                                "/guide  ->  Вернуться в меню гида"
                        );
                    case INFO:
                        return new TextAction(
                                message,
                                "Инфо о гиде, экскурсионном бюро, ближайших экскурсиях",
                                "/post  ->  Кто сегодня гид?",
                                "/post  ->  Экскурсионное бюро",
                                "/post  ->  Ближайшие экскурсии",
                                "/post  ->  Попросить написать отзыв",
                                "/start ->  Перестартовать"
                        );
                    case SHARE_LOCATION:
                        return new TextAction(
                                message,
                                "Участникам экскурсии ушла схема маршрута до вас!",
                                "/guide  ->  Меню гида"
                        );
                    case POST:
                        return new TextAction(
                                message,
                                "Эиа страница - заглушка будущей функциональности",
                                "/start  ->  Перестартовать",
                                "/help  ->  Справка"
                        );
                    default:
                        return new UnknownAction(message);
                }
            } catch (IllegalArgumentException e) {
                logger.warn("There are no specific command yet, responding with default to: " + message.getText());
                return new UnknownAction(message);
            }

        } else {
            return new UnknownAction(message);
        }
    }
}
