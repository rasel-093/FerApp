package com.vicksam.ferapp.db.user

//fun getUserById(userId: String, onComplete:(User?)->Unit ) {
//    val firestore = FirebaseFirestore.getInstance().collection("retailers")
//    val documentReference = firestore.document(retailerId)
//    documentReference.get()
//        .addOnCompleteListener { task: Task<DocumentSnapshot> ->
//            if (task.isSuccessful) {
//                val documentSnapshot = task.result
//                val retailer = if (documentSnapshot != null && documentSnapshot.exists()) {
//                    documentSnapshot.toObject(Retailer::class.java)
//                } else {
//                    null
//                }
//                onComplete(retailer)
//            } else {
//                // Handle errors
//            }
//        }
//}