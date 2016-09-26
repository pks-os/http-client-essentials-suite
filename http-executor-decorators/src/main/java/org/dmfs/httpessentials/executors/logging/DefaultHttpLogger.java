/*
 * Copyright 2016 Marten Gajda <marten@dmfs.org>
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.httpessentials.executors.logging;

import org.dmfs.httpessentials.client.HttpRequest;
import org.dmfs.httpessentials.client.HttpResponse;
import org.dmfs.httpessentials.headers.Header;

import java.net.URI;


/**
 * @author Gabor Keszthelyi
 */
public final class DefaultHttpLogger implements HttpLogger
{
    private static final String NL = "\n";

    private final HttpLogFormatter mFormatter;
    private final LoggingFacility mLoggingFacility;


    public DefaultHttpLogger(HttpLogFormatter logFormatter, LoggingFacility loggingFacility)
    {
        mFormatter = logFormatter;
        mLoggingFacility = loggingFacility;
    }


    @Override
    public HttpRequest<?> log(URI uri, HttpRequest<?> request)
    {
        String logMessage = composeRequestMessage(uri, request);

        mLoggingFacility.log(logMessage);

        BodyLineFormatter bodyLineFormatter = mFormatter.requestBodyFormatter();
        // TODO if bodyLineFormatter != null, decorate request adding bodyLineFormatter and mLoggingFacility to the stream handler
        return request;
    }


    private String composeRequestMessage(URI uri, HttpRequest<?> request)
    {
        StringBuilder message = new StringBuilder();

        appendNewLine(mFormatter.appendRequestMsg(request.method(), uri, message), message);
        for (Header<?> header : request.headers())
        {
            appendNewLine(mFormatter.appendRequestMsg(header, message), message);
        }
        appendNewLine(mFormatter.appendRequestMsg(request.requestEntity(), message), message);

        return message.toString();
    }


    @Override
    public HttpResponse log(HttpResponse response)
    {
        String responseLog = composerResponseMessage();

        if (isError(response))
        {
            mLoggingFacility.logError(responseLog, null);
        }
        else
        {
            mLoggingFacility.log(responseLog);
        }

        BodyLineFormatter bodyLineFormatter = mFormatter.responseBodyFormatter();
        // TODO if bodyLineFormatter != null, decorate response adding bodyLineFormatter and mLoggingFacility to the stream handler
        return response;
    }


    private String composerResponseMessage()
    {
        StringBuilder message = new StringBuilder();
        // TODO similarly as in composeRequestMessage()
        return message.toString();
    }


    private boolean isError(HttpResponse response)
    {
        return response.status().isClientError() || response.status().isServerError();
    }


    private void appendNewLine(boolean appended, StringBuilder message)
    {
        if (appended)
        {
            message.append(NL);
        }
    }
}