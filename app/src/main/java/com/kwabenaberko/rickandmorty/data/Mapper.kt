package com.kwabenaberko.rickandmorty.data

interface Mapper<Data, Domain> {

    fun mapFromData(data: Data): Domain

    fun mapToData(domain: Domain): Data

}