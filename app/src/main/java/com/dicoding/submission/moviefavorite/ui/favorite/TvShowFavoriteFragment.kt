package com.dicoding.submission.moviefavorite.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.submission.moviefavorite.R
import kotlinx.android.synthetic.main.list_item_favorite_tv_show.*

class TvShowFavoriteFragment : Fragment() {

    companion object {

        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): TvShowFavoriteFragment {
            val fragment = TvShowFavoriteFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_item_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var index = 1
        if (getArguments() != null) {
            index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
        }

        section_label.text = "${getString(R.string.tab_favorite_tv_show)} $index"
    }
}