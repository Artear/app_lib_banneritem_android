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

import java.nio.charset.Charset

class TestUtils {

    /**
     * @param loader loader needed to get the file
     * @param folder Folder where the files are stored
     * @param file   filename for this test
     * @return file content as string
     */
    fun loadJSONFromAsset(loader: ClassLoader, folder: String, file: String): String? {
        var json: String? = null

        val builder = StringBuilder()
        builder.append(folder)
        builder.append("/")
        builder.append(file)
        builder.append(".json")
        try {
            val `is` = loader.getResourceAsStream(builder.toString())
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return json
    }
}
