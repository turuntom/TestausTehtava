package com.example.testipohja;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PohjaFragment extends Fragment {

    private PohjaViewModel mViewModel;
    private EditText editText;
    private Button button;
    private TextView textView;

    public static PohjaFragment newInstance(String defaultTxt)
    {
       return new PohjaFragment();
    }

    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pohja_fragment, container, false);
        this.view = v;
        button = (Button)v.findViewById(R.id.nappi);
        editText = (EditText)v.findViewById(R.id.editText);
        textView = (TextView)v.findViewById(R.id.teksti);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muutaTeksti(editText.getText().toString());
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PohjaViewModel.class);
    }

    public boolean muutaTeksti(String teksti){
        if (teksti.length() > 20) {
            return false;
        }
        textView.setText(teksti);
        return true;
    }

    public View getNakyma(){
        return view;
    }

}
