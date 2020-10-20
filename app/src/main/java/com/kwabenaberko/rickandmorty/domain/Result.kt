package com.kwabenaberko.rickandmorty.domain

sealed class Result<out T, out E> {

    data class Ok<T>(val data: T): Result<T, Nothing>()

    data class Fail<E>(val error: E): Result<Nothing, E>()

}