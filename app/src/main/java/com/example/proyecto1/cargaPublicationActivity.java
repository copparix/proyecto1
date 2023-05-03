package com.example.proyecto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class cargaPublicationActivity extends AppCompatActivity {
    Button post_button;
    EditText title_input, text_input, source_input;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_publication);

        mfirestore = FirebaseFirestore.getInstance();
        post_button = findViewById(R.id.post_button);
        title_input = findViewById(R.id. title_input);
        text_input = findViewById(R.id.text_input);
        source_input = findViewById(R.id.source_input);

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = title_input.getText().toString();
                String text = text_input.getText().toString();
                String source = source_input.getText().toString();

                if (title.isEmpty() || text.isEmpty() || source.isEmpty())
                    Toast.makeText(cargaPublicationActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    postPublication(title, text, source);
                }

            }
        });
    }

    private void postPublication(String title, String text, String source) {
        Map<String, Object> newPublication = new HashMap<>();
        newPublication.put("title", title);
        newPublication.put("text", text);
        newPublication.put("source", source);

        mfirestore.collection("publications").add(newPublication).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(cargaPublicationActivity.this, "Publicado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(cargaPublicationActivity.this, "Error al publicar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goBack(View view){
        Intent intent = new Intent(cargaPublicationActivity.this, menuActivity.class);
        startActivity(intent);
    }
}