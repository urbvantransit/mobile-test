package com.example.urbvanmobiletest.utils

import android.content.Context
import android.widget.Toast

object AlertUtils {

    fun makeToast(context: Context, message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}