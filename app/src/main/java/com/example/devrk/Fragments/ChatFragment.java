package com.example.devrk.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.devrk.Adapters.UserAdapter;
import com.example.devrk.Models.Users;
import com.example.devrk.R;
import com.example.devrk.databinding.FragmentChatBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public
class ChatFragment extends Fragment {



    public ChatFragment() {
        // Required empty public constructor
    }

    FragmentChatBinding binding;

    private Object ArrayList;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     binding = FragmentChatBinding.inflate(inflater, container, false);

        UserAdapter adapter = new UserAdapter(list, getContext());
        binding.chatRecyclarView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecyclarView.setLayoutManager(layoutManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                  Users users = dataSnapshot.getValue(Users.class);
                  Users.getUserId(dataSnapshot.getKey());
                  list.add(users);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
     return binding.getRoot();

    }
}