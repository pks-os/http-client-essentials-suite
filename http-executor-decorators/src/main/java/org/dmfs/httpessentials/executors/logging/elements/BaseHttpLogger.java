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

package org.dmfs.httpessentials.executors.logging.elements;

import org.dmfs.httpessentials.client.HttpRequest;
import org.dmfs.httpessentials.client.HttpResponse;
import org.dmfs.httpessentials.executors.logging.httplogger.HttpLogger;
import org.dmfs.httpessentials.executors.logging.io.LogSink;

import java.net.URI;


/**
 * @author Gabor Keszthelyi
 */
public final class BaseHttpLogger implements HttpLogger
{
    private final HttpLogComposer mHttpLogComposer;


    public BaseHttpLogger(HttpLogComposer httpLogComposer)
    {
        mHttpLogComposer = httpLogComposer;
    }


    @Override
    public <T> HttpRequest<T> log(URI uri, HttpRequest<T> request)
    {
        StringBuilder logMessage = new StringBuilder();
        for (RequestLogElement requestLogElement : mHttpLogComposer.requestLogElements())
        {
            requestLogElement.log(request, uri, logMessage);
        }
        // logFacility.log(...)
        LogSink logSink = mHttpLogComposer.entityBodyLogSinkProvider().provide(uri, request);
        // if logSink not null, decorate request using LogSink
        return null;
    }


    @Override
    public HttpResponse log(HttpResponse response)
    {
        return null;
    }
}
