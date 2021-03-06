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

package org.dmfs.httpessentials.executors.authorizing.authschemes;

import org.dmfs.httpessentials.executors.authorizing.AuthScheme;
import org.dmfs.httpessentials.executors.authorizing.AuthStrategy;
import org.dmfs.httpessentials.executors.authorizing.Challenge;
import org.dmfs.httpessentials.executors.authorizing.CredentialsStore;
import org.dmfs.httpessentials.executors.authorizing.Tokens;
import org.dmfs.httpessentials.executors.authorizing.UserCredentials;
import org.dmfs.httpessentials.executors.authorizing.authscopes.UriScope;
import org.dmfs.httpessentials.executors.authorizing.authstates.AuthenticatedBasicAuthState;
import org.dmfs.httpessentials.executors.authorizing.utils.ParametrizedChallenges;
import org.dmfs.iterables.elementary.PresentValues;
import org.dmfs.jems.iterable.decorators.Mapped;
import org.dmfs.jems.optional.composite.Zipped;
import org.dmfs.jems.pair.Pair;
import org.dmfs.jems.pair.elementary.ValuePair;

import java.net.URI;


/**
 * The Basic authentication scheme.
 *
 * @author Marten Gajda
 */
public final class Basic implements AuthScheme<UserCredentials>
{
    @Override
    public Iterable<Pair<CharSequence, AuthStrategy>> authStrategies(Iterable<Challenge> challenges, final CredentialsStore<UserCredentials> credentialsStore, final URI uri)
    {
        return new PresentValues<>(

                new Mapped<>(challenge -> new Zipped<>(
                        credentialsStore.credentials(new UriScope(uri)),
                        challenge.parameter(Tokens.REALM),
                        (userCredentials, charSequence) -> new ValuePair<>(
                                charSequence,
                                (method, uri1, fallback) -> new AuthenticatedBasicAuthState(userCredentials, fallback))),
                        new ParametrizedChallenges(Tokens.BASIC, challenges)));
    }
}
