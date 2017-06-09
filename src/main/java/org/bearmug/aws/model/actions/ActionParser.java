package org.bearmug.aws.model.actions;

import org.apache.log4j.Logger;
import org.bearmug.aws.model.Action;
import org.bearmug.aws.model.Command;
import org.bearmug.aws.model.CommandType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionParser {

    private final Logger logger = Logger.getLogger(ActionParser.class);
    private final Pattern pattern = Pattern.compile("/([a-z_]+).*");
    private final Command command;

    public ActionParser(Command command) {
        this.command = command;
    }

    public Action getAction() {
        Matcher matcher = pattern.matcher(command.text);
        if (matcher.find()) {
            try {
                CommandType type = CommandType.valueOf(matcher.group(1).toUpperCase());
                logger.info("Input command type parsed: " + type);
                switch (type) {
                    case START:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "Приступим! Кто ты, гид или посетитель???",
                                "/guide     ->  Я гид!!!",
                                "/visitor   ->  Посетитель я, пришел экскурсию посмотреть",
                                "/help      ->  Что все это значит?");
                    case HELP:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
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
                                command.chatId,
                                command.messageId,
                                "Теперь вы гид, экскурсия запущена!",
                                "/extras            ->  Дополнить экскурсию",
                                "/info              ->  Поделиться полезной информацией",
                                "/share_location    ->  Поискать потерявшихся посетителей",
                                "/start             ->  Перестартовать"
                        );
                    case VISITOR:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "Добро пожаловать на нашу замечательную экскурсию!",
                                "/info  ->  Что интересненького расскажете?",
                                "/metro ->  Я передумал, где метро?",
                                "/lost  ->  Я не вижу гида, где он?",
                                "/start ->  Перестартовать",
                                "/help  ->  Как этим пользоваться. /help"
                        );
                    case METRO:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "А вот и схема вашего маршрута до метро. Совсем близко!",
                                "/lost  ->  Нет! Отведите меня к гиду!",
                                "/start ->  Перестартовать",
                                "/help  ->  Как этим пользоваться? /help"
                        );
                    case LOST:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "Вот ваш маршрут до гида. Тут совсем недалеко!",
                                "/metro ->  Далековато! А где метро?",
                                "/start ->  Перезапустись!",
                                "/help  ->  Показать справку"
                        );
                    case EXTRAS:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "Поделитесь дополнительными материалами с участниками экскурсии. " +
                                        "Вся информация строгопривязана к маршруту",
                                "/post  ->  Информация по точкам",
                                "/post  ->  Уникальные фото",
                                "/post  ->  Байки, связанные с объектами",
                                "/post  ->  Что-то еще на ваш выбор",
                                "/guide ->  Вернуться в меню гида"
                        );
                    case INFO:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "Инфо о гиде, экскурсионном бюро, ближайших экскурсиях",
                                "/post  ->  Кто сегодня гид?",
                                "/post  ->  Экскурсионное бюро",
                                "/post  ->  Ближайшие экскурсии",
                                "/post  ->  Попросить написать отзыв",
                                "/start ->  Перестартовать"
                        );
                    case SHARE_LOCATION:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "Участникам экскурсии ушла схема маршрута до вас!",
                                "/guide  ->  Меню гида"
                        );
                    case POST:
                        return new TextAction(
                                command.chatId,
                                command.messageId,
                                "Эта страница - заглушка будущей функциональности",
                                "/start  ->  Перестартовать",
                                "/help  ->  Справка"
                        );
                    default:
                        return new UnknownAction(command);
                }
            } catch (IllegalArgumentException e) {
                logger.warn("There are no specific command yet, responding with default to: " + command.text);
                return new UnknownAction(command);
            }

        } else {
            logger.info("No matches found for the input: " + command.text);
            return new UnknownAction(command);
        }
    }
}
