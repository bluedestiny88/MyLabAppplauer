package edu.uw.tcss450.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.R;
import edu.uw.tcss450.databinding.FragmentSignInBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements View.OnClickListener {

    private FragmentSignInBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)  {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonRegister.setOnClickListener(this);

        binding.buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.buttonRegister) {
            Navigation.findNavController(getView()).navigate(
                    SignInFragmentDirections.actionSignInFragmentToRegisterFragment());
        }   else if (view == binding.buttonLogin)    {
            processLogin(binding.edittextEmailaddress.getText().toString());
        }

    }


    public void processLogin(String email) {
        String password = binding.edittextPassword.getText().toString();
        int error = 0;
        if(email.isEmpty()) error = 2;
        else if(!(email.contains("@"))) error = 1;
        else if(password.isEmpty()) error = 3;

        if (error > 0) {
            processError(error);
        } else {
            //The following object represents the action from sign in to success.
            SignInFragmentDirections.ActionSignInFragmentToMainActivity directions =
                    SignInFragmentDirections.actionSignInFragmentToMainActivity(email, "");

            //Use  the navigate method to perform the navigation.
            Navigation.findNavController(getView()).navigate(directions);

            getActivity().finish();
        }
        binding.edittextEmailaddress.requestFocus();
        binding.edittextPassword.requestFocus();
    }

    public void processError(int error) {
        switch (error) {
            case 1:
                binding.edittextEmailaddress.setError(
                        "Invalid email address - missing @ character");
                break;
            case 2:
                binding.edittextEmailaddress.setError(
                        "Invalid email address - field cannot be empty");
                break;
            case 3:
                binding.edittextPassword.setError(
                        "Invalid password - field cannot be empty");
                break;
        }

    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}