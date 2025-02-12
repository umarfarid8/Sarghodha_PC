package com.UZdevelopers.sarghodhapc.Repositories

import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class LoadRepository {
    val orderCollection = FirebaseFirestore.getInstance().collection("orders")

    suspend fun saveLoad(load: Load): Result<Boolean> {
        try {
            val document = orderCollection.document()
            load.id = document.id
            document.set(load).await()
            return Result.success(true)

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun updateRequest(load: Load): Result<Boolean> {
        try {
            if (load.id.isNullOrEmpty()) {
                return Result.failure(IllegalArgumentException("Order ID (rid) is null or empty"))
            }

            // Update the document with the provided rid
            orderCollection.document(load.id!!).set(load).await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    fun getLoads() =
        orderCollection.snapshots().map { it.toObjects(Load::class.java) }

    fun getOrdersOfUser(phoneNumber: String) =
        orderCollection.whereEqualTo("phoneNumber",phoneNumber).snapshots().map { it.toObjects(Load::class.java)
        }


    fun getOrdersOfUserPending(phoneNumber: String) =
        orderCollection.whereEqualTo("phoneNumber",phoneNumber).whereEqualTo("status", "Pending").snapshots().map { it.toObjects(
            Load::class.java) }
    fun getOrdersOfUserCompleted(phoneNumber: String) =
        orderCollection.whereEqualTo("phoneNumber",phoneNumber).whereEqualTo("status", "Completed").snapshots().map { it.toObjects(
            Load::class.java) }
    suspend fun getMostRecentOrderOfUser(phoneNumber: String): Result<Load?>{
        try {
            val result = orderCollection
                .whereEqualTo("phoneNumber", phoneNumber)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .await()
                .toObjects(Load::class.java)
                .firstOrNull()

            if(result == null){
                return  Result.success(null)
            } else{
                return Result.success(result)
            }

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }


    fun getAllCompleted() =
        orderCollection.whereEqualTo("status", "Completed").snapshots().map { it.toObjects(Load::class.java) }
    fun getAllPending() =
        orderCollection.whereEqualTo("status", "Pending").snapshots().map { it.toObjects(Load::class.java) }
}