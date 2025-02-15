/*
 *  Copyright 2020 Xiaomi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.xiaomi.youpin.docean.mvc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.xiaomi.youpin.docean.mvc.httpmethod.HttpMethodUtils;

/**
 * @author goodjava@qq.com
 */
public abstract class Post {

    private static Gson gson = new Gson();

    public static JsonArray getParams(HttpRequestMethod method, byte[] data) {
        JsonElement arguments = (null == data || data.length == 0) ? null : gson.fromJson(new String(data), JsonElement.class);
        JsonArray array = new JsonArray();
        HttpMethodUtils.addMvcContext(method, array);

        if (null == arguments) {
            return array;
        }

        if (arguments.isJsonObject()) {
            array.add(arguments);
        }

        if (arguments.isJsonArray()) {
            arguments.getAsJsonArray().forEach(it -> array.add(it));
        }

        if (arguments.isJsonPrimitive()) {
            array.add(arguments.getAsJsonPrimitive());
        }

        return array;
    }

}
