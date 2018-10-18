package com.example.r3tr0.countriesguide.core.interfaces;

import com.example.r3tr0.countriesguide.core.models.User;
import com.google.firebase.auth.FirebaseUser;

/**
 * An interface for the {@link com.example.r3tr0.countriesguide.interactors.managers.SignInManager}
 * which holds all the methods used in the implemented class.
 */
public interface ISignInManager {

    /**
     * The signing in method for the firebase using a {@link User} class.
     * @param user The user to be logging in.
     * @return A {@link FirebaseUser} object if the user is logged in or null if not.
     */
    FirebaseUser signIn(User user);

    /**
     * The signing up method for the firebase using a {@link User} class.
     * @param user The user to be registered.
     * @return A {@link FirebaseUser} object if the user is registered or null if not.
     */
    FirebaseUser signUp(User user);

    /**
     * The signing out method for the firebase.
     */
    void signOut();
}
