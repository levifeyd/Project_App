package android.example.AppCinema;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CreateRoomFragment extends Fragment {
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_room_fragment, container, false);
        navController= NavHostFragment.findNavController(this);

        Button button_next = (Button) rootView.findViewById(R.id.b_start);
        button_next.setOnClickListener(view -> navController.navigate(R.id.fragment2));
        return rootView;
    }
}