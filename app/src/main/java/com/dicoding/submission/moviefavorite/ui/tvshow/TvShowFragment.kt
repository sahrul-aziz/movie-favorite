package com.dicoding.submission.moviefavorite.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.adapter.TvShowAdapter
import com.dicoding.submission.moviefavorite.model.ErrorResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_tv_show, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvShowAdapter = TvShowAdapter()
        tvShowAdapter.notifyDataSetChanged()
        tvShowAdapter.initFavoriteHelper(this.requireContext())

        root.rv_tv_show.layoutManager = LinearLayoutManager(this.context)
        root.rv_tv_show.adapter = tvShowAdapter

        tvShowViewModel =
            ViewModelProviders.of(this, SavedStateVMFactory(this@TvShowFragment)).get(TvShowViewModel::class.java)
        tvShowViewModel.listTvShow.observe(this, Observer {
            if (it != null) {
                root.tv_show_empty.visibility = View.GONE
                tvShowAdapter.listTvShow = it
            } else {
                root.tv_show_empty.text = resources.getString(R.string.no_movie)
                root.tv_show_empty.visibility = View.VISIBLE
            }
            showLoading(false)
        })

        tvShowViewModel.errorResponse.observe(this, Observer {
            if (it != null) {
                showSnackbar(it)
                tvShowViewModel.errorResponse.value = null
            }
        })

        val tvShow = tvShowViewModel.getTvShow()
        if (tvShow != null) {
            tvShowAdapter.listTvShow = tvShow
        } else {
            showLoading(true)
            tvShowViewModel.retrieveTvShow()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tvShowViewModel.saveTvShow(tvShowViewModel.listTvShow.value)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            root.progressBarTvShow.visibility = View.VISIBLE
        } else {
            root.progressBarTvShow.visibility = View.GONE
        }
    }


    private fun showSnackbar(errorResponse: ErrorResponse){
        Snackbar.make(root, "Error [code: ${errorResponse.statusCode}]: ${errorResponse.statusMessage}", Snackbar.LENGTH_SHORT).show()
    }
}