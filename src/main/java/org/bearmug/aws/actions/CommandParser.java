package org.bearmug.aws.actions;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private final Logger logger = Logger.getLogger(CommandParser.class);
    private final Pattern pattern = Pattern.compile("/([a-z_]+).*");
    private final Long chatId;
    private final String text;

    public CommandParser(Long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public Action getCommand() {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            try {
                InputCommand inputCommand = InputCommand.valueOf(matcher.group(1).toUpperCase());
                logger.info("Input command parsed: " + inputCommand);
                switch (inputCommand) {
                    case START:
                        return new TextAction(
                                chatId,
                                "Приступим! Кто ты, гид или посетитель???",
                                "/guide     ->  Я гид!!!",
                                "/visitor   ->  Посетитель я, пришел экскурсию посмотреть",
                                "/help      ->  Что все это значит?");
                    case HELP:
                        return new TextAction(
                                chatId,
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
                                chatId,
                                "Теперь вы гид, экскурсия запущена!",
                                "/extras            ->  Дополнить экскурсию",
                                "/info              ->  Поделиться полезной информацией",
                                "/share_location    ->  Поискать потерявшихся посетителей",
                                "/start             ->  Перестартовать"
                        );
                    case VISITOR:
                        return new TextAction(
                                chatId,
                                "Добро пожаловать на нашу замечательную экскурсию!",
                                "/info  ->  Что интересненького расскажете?",
                                "/metro ->  Я передумал, где метро?",
                                "/lost  ->  Я не вижу гида, где он?",
                                "/start ->  Перестартовать",
                                "/help  ->  Как этим пользоваться. /help"
                        );
                    case METRO:
                        return new TextAction(
                                chatId,
                                "А вот и схема вашего маршрута до метро. Совсем близко!",
                                "/lost  ->  Нет! Отведите меня к гиду!",
                                "/start ->  Перестартовать",
                                "/help  ->  Как этим пользоваться? /help"
                        );
                    case LOST:
                        return new TextAction(
                                chatId,
                                "Вот ваш маршрут до гида. Тут совсем недалеко!",
                                "/metro ->  Далековато! А где метро?",
                                "/start ->  Перезапустись!",
                                "/help  ->  Показать справку"
                        );
                    case EXTRAS:
                        return new TextAction(
                                chatId,
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
                                chatId,
                                "Инфо о гиде, экскурсионном бюро, ближайших экскурсиях",
                                "/post  ->  Кто сегодня гид?",
                                "/post  ->  Экскурсионное бюро",
                                "/post  ->  Ближайшие экскурсии",
                                "/post  ->  Попросить написать отзыв",
                                "/start ->  Перестартовать"
                        );
                    case SHARE_LOCATION:
                        return new TextAction(
                                chatId,
                                "Участникам экскурсии ушла схема маршрута до вас!",
                                "/guide  ->  Меню гида"
                        );
                    case POST:
                        return new TextAction(
                                chatId,
                                "Эта страница - заглушка будущей функциональности",
                                "/start  ->  Перестартовать",
                                "/help  ->  Справка"
                        );
                    default:
                        return new UnknownAction(chatId);
                }
            } catch (IllegalArgumentException e) {
                logger.warn("There are no specific command yet, responding with default to: " + text);
                return new UnknownAction(chatId);
            }

        } else {
            logger.info("No matches found for the input: " + text);
            return new UnknownAction(chatId);
        }
    }
}
