package es.vag.vmusic.Models

import com.google.firebase.Timestamp


data class Message(var id: String? = null,
                   var userId: String = "",
                   var fecha: Timestamp? = null,
                   var content: String = "")
