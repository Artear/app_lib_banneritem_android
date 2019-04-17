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
package com.artear.cover.banneritem

import com.artear.cover.coveritem.repository.deserializer.SizeDeserializer
import com.artear.tools.media.Size
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class DfpParserTest {

    companion object {
        private const val FOLDER = "banner"
        private const val JSON_TYPE = "application/json"

        private const val DATA = "data"

        private const val DFP_BLOCK = "dfp_block"
        private const val DFP_BLOCK_MISS_SIZE = "dfp_block_miss_size"
        private const val DFP_BLOCK_WRONG_SIZE = "dfp_block_wrong_size"
        private const val DFP_BLOCK_WRONG_DATA_SIZE = "dfp_block_wrong_data_size"
    }

    private lateinit var gson: Gson

    @Before
    fun setUp() {
        gson = GsonBuilder()
                .registerTypeAdapter(Size::class.java, SizeDeserializer())
                .create()
    }

    private fun loadLocalJson(jsonName: String): ResponseBody {
        val loader = javaClass.classLoader!!
        val jsonString = TestUtils().loadJSONFromAsset(loader, FOLDER, jsonName)
        val mediaType = MediaType.parse(JSON_TYPE)
        return ResponseBody.create(mediaType, jsonString!!)
    }

    private fun simpleCall(responseBody: ResponseBody): BlockContentDfp {
        val jsonObject = JsonParser().parse(responseBody.string()).asJsonObject
        val jsonObjectData = jsonObject.getAsJsonObject(DATA)
        return gson.fromJson(jsonObjectData, BlockContentDfp::class.java)
    }

    @Test
    fun responseDfpNameTest() {
        val responseDfpBlock = loadLocalJson(DFP_BLOCK)
        val blockContentDfp = simpleCall(responseDfpBlock)
        Assert.assertEquals("/113951150/tn/app/home/caja", blockContentDfp.name)
    }

    @Test
    fun responseDfpSizeTest() {
        val responseDfpBlock = loadLocalJson(DFP_BLOCK)
        val blockContentDfp = simpleCall(responseDfpBlock)
        Assert.assertEquals(300, blockContentDfp.size.width)
        Assert.assertEquals(250, blockContentDfp.size.height)
    }

    @Test(expected = IllegalArgumentException::class)
    fun responseDfpMissSizeTest() {
        val responseDfpBlockMissSize = loadLocalJson(DFP_BLOCK_MISS_SIZE)
        simpleCall(responseDfpBlockMissSize)
    }

    @Test(expected = JsonSyntaxException::class)
    fun responseDfpEmptySizeTest() {
        val responseDfpBlockWrongSize = loadLocalJson(DFP_BLOCK_WRONG_SIZE)
        simpleCall(responseDfpBlockWrongSize)
    }

    @Test(expected = NumberFormatException::class)
    fun responseDfpWrongDataSizeTest() {
        val responseDfpBlockWrongDataSize = loadLocalJson(DFP_BLOCK_WRONG_DATA_SIZE)
        simpleCall(responseDfpBlockWrongDataSize)
    }


}