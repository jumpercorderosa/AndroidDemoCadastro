package com.example.danielle.democadastro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_NOVO_USUARIO = 1;

    @BindView(R.id.etUsername)
    EditText etUsername; //não utiliza o private neste caso, só public ou protected, esta implícito como protected

    @BindView(R.id.etSenha)
    EditText etSenha;

    @BindView(R.id.etNovoUsername)
    TextView tvNovoUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        //para não perer o contexto
        if(savedInstanceState != null) {
            tvNovoUsername.setText(savedInstanceState.getString("TEXTO"));
        }

        requestSmsPermission();

    }

    @OnClick(R.id.etNovoUsername)
    public void novoUsuarioClick(){
        Intent novoUsuario = new Intent(this, NovoUsuarioActivity.class);
        startActivityForResult(novoUsuario, REQUEST_NOVO_USUARIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_NOVO_USUARIO:
                etUsername.setText(data.getStringExtra("USERNAME"));
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TEXTO", tvNovoUsername.getText().toString());
    }

    // Acima do Android 6 ou M, tem que pedir permissão para o usuário
    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }
}
