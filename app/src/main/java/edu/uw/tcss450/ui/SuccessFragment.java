package edu.uw.tcss450.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.R;
import edu.uw.tcss450.databinding.FragmentSuccessBinding;
import edu.uw.tcss450.model.UserInfoViewModel;

/**
 * A simple {@link Fragment} subclass.

 */
public class SuccessFragment extends Fragment {

    private FragmentSuccessBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = edu.uw.tcss450.databinding.FragmentSuccessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)  {

        super.onViewCreated(view, savedInstanceState);

        //SuccessFragmentArgs args = SuccessFragmentArgs.fromBundle(getArguments());

        //binding.textviewEmailaddresssuccess.setText("Hello, " + args.getEmail());

        UserInfoViewModel model = new ViewModelProvider(getActivity()).get(UserInfoViewModel.class);
         binding.textviewEmailaddresssuccess.setText("Welcome Home " + model.getmEmail() + "!");
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}