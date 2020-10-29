package edu.uw.tcss450.ui;

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
import edu.uw.tcss450.databinding.FragmentRegisterBinding;

/**
 * A simple {@link Fragment} subclass.

 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private FragmentRegisterBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)  {
        super.onViewCreated(view, savedInstanceState);


        binding.buttonRegistrationconfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        processRegistration(binding.edittextRegisteremailaddress.getText().toString(),
                binding.edittextPasswordregister.getText().toString(),
                binding.edittextPasswordconfirmregister.getText().toString());
    }

    public void processRegistration(String email, String password, String password2)    {
        int error = 0;
        if(email.isEmpty()) error = 1;
        else if(!(email.contains("@"))) error = 4;
        else if(password.isEmpty()) error = 2;
        else if(password2.isEmpty()) error = 3;
        else if(!(password.equals(password2))) error = 5;
        else if(password.length() < 6) error = 6;
        else if(!(password.contains("!")) &&
                !(password.contains("@")) &&
                !(password.contains("#")) &&
                !(password.contains("#")) &&
                !(password.contains("$")) &&
                !(password.contains("%")) &&
                !(password.contains("^")) &&
                !(password.contains("&")) &&
                !(password.contains("*")) &&
                !(password.contains("`")) &&
                !(password.contains("~")) ) error = 7;

        if (error > 0) {
            processError(error);
        } else {
            //The following object represents the action from sign in to success.
            RegisterFragmentDirections.ActionRegisterFragmentToMainActivity directions =
                    RegisterFragmentDirections.actionRegisterFragmentToMainActivity(email, "");

            //Use  the navigate method to perform the navigation.
            Navigation.findNavController(getView()).navigate(directions);
        }
        binding.edittextRegisteremailaddress.requestFocus();
        binding.edittextPasswordregister.requestFocus();
        binding.edittextPasswordconfirmregister.requestFocus();
    }

    public void processError(int error) {
        switch (error) {
            case 1:
                binding.edittextRegisteremailaddress.setError("" +
                        "Invalid Email - field cannot be empty");
                break;
            case 2:
                binding.edittextPasswordregister.setError("" +
                        "Invalid Password - field cannot be empty");
                break;
            case 3:
                binding.edittextPasswordconfirmregister.setError("" +
                        "Invalid Password - field cannot be empty");
                break;
            case 4:
                binding.edittextRegisteremailaddress.setError("Invalid Email - must contain @");
                break;
            case 5:
                binding.edittextPasswordconfirmregister.setError("" +
                        "Invalid Password - passwords must match");
                break;
            case 6:
                binding.edittextPasswordregister.setError("" +
                        "Invalid Password - password must be more than 5 characters");
                break;
            case 7:
                binding.edittextPasswordregister.setError("" +
                        "Invalid Password - password must contain a special character !@#$%^&*~`");
                break;
        }

    }

}
