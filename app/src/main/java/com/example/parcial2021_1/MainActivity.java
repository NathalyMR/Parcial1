package com.example.parcial2021_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnMessagesListener {
    private EditText posnX, posnY, recordatorio;
    private boolean amarillopress, verdepress, completo;
    private ImageView verdeb, amarillob, rojob;
    private Button vistab, confirmarb;
    private String color, posX, posY, msg, vcbtn;

    private Connection tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tcp = Connection.getInstance();
        tcp.setObserver(this);

        verdeb = findViewById(R.id.verdeb);
        amarillob = findViewById(R.id.amarillob);
        rojob = findViewById(R.id.rojob);
        posnX = findViewById(R.id.posX);
        posnY = findViewById(R.id.posY);
        recordatorio = findViewById(R.id.recordatorio);
        vistab = findViewById(R.id.vistab);
        confirmarb = findViewById(R.id.confirmarb);
        amarillopress = true;
        verdepress = true;


        verdeb.setOnClickListener(
                (v) -> {
                    amarillopress = false;
                    verdepress = true;
                    color();

                }
        );
        amarillob.setOnClickListener(
                (v) -> {
                    amarillopress = true;
                    verdepress = false;
                    color();

                }
        );
        rojob.setOnClickListener(
                (v) -> {
                    amarillopress = false;
                    verdepress = false;
                    color();

                }
        );
        confirmarb.setOnClickListener(
                v -> {
                    vcbtn = "1";
                    comprobacion();
                    if (completo) {
                        String json1 = color + "," + posX + "," + posY + "," + msg + "," + vcbtn;
                        tcp.mensaje(json1);
                        reset();
                    }
                }
        );
        vistab.setOnClickListener(
                v -> {
                    vcbtn = "2";
                    comprobacion();
                    if (completo) {
                        String json1 = color + "," + posX + "," + posY + "," + msg + "," + vcbtn;
                        tcp.mensaje(json1);
                    }
                }
        );
    }

    private void comprobacion() {
        completo = true;
        msg = recordatorio.getText().toString();
        posX = posnX.getText().toString();
        posY = posnY.getText().toString();

        if (msg.isEmpty() || msg.equals(null) || posX.isEmpty() || posX.equals(null) || posY.isEmpty() || posY.equals(null)) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show();
            completo = false;
        } else if (amarillopress && verdepress) {
            Toast.makeText(this, "Seleccione un color", Toast.LENGTH_LONG).show();
            completo = false;
        }
    }

    private void color() {
        if (verdepress) {
            color = "verde";
        } else if (amarillopress) {
            color = "amarillo";
        } else {
            color = "rojo";
        }
    }
    @Override
    public void OnMessage(String msg) {
        runOnUiThread(
                () -> {
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }
        );
    }

    public void reset() {
        posnX.setText("");
        posnY.setText("");
        recordatorio.setText("");
        color = "rojo";

        amarillob.setImageResource(R.drawable.amarillo);
        rojob.setImageResource(R.drawable.rojo);
        verdeb.setImageResource(R.drawable.verde);
        amarillopress = true;
        verdepress = true;
    }
}

