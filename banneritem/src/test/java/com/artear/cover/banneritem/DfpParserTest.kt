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

import com.google.gson.Gson
import com.google.gson.JsonParser
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

    private lateinit var responseDfpBlock: ResponseBody
    private lateinit var gson: Gson

    @Before
    fun setUp() {

        val loader = javaClass.classLoader!!
        val dfpBlock = TestUtils().loadJSONFromAsset(loader, "banner", "dfp_block")
        val mediaType = MediaType.parse("application/json")

        responseDfpBlock = ResponseBody.create(mediaType, dfpBlock!!)

        gson = Gson()
    }

    @Test
    fun responseDfpNameTest() {
        val jsonObject = JsonParser().parse(responseDfpBlock.string()).asJsonObject
        val jsonObjectData = jsonObject.getAsJsonObject("data")
        val blockContentDfp = gson.fromJson(jsonObjectData, BlockContentDfp::class.java)
        Assert.assertEquals("/113951150/tn/app/home/caja", blockContentDfp.name)
    }

    @Test
    fun responseDfpSizeTest() {
        val jsonObject = JsonParser().parse(responseDfpBlock.string()).asJsonObject
        val jsonObjectData = jsonObject.getAsJsonObject("data")
        val blockContentDfp = gson.fromJson(jsonObjectData, BlockContentDfp::class.java)
        Assert.assertEquals(300, blockContentDfp.size.width)
        Assert.assertEquals(250, blockContentDfp.size.height)
    }


}