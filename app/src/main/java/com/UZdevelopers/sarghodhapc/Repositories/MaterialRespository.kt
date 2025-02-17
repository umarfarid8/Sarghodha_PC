package com.UZdevelopers.sarghodhapc.Repositories


import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Material
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class MaterialRespository {
   private  val materialCollection = FirebaseFirestore.getInstance().collection("materials")

    suspend fun saveMaterial(material: Material): Result<Boolean> {
        try {
            val document = materialCollection.document()
            material.id = document.id
            document.set(material).await()
            return Result.success(true)

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
    suspend fun updateRequest(material: Material): Result<Boolean> {
        try {
            if (material.id.isNullOrEmpty()) {
                return Result.failure(IllegalArgumentException("Material ID (rid) is null or empty"))
            }
            // Update the document with the provided rid
            materialCollection.document(material.id!!).set(material).await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    fun getMaterials() =
        materialCollection.snapshots().map { it.toObjects(Material::class.java)
    }

    fun getMaterail(matName: String) =
        materialCollection.whereEqualTo("material",matName).snapshots().map {
            it.toObjects(Material::class.java)
        }
}