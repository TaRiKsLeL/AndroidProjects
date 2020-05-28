package com.example.permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PermissionsActivity extends AppCompatActivity {


    //==========UI============

    ListView permissionsToPromptLV;

    //======================
    ArrayAdapter adapter;

    public String[] permissionsNeeded = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.NFC,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY,
            Manifest.permission.ANSWER_PHONE_CALLS
    };

    public ArrayList<String> permissionsToPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        permissionsToPromptLV = findViewById(R.id.permissionsListView);
        permissionsToPrompt=new ArrayList<>();
        initPermissionsToPromptList();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, permissionsToPrompt);
        permissionsToPromptLV.setAdapter(adapter);
        permissionsToPromptLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String [] perms = new String[]{permissionsToPrompt.get(position)};
                requestPermissions(perms,Math.abs(permissionsToPrompt.get(position).hashCode()+10));
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == Math.abs(permissions[0].hashCode()+10)){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initPermissionsToPromptList();
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initPermissionsToPromptList(){
        permissionsToPrompt.clear();

        for(int i=0;i<permissionsNeeded.length;i++){
            if(checkSelfPermission(permissionsNeeded[i])== PackageManager.PERMISSION_DENIED){
                permissionsToPrompt.add(permissionsNeeded[i]);
            }
        }
    }

}
