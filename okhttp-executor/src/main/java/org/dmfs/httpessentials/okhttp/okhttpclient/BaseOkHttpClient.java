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

package org.dmfs.httpessentials.okhttp.okhttpclient;

import okhttp3.OkHttpClient;
import org.dmfs.jems.single.Single;


/**
 * A {@link Single} basic {@link OkHttpClient}. No configuration if performed, other than disabling following redirects.
 *
 * @author Marten Gajda
 */
public final class BaseOkHttpClient implements Single<OkHttpClient>
{
    @Override
    public OkHttpClient value()
    {
        // note we disable following redirects explicitly
        return new OkHttpClient.Builder().followRedirects(false).followSslRedirects(false).build();
    }
}
