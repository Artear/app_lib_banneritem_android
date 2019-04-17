package com.artear.cover.banneritem

import com.artear.cover.coveritem.repository.getModelObject
import com.artear.tools.media.Size
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BlockContentDfpDeserializer : JsonDeserializer<BlockContentDfp> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): BlockContentDfp {

        val id = json.asJsonObject.get("id").asString
        val name = json.asJsonObject.get("name").asString
        val dfpSize = json.getModelObject("size", context, Size::class.java)

        return BlockContentDfp(id, name, dfpSize)
    }
}