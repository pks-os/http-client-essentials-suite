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

import org.dmfs.httpessentials.executors.logging.alternatives.LogLevel;


/**
 * @author Gabor Keszthelyi
 */
public final class LogcatLoggingFacility implements LoggingFacility
{
    private final LogLevel mLogLevel; // would be the logcat's one
    private final String mTag;


    public LogcatLoggingFacility(LogLevel logLevel, String tag)
    {
        mLogLevel = logLevel;
        mTag = tag;
    }


    @Override
    public void log(String message)
    {
        // logcat log with mTag and mLogLevel
    }


    @Override
    public void logError(String message, Throwable throwable)
    {
        // logcat log with mTag and ERROR level
    }
}
