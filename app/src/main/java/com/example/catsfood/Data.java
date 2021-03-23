package com.example.catsfood;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    public static FirebaseAuth auth;
    public static List<Cat> mycat;
    public static String myid;  //DYsembQnzFUWucfwA5m20tpfK5q1
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void init(){
        Log.e("ID", myid);
        if (mycat == null)
        {
            mycat = new ArrayList<>();
            db.collection("Cats")
                    .whereEqualTo("User", myid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ArrayList<HashMap<String, Object>> temp = (ArrayList<HashMap<String, Object>>) document.get("Cats");
                                    int idd = 0;
                                    for(HashMap<String, Object> cat : temp)
                                    {
                                        mycat.add(new Cat(idd, (String) cat.get("name"), (long) cat.get("age"), (long) cat.get("weight"), (boolean) cat.get("is_male")));
                                        ++idd;
                                    }
                                }
                            } else {
                                Log.w("TAG", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }

    }

    public static void exit(){
        mycat = null;
        myid = null;
    }

    public static void update(Cat kost){
        if (mycat.size() > 0)
        {
            db.collection("Cats")
                    .whereEqualTo("User", myid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if (task.isSuccessful())
                            {
                                for (QueryDocumentSnapshot document : task.getResult())
                                {
                                    db.collection("Cats").document(document.getId())
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("TAG", "DocumentSnapshot successfully deleted!");
                                                    Map<String, Object> user = new HashMap<>();
                                                    List<Map<String, Object>> cat = new ArrayList<>();
                                                    for (Cat cat1 : Data.mycat)
                                                    {
                                                        Map<String, Object> temp = new HashMap<>();
                                                        temp.put("name", cat1.name);
                                                        temp.put("age", cat1.age);
                                                        temp.put("weight", cat1.weight);
                                                        temp.put("is_male", cat1.is_male);
                                                        cat.add(temp);
                                                    }
                                                    user.put("User", myid);
                                                    user.put("Cats", cat);

                                                    db.collection("Cats")
                                                            .add(user)
                                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                                @Override
                                                                public void onSuccess(DocumentReference documentReference)
                                                                {
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Log.w("TAG", "Error adding document", e);
                                                                }
                                                            });
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("TAG", "Error deleting document", e);
                                                }
                                            });
                                }
                            }
                            else
                            {
                                Log.e("TAGload", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        else
        {
            Map<String, Object> user = new HashMap<>();
            List<Map<String, Object>> cat = new ArrayList<>();
            Map<String, Object> temp = new HashMap<>();
            temp.put("name", kost.name);
            temp.put("age", kost.age);
            temp.put("weight", kost.weight);
            temp.put("is_male", kost.is_male);
            cat.add(temp);
            user.put("User", myid);
            user.put("Cats", cat);

            db.collection("Cats")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference)
                        {
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                        }
                    });
        }
    }
}
