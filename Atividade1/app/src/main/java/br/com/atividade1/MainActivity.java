package br.com.atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestoesTextView;
    TextView questaoTextView;
    Button btnA, btnB, btnC, btnD, btnE;
    Button btnProximo;

    int resultado =0;
    int totalQuestoes = Questoes.perguntas.length;
    int numPerguntaIndex = 0;
    String respostas = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestoesTextView = findViewById(R.id.total_questoes);
        questaoTextView = findViewById(R.id.n_pergunta);
        btnA = findViewById(R.id.btn_A);
        btnB = findViewById(R.id.btn_B);
        btnC = findViewById(R.id.btn_C);
        btnD = findViewById(R.id.btn_D);
        btnE = findViewById(R.id.btn_E);
        btnProximo = findViewById(R.id.btn_proximo);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnProximo.setOnClickListener(this);

        questaoTextView.setText("Total questoes: " + totalQuestoes);

        novaPergunta();




    }

    @Override
    public void onClick(View view) {

        btnA.setBackgroundColor(Color.WHITE);
        btnB.setBackgroundColor(Color.WHITE);
        btnC.setBackgroundColor(Color.WHITE);
        btnD.setBackgroundColor(Color.WHITE);
        btnE.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.btn_proximo){
            if(respostas.equals(Questoes.respostas[numPerguntaIndex])){
                resultado++;
            }
            numPerguntaIndex++;
            novaPergunta();


        }else{
            //choices button clicked
            respostas  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.BLUE);

        }

    }

    void novaPergunta(){

        if(numPerguntaIndex == totalQuestoes ){
            finishQuiz();
            return;
        }

        questaoTextView.setText(Questoes.perguntas[numPerguntaIndex]);
        btnA.setText(Questoes.escolha[numPerguntaIndex][0]);
        btnB.setText(Questoes.escolha[numPerguntaIndex][1]);
        btnC.setText(Questoes.escolha[numPerguntaIndex][2]);
        btnD.setText(Questoes.escolha[numPerguntaIndex][3]);
        btnE.setText(Questoes.escolha[numPerguntaIndex][4]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(resultado > totalQuestoes*0.60){
            passStatus = "Passou";
        }else{
            passStatus = "Falhou";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Acertou: "+ resultado+" do total de: "+ totalQuestoes)
                .setPositiveButton("Fazer de Novo",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        resultado = 0;
        numPerguntaIndex =0;
        novaPergunta();
    }


}