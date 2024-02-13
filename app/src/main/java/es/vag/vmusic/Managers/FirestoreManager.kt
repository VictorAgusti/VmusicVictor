package es.vag.vmusic.Managers

import com.google.firebase.Timestamp
import com.google.firebase.firestore.*
import es.vag.vmusic.Models.Message
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreManager() {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val auth = AuthManager()
    private val userId = auth.getCurrentUser()?.email


    suspend fun addMessage(message: Message): Boolean {
        return try {
            message.userId = userId.toString()
            message.fecha = Timestamp.now()
            firestore.collection(MESSAGE_COLLECTION).add(message).await()
            true
        }catch(e: Exception){
            false
        }
    }


    suspend fun getMessagesFlow(): Flow<List<Message>> = callbackFlow{
        // Collection Reference
        var messagesCollection: CollectionReference? = null
        try {
            //Get the reference of note collection
            messagesCollection = FirebaseFirestore.getInstance()
                .collection(MESSAGE_COLLECTION)

            // Registers callback to firestore, which will be called on collection updates
            val subscription = messagesCollection?.orderBy("fecha")?.addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    // Format the results & send to the flow! Consumers will get the collection updated
                    val messages = mutableListOf<Message>()
                    snapshot.forEach{
                        messages.add(
                            Message(
                                userId = userId.toString(),
                                fecha = Timestamp.now(),
                                content = it.get(MESSAGE_CONTENT).toString()
                            )
                        )
                    }
                    trySend(messages)
                }
            }

            // The callback inside awaitClose will be executed when the flow is
            // either closed or cancelled.
            // In this case, remove the callback from Firestore
            awaitClose { subscription?.remove() }

        } catch (e: Throwable) {
            // If Firebase cannot be initialized, close the stream of data
            // flow consumers will stop collecting and the coroutine will resume
            close(e)
        }
    }

    companion object{
        const val MESSAGE_COLLECTION = "messages"
        const val MESSAGE_CONTENT = "content"
    }
}