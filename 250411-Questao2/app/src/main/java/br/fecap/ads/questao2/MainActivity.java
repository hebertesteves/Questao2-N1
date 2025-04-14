package br.fecap.ads.questao2;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declaração dos elementos da interface (View)
    private EditText campoSalario;
    private RadioGroup radioGroup;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Função para calcular novo salário
    public void calcularNovoSalario(View view) {

        // Vinculando os elementos com os Views
        campoSalario = findViewById(R.id.txtSalario);
        radioGroup = findViewById(R.id.radioGroup);
        textResultado = findViewById(R.id.textResultado);

        // Pega o salário digitado e converte em string
        String salario = campoSalario.getText().toString();

        // Verifica se o campo de salário está vazio, caso esteja ele vai usar um return para o usuario não prosseguir sem digitar
        if (salario.isEmpty()) {
            campoSalario.setError("Informe seu salário");
            campoSalario.requestFocus(); // Coloca o foco no campo para o usuário digitar
            return;
        }

        // Pega o salário digitado, converte em double e armezena na variavel numSalario
        double numSalario = Double.parseDouble(salario);

        // Variavel para armazenar o valor do salario reajustado
        double novoSalario = 0.0;

        // Obter o ID do RadioButton selecionado e atribuir a variavel selecionarId
        int selecionarId = radioGroup.getCheckedRadioButtonId();

        // Verificar se nenhum radio button do Tamanho está selecionado
        if (selecionarId == -1) {
            Toast.makeText(this, "Escolha o Percentual", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selecionarId == R.id.radio40) {
            // Salario reajustado em 40%
            novoSalario = (numSalario * 140) / 100;
        } else if (selecionarId == R.id.radio45) {
            // Salario reajustado em 45%
            novoSalario = (numSalario * 145) / 100;
        } else if (selecionarId == R.id.radio50) {
            // Salario reajustado em 50%
            novoSalario = (numSalario * 150) / 100;
        }

        // Exibe o resultado do salario reajusto com formatação de duas casas decimais
        textResultado.setText(String.format("O salario reajustado é de R$ %.2f", novoSalario));

    }

}