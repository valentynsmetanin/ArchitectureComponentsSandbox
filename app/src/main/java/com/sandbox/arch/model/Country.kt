package com.sandbox.arch.model

data class Country(var name: String? = null,
                   var capital: String? = null,
                   var region: String? = null,
                   var population: Int? = null,
                   val latlng: List<Double>? = null,
                   val area: Double? = null,
                   var flag: String? = null)