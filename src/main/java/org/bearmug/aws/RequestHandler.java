package org.bearmug.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.bearmug.aws.model.Action;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Lean data codec to/from JSON. The reason for this class to exist is that Telegram do post snake_case
 * json objects, but AWS Lambda configured to read camelCase incoming data.
 */
public class RequestHandler implements RequestStreamHandler {

    private final Logger logger = Logger.getLogger(RequestHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        final String rawUpdate = readDataFrom(input);
        logger.info("request json data is " + rawUpdate);

        // Telegram API object parsed from JSON
        final Update update = objectMapper.readValue(rawUpdate, Update.class);
        logger.info("request data object: " + update);

        // Telegram API object prepare for JSON serializaion
        final BotApiMethod response = Action.forInput(update).respond();
        logger.info("response data object: " + response);

        output.write(objectMapper.writeValueAsBytes(response));
    }

    private String readDataFrom(InputStream input) throws IOException {
        return IOUtils.toString(input, StandardCharsets.UTF_8.name());
    }
}
