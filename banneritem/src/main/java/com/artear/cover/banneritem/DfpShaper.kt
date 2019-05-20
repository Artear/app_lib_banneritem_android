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

import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearSection
import com.artear.cover.coveritem.repository.model.block.Block
import com.artear.domain.coroutine.DataShaper


class DfpShaper : DataShaper<Block, ArtearItem> {

    override suspend fun transform(input: Block): ArtearItem {
        val blockContentDfp = (input.data as BlockContentDfp)

        val data = DfpData(blockContentDfp.name, blockContentDfp.size, DfpStyle(input.style.weight))
        return ArtearItem(data, ArtearSection())
    }


}