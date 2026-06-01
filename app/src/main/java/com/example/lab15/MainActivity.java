package com.example.lab15;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab15.classes.Etudiant;
import com.example.lab15.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etId;
    private TextView tvResultat;
    private EtudiantService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service    = new EtudiantService(this);
        etNom      = findViewById(R.id.nom);
        etPrenom   = findViewById(R.id.prenom);
        etId       = findViewById(R.id.id);
        tvResultat = findViewById(R.id.res);

        findViewById(R.id.bn).setOnClickListener(v -> ajouterEtudiant());
        findViewById(R.id.load).setOnClickListener(v -> rechercherEtudiant());
        findViewById(R.id.delete).setOnClickListener(v -> supprimerEtudiant());
    }

    private void ajouterEtudiant() {
        String nom    = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();

        if (nom.isEmpty() || prenom.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        service.create(new Etudiant(nom, prenom));
        Toast.makeText(this, "Étudiant ajouté !", Toast.LENGTH_SHORT).show();
        etNom.setText("");
        etPrenom.setText("");
    }

    private void rechercherEtudiant() {
        String idStr = etId.getText().toString().trim();
        if (idStr.isEmpty()) return;

        Etudiant e = service.findById(Integer.parseInt(idStr));
        if (e != null) {
            tvResultat.setText("Résultat : " + e.getNom() + " " + e.getPrenom());
            tvResultat.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            tvResultat.setText("Étudiant non trouvé.");
            tvResultat.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void supprimerEtudiant() {
        String idStr = etId.getText().toString().trim();
        if (idStr.isEmpty()) return;

        Etudiant e = service.findById(Integer.parseInt(idStr));
        if (e != null) {
            service.delete(e);
            tvResultat.setText("Étudiant supprimé.");
            etId.setText("");
        } else {
            Toast.makeText(this, "ID introuvable", Toast.LENGTH_SHORT).show();
        }
    }
}
