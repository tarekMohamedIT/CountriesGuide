package com.example.r3tr0.countriesguide.interactors.managers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.r3tr0.countriesguide.core.events.OnFirebaseAuthProcessEndListener;
import com.example.r3tr0.countriesguide.core.interfaces.ISignInManager;
import com.example.r3tr0.countriesguide.core.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The implementation of the {@link ISignInManager} interface.
 * This class is responsible for the Firebase operations.
 */
public class SignInManager implements ISignInManager {
    static String TAG = "test firebase"; //Tag for debugging.

    private FirebaseAuth firebaseAuth; // The authentication object of the firebase.
    private Context context;

    private OnFirebaseAuthProcessEndListener onFirebaseAuthLoginEndListener; //The login operation event listener.
    private OnFirebaseAuthProcessEndListener onFirebaseAuthSignupEndListener; //The sign up operation event listener.

    public SignInManager(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void setOnFirebaseAuthLoginEndListener(OnFirebaseAuthProcessEndListener onFirebaseAuthLoginEndListener) {
        this.onFirebaseAuthLoginEndListener = onFirebaseAuthLoginEndListener;
    }

    public void setOnFirebaseAuthSignupEndListener(OnFirebaseAuthProcessEndListener onFirebaseAuthSignupEndListener) {
        this.onFirebaseAuthSignupEndListener = onFirebaseAuthSignupEndListener;
    }

    /**
     * The Sign in process is done using the
     * {@link FirebaseAuth#signInWithEmailAndPassword(String, String)}
     *
     * then in the OnCompleteListener, The event listeners are added.
     *
     * @param user The user to be logging in.
     * @return The currently logged in FirebaseUser or null if not successful.
     */
    @Override
    public FirebaseUser signIn(User user) {

        // [START sign_in_with_email]
        firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {// Sign in success, update UI with the signed-in user's information

                            Log.d(TAG, "signInWithEmail:success");
                            if (onFirebaseAuthLoginEndListener != null)
                                onFirebaseAuthLoginEndListener.onSuccess(firebaseAuth.getCurrentUser());
                        }

                        else {// If sign in fails, display a message to the user.

                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            if (onFirebaseAuthLoginEndListener != null)
                                onFirebaseAuthLoginEndListener.onFail();
                        }
                    }
                });
        return firebaseAuth.getCurrentUser();
    }

    /**
     * The Sign in process is done using the
     * {@link FirebaseAuth#createUserWithEmailAndPassword(String, String)}
     *
     * then in the OnCompleteListener, The event listeners are added.
     *
     * @param user The user to be signed up.
     * @return The currently logged in FirebaseUser or null if not successful.
     */
    @Override
    public FirebaseUser signUp(User user) {

        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            if (onFirebaseAuthSignupEndListener != null)
                                onFirebaseAuthSignupEndListener.onSuccess(firebaseAuth.getCurrentUser());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            if (onFirebaseAuthSignupEndListener != null)
                                onFirebaseAuthSignupEndListener.onFail();
                        }
                    }
                });

        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void signOut(){
        firebaseAuth.signOut();
    }
}
