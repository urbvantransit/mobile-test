package com.example.urbvanmobiletest.models

class MapLocation {
    var id = ""
    var latitude = ""
    var longitude = ""

    constructor()

    constructor(id: String, latitude: String, longitude: String) {
        this.id = id
        this.latitude = latitude
        this.longitude = longitude
    }
}