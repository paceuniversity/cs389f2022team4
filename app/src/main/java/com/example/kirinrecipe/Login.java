package com.example.kirinrecipe;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "Login";
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
                ,new AuthUI.IdpConfig.GoogleBuilder().build());//new AuthUI.IdpConfig.GoogleBuilder().build()

        Intent signInIntent =
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.kirin_icon2)
                        .setTheme(R.style.Theme_KirinRecipe)
                        .build();
        signInLauncher.launch(signInIntent);

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            String uid = user.getUid();
            FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
            DatabaseReference myRef = db.getReference();
            myRef.child("users").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        if(String.valueOf(task.getResult().child("Name").getValue())!="null"){
                            String Name,  Gender,  Favorite,  Dislike, ID;
                            double Weight,  Height;
                            int Age, Calories;
                            Name = String.valueOf(task.getResult().child("Name").getValue());
                            Gender = String.valueOf(task.getResult().child("Gender").getValue());
                            Favorite = String.valueOf(task.getResult().child("Favorite").getValue());
                            Dislike = String.valueOf(task.getResult().child("Dislike").getValue());
                            ID = uid;
                            Weight = Double.valueOf(task.getResult().child("Weight").getValue().toString());
                            Height = Double.valueOf(task.getResult().child("Height").getValue().toString());
                            Age = Integer.valueOf(task.getResult().child("Age").getValue().toString());
                            Calories = Integer.valueOf(task.getResult().child("Calories").getValue().toString());
                            Splash.Myuser = new User(Name,Gender,Favorite,Dislike,ID,Weight,Height,Age,Calories);
                            if(String.valueOf(task.getResult().child("LikeRecipeList").getValue())!="null"){
                                for(int i=0;i<55;i++){
                                    Splash.Myuser.LikeRecipe[i]=IfLike.valueOf(task.getResult().child("LikeRecipeList").child("LikeRecipe?"+i).getValue().toString());
                                }
                            }
                            if(String.valueOf(task.getResult().child("History").getValue())!="null"){
                                String History = String.valueOf(task.getResult().child("History").getValue());
                                int size = Integer.parseInt(History.substring(History.length()-3,History.length()-2));
                                //Log.d("History","history " + History + " " +  size);

                                for (int i = 0; i < size; i++){
                                    String count = String.valueOf(i + 1);
                                    String histories = String.valueOf(task.getResult().child("History").child(count).getValue());
                                    int [] equal = new int[3];
                                    int [] comma = new int[2];
                                    Pattern pattern1 = Pattern.compile("=");
                                    Matcher findMatcher = pattern1.matcher(histories);
                                    int j = 0;
                                    while(findMatcher.find()) {
                                        equal[j] = findMatcher.start();
                                        j++;
                                    }
                                    Pattern pattern2 = Pattern.compile(",");
                                    findMatcher = pattern2.matcher(histories);
                                    int c = 0;
                                    while(findMatcher.find()) {
                                        comma[c] = findMatcher.start();
                                        c++;
                                    }
                                    int [] history = new int[j];
                                    for (int m = 0; m < history.length; m++){
                                        if (m == 0){
                                            history[m] = Integer.parseInt(histories.substring(1,equal[m]));
                                        }else {
                                            int x = m-1;
                                            int commaIndex = comma[x] + 2;
                                            history[m] = Integer.parseInt(histories.substring(commaIndex,equal[m]));
                                        }
                                        //Log.d("History","Key " + history[m]);
                                    }
                                   // Log.d("History","Key " + histories);
                                    recipe [] TempHistory = new recipe[3];
                                    for (int n = 0; n < history.length; n++){
                                        TempHistory[n] = BaseActivity.MyrecipeList.FindRecipeByID(history[n]);
                                        //Log.d("History","Recipes " + TempHistory[n].ImageId + " " + TempHistory[n].type);
                                    }
                                    Splash.Myuser.setHistoryRecipe(TempHistory);
                                    //Log.d("History","Recipes " + Splash.Myuser.HistoryRecipe.get(0)[0].ImageId + " " + Splash.Myuser.HistoryRecipe.get(0)[0].type);
                                    //Log.d("History","Recipes " + Splash.Myuser.HistoryRecipe.get(0)[1].ImageId + " " + Splash.Myuser.HistoryRecipe.get(0)[1].type);
                                    //Log.d("History","Recipes " + Splash.Myuser.HistoryRecipe.get(0)[2].ImageId + " " + Splash.Myuser.HistoryRecipe.get(0)[2].type);
                                }


                            }

                            Intent intent=new Intent(Login.this,HomePage.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent=new Intent(Login.this,Userinfo.class);
                            startActivity(intent);
                        }
                    }
                }
            });

        }
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }
    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
    public void writeNewUser(String userId, String name, String email) {
        User user = new User();
        mDatabase.child("users").child(userId).setValue(user);
    }
}
