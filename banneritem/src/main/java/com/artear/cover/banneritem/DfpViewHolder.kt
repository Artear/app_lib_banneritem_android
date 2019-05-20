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

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.artear.cover.coveritem.presentation.contract.ArtearViewHolder
import com.artear.cover.coveritem.presentation.model.ArtearSection
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import kotlinx.android.synthetic.main.cover_dfp_view_holder.view.*

class DfpViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), ArtearViewHolder<DfpData<*>> {

    override fun bind(model: DfpData<*>, artearSection: ArtearSection) {

        val doubleClickBannerLayout = itemView.dfp_banner_layout

        val backgroundColor = Color.parseColor(artearSection.backgroundColor ?: "#F7F7F7")
        itemView.dfp_linearlayout.setBackgroundColor(backgroundColor)

        doubleClickBannerLayout.removeAllViews()
        doubleClickBannerLayout.minimumHeight = pxFromDp(itemView.context, model.size.height)

        val adView = PublisherAdView(itemView.context)
        adView.setAdSizes(AdSize(model.size.width, model.size.height))
        adView.adUnitId = model.name

        val adRequest = PublisherAdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.visibility = View.GONE
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                adView.visibility = View.VISIBLE
            }
        }
        adView.isFocusable = false
        doubleClickBannerLayout.addView(adView)

    }

    private fun pxFromDp(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

}