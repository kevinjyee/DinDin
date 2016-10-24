package com.example.dindin;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * Created by Davin on 10/23/2016.
 */

public class ProfileEditFragment extends Fragment implements View.OnClickListener{
    View view;
    Button saveButton;
    Button cancelButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profileview, null);
        view = root;
        saveButton = (Button) view.findViewById(R.id.editProfileButton);
        saveButton.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new ProfileEditFragment());
        ft.commit();

        ft.addToBackStack(null);
    }
}
