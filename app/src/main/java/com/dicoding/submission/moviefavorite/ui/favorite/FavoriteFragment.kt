package com.dicoding.submission.moviefavorite.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.adapter.SectionsPagerAdapter
import com.dicoding.submission.moviefavorite.db.FavoriteHelper
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import androidx.appcompat.app.AppCompatActivity

class FavoriteFragment : Fragment() {

    private lateinit var favoriteHelper: FavoriteHelper
    private lateinit var root: View
    private lateinit var mContext: FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_favorite, container, false)

        val sectionsPagerAdapter = SectionsPagerAdapter(root.context, mContext.supportFragmentManager)
        root.view_pager.adapter = sectionsPagerAdapter
        root.favorite_tab.setupWithViewPager(root.view_pager)

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as FragmentActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.elevation = 0f
        favoriteHelper = FavoriteHelper(root.context).getInstance(root.context)
        favoriteHelper.open()
    }
}