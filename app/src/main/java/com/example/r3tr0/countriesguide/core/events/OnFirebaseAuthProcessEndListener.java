package com.example.r3tr0.countriesguide.core.events;

import com.google.firebase.auth.FirebaseUser;

/**
 * The event of finishing a fire base process (Login and register).
 * Use the methods inside to react on the success or the failure of the process.
 */
public interface OnFirebaseAuthProcessEndListener {
    /**
     * A method used when the Firebase's process is succeeded.
     * @param user The registered or the logged in user.
     */
    void onSuccess(FirebaseUser user);

    /**
     * A method used when the Firebase's process is failed.
     */
    void onFail();
}
