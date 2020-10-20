package com.kwabenaberko.rickandmorty.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kwabenaberko.rickandmorty.R
import com.kwabenaberko.rickandmorty.domain.CharacterRepository
import com.kwabenaberko.rickandmorty.domain.Result
import com.kwabenaberko.rickandmorty.domain.model.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_characters.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject


@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var characterAdapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_characters, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterAdapter = CharactersAdapter(viewModel)

        setupViewObservers()

        rootView.charactersRecyclerView.apply {
            adapter = characterAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //Load More Feature?
                    }
                }
            })
        }

        rootView.swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }

    }


    private fun setupViewObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.apply {
                swipeRefreshLayout.isRefreshing = isLoading
                characterAdapter.refill(characters)
            }
        })
    }


}