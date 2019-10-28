package com.omargtz.mobiletest.data.remote.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseService {

    val firebaseDb: FirebaseDatabase
    get() = FirebaseDatabase.getInstance()

    val dbReference: DatabaseReference
    get() = firebaseDb.reference



}