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
package com.artear.stevedore.banneritem.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artear.stevedore.banneritem.R
import com.artear.stevedore.stevedoreitems.presentation.contract.ArtearViewHolder
import com.artear.stevedore.stevedoreitems.presentation.contract.SpanItemAdapter
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItemDecoration
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxStyle


class DfpItemAdapter : SpanItemAdapter<DfpData<out BoxStyle>> {


    override fun isForViewType(item: ArtearItem): Boolean {
        return item.model is DfpData
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.dfp_view_holder, parent, false)
        return DfpViewHolder(view)
    }

    override fun onBindViewHolderBase(holder: ArtearViewHolder<DfpData<out BoxStyle>>,
                                      model: DfpData<out BoxStyle>,
                                      artearItemDecoration: ArtearItemDecoration) {
        holder.bind(model, artearItemDecoration)
    }

    override fun onSpanSizeRequired(model: DfpData<out BoxStyle>): Int {
        return spanSize(model.style.weight)
    }

    private fun spanSize(weight: Float?): Int {
        var result = 1
        weight?.let {
            result = (it * 2).toInt()
        }
        return result
    }


}
