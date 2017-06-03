package org.bearmug.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimpleHandler implements RequestHandler<String, String> {
    @Override
    public String handleRequest(String input, Context context) {
        return "<response in CAPS: >" + (input != null ? input.toUpperCase() : "<input is empty>");
    }
}
