package com.example.danielle.democadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NovoUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.etNovoUsername)
    EditText etNovoUsername;

    @BindView(R.id.etNome)
    EditText etNome;

    @BindView(R.id.etSenha)
    EditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btCriar)
    public void criar() {
        Intent criar = new Intent();
        criar.putExtra("USERNAME", etNovoUsername.getText().toString());
        setResult(RESULT_OK, criar);
        finish();
    }


}
