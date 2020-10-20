package com.kwabenaberko.rickandmorty.presentation.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwabenaberko.rickandmorty.domain.CharacterRepository
import com.kwabenaberko.rickandmorty.domain.Result
import com.kwabenaberko.rickandmorty.domain.model.Character
import com.kwabenaberko.rickandmorty.presentation.util.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class CharactersViewModel @ViewModelInject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val viewState = MutableLiveData(CharactersViewState())
    private fun currentViewState() = viewState.value!!

    init {
        viewModelScope.launch {
            handleFlow(characterRepository.findAllCharacters())
        }
    }

    fun onRefresh() = viewModelScope.launch {
        handleFlow(characterRepository.findAllCharacters(forceUpdate = true))
    }

    fun onCharacterSelected(character: Character) {
        Timber.d("Character Selected: %s", character.name)
    }

    private suspend fun handleFlow(flow: Flow<Result<List<Character>, Exception>>) {
        flow.catch {
            Timber.e(it)
        }.collect { result ->
            when (result) {
                is Result.Fail -> {
                    Timber.e("Failed")
                    Timber.e(result.error)
                    viewState.postValue(
                        currentViewState().copy(isLoading = false)
                    )
                }
                is Result.Ok -> {
                    viewState.postValue(
                        currentViewState().copy(
                            isLoading = false,
                            characters = result.data
                        )
                    )
                }
            }
        }

    }
}