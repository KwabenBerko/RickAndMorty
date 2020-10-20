package com.kwabenaberko.rickandmorty.domain.model

import java.io.Serializable

/*
Using Serializable instead of Parcelable to avoid using Android related classes in the domain layer.
Another option would be to create and map domain to ui models to be used solely in the presentation layer
*/

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String
): Serializable