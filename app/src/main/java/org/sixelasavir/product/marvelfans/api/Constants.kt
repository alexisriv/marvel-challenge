package org.sixelasavir.product.marvelfans.api

private const val API_QUERY_KEY = "3a783b25c80e1c44875356dd363f272d"
private const val API_QUERY_HASH = "51a3ecf2f92a23817992a2663183325e"
private const val API_QUERY_TS = "1"
private const val OTHER_QUERIES =
    "apikey=${API_QUERY_KEY}&hash=${API_QUERY_HASH}&ts=${API_QUERY_TS}"

const val API_QUERY_CHARACTERS_LIMIT: Int = 15
const val API_QUERY_EVENTS_LIMIT: Int = 25

// Characters
const val URL_CHARACTER: String = "/v1/public/characters?$OTHER_QUERIES&orderBy=name"
const val URL_COMICS_OF_CHARACTER: String =
    "/v1/public/characters/{characterId}/comics?$OTHER_QUERIES"

// Events
const val URL_EVENTS: String = "/v1/public/events?$OTHER_QUERIES&orderBy=startDate"
const val URL_COMICS_OF_EVENT: String = "/v1/public/events/{eventId}/comics?$OTHER_QUERIES"
