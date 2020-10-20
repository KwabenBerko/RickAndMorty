package com.kwabenaberko.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kwabenaberko.rickandmorty.data.db.RickAndMortyDatabase
import com.kwabenaberko.rickandmorty.data.db.model.DbCharacter
import com.kwabenaberko.rickandmorty.domain.CharacterRepository
import com.kwabenaberko.rickandmorty.domain.Result
import com.kwabenaberko.rickandmorty.domain.model.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import java.lang.Exception
import java.lang.Thread.sleep
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: CharacterRepository

    @Inject
    lateinit var database: RickAndMortyDatabase

    private val characterAdapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charactersRecyclerView.apply {
            adapter = characterAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            GlobalScope.launch(Dispatchers.Main) {
                val flow = repository.findAllCharacters(forceUpdate = true)
                handleFlow(flow)
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val flow = repository.findAllCharacters()
            handleFlow(flow)
        }

    }

    private suspend fun handleFlow(flow: Flow<Result<List<Character>, Exception>>) {

        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }

        flow.catch {
            Timber.e(it)
        }.collect { result ->
            when (result) {
                is Result.Fail -> {
                    Timber.e("Failed")
                }
                is Result.Ok -> {
                    characterAdapter.refill(result.data)
                }
            }
        }

    }
}