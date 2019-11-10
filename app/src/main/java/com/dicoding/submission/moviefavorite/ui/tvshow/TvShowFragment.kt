package com.dicoding.submission.moviefavorite.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dicoding.submission.moviefavorite.R

class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowViewModel =
            ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tv_show, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        tvShowViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}