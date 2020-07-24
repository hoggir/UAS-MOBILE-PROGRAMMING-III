package com.example.amikomhilang.Core.Register;

import android.app.Activity;

import com.example.amikomhilang.Model.Mahasiswa;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPresenter implements RegistrationContract.Presenter, RegistrationContract.onRegistrationListener {
    private RegistrationContract.View mRegisterView;
    private RegistrationInteractor mRegistrationInteractor;

    public RegistrationPresenter(RegistrationContract.View registerView){
        this.mRegisterView = registerView;
        mRegistrationInteractor = new RegistrationInteractor(this);
    }
    @Override
    public void register(Activity activity, String email, String password) {
        mRegistrationInteractor.performFirebaseRegistration(activity,email,password);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        String username = usernameFromEmail(firebaseUser.getEmail());

        // membuat User admin baru
        writeNewAdmin(firebaseUser.getUid(), username, firebaseUser.getEmail());
        mRegisterView.onRegistrationSuccess(firebaseUser);
    }

    @Override
    public void onFailure(String message) {
        mRegisterView.onRegistrationFailure(message);

    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewAdmin(String userId, String name, String email) {
        Mahasiswa mahasiswa = new Mahasiswa(name, email);

        FirebaseDatabase.getInstance().getReference().child("Mahasiswa").child(userId).setValue(mahasiswa);
    }
}