/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.httpessentials.executors.authorizing.challenges;

import org.dmfs.httpessentials.client.HttpResponse;
import org.dmfs.httpessentials.executors.authorizing.Challenge;

import java.util.Iterator;


/**
 * The {@link Iterable} of the {@link Challenge}s of a given {@link HttpResponse}.
 *
 * @author Marten Gajda
 */
public final class ResponseChallenges implements Iterable<Challenge>
{
    private final HttpResponse mResponse;


    public ResponseChallenges(HttpResponse response)
    {
        mResponse = response;
    }


    @Override
    public Iterator<Challenge> iterator()
    {
        return new HeaderChallenges(mResponse.headers()).iterator();
    }
}
