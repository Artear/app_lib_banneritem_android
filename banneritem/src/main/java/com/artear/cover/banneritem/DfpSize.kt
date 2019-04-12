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

import android.os.Parcel
import android.os.Parcelable

/**
 * @param url can be empty when is created from article click
 */
data class DfpSize(val width: Int, val height: Int) : Parcelable {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DfpSize> {
        override fun createFromParcel(parcel: Parcel): DfpSize {
            return DfpSize(parcel)
        }

        override fun newArray(size: Int): Array<DfpSize?> {
            return arrayOfNulls(size)
        }
    }
}