package com.example.catsfood;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static void update(){
    }
}
