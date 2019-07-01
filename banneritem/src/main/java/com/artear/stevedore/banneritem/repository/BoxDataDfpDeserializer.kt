/*
 * Copyright 2019 Artear S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.artear.stevedore.banneritem.repository

import com.artear.stevedore.stevedoreitems.repository.getModelObject
import com.artear.tools.media.Size
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BoxDataDfpDeserializer : JsonDeserializer<BoxDataDfp> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): BoxDataDfp {

        val id = json.asJsonObject.get("id").asString
        val name = json.asJsonObject.get("name").asString
        val dfpSize = json.getModelObject("size", context, Size::class.java)

        return BoxDataDfp(id, name, dfpSize)
    }
}