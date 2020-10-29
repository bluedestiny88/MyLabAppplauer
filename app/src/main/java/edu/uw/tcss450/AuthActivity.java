package edu.uw.tcss450;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.uw.tcss450.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  binding = ActivityAuthBinding.inflate(getLayoutInflater());
        //  setContentView(binding.getRoot());
        setContentView(R.layout.activity_auth);
    }


}