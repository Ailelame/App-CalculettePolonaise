package fr.dta.formtion.calculettepolonaise;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<Integer> stackCalculette = new ArrayList<>();

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonMinus;
    Button buttonPlus;
    Button buttonDivided;
    Button buttonMulti;
    Button buttonEnter;
    Button buttonPop;
    Button buttonSwap;
    Button buttonClear;
    Button buttonRandom;

    TextView inputView;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonDivided = (Button) findViewById(R.id.buttonDivided);
        buttonMulti = (Button) findViewById(R.id.buttonMulti);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonPop = (Button) findViewById(R.id.buttonPop);
        buttonSwap = (Button) findViewById(R.id.buttonSwap);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonRandom = (Button) findViewById(R.id.buttonRandom);

        inputView = (TextView) findViewById(R.id.currentInput);

        listView = (ListView) findViewById(R.id.listV);


        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNumber(v);
            }
        };
        
        View.OnClickListener operandeListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                operation(v);
            }
        };

        View.OnClickListener transformationListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                stackTransformation(v);
            }
        };


        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);


        buttonMinus.setOnClickListener(operandeListener);
        buttonPlus.setOnClickListener(operandeListener);
        buttonDivided.setOnClickListener(operandeListener);
        buttonMulti.setOnClickListener(operandeListener);

        buttonEnter.setOnClickListener(transformationListener);
        buttonPop.setOnClickListener(transformationListener);
        buttonSwap.setOnClickListener(transformationListener);
        buttonClear.setOnClickListener(transformationListener);
        buttonRandom.setOnClickListener(transformationListener);



    }


    private void displayNumber(View v){
        String number = v.getTag().toString();
        String prev = this.inputView.getText().toString();
        this.inputView.setText(prev + number);
    }


    //update the stack view with the new input  and clear the input view
    private void displayStack(){
        String num = this.inputView.getText().toString();
        Log.d("display", "Le nombre à ajouter à la display stack est :"+num);
        updateListView();
        emptyInputView();



    }
    // Display the number hit by the user in the input view
    private void addNumber() {
        String num = this.inputView.getText().toString();
        int number = Integer.parseInt(num);
        this.stackCalculette.add(number);
    }

    //Method implementing the operande operations
    private void operation(View v) {
        String input = v.getTag().toString();
        if(this.stackCalculette.size()>=2){
            if(input.equals("+")){
                int result = this.stackCalculette.get(this.stackCalculette.size()-1) + this.stackCalculette.get(this.stackCalculette.size()-2);
                this.stackCalculette.remove(this.stackCalculette.size()-2);
                this.stackCalculette.set(this.stackCalculette.size()-1,result);

                updateListView();
            }
            if(input.equals("-")){
                int result = this.stackCalculette.get(this.stackCalculette.size()-1) - this.stackCalculette.get(this.stackCalculette.size()-2);
                this.stackCalculette.remove(this.stackCalculette.size()-2);
                this.stackCalculette.set(this.stackCalculette.size()-1,result);

                updateListView();
            }
            if(input.equals("*")){
                int result = this.stackCalculette.get(this.stackCalculette.size()-1) * this.stackCalculette.get(this.stackCalculette.size()-2);
                this.stackCalculette.remove(this.stackCalculette.size()-2);
                this.stackCalculette.set(this.stackCalculette.size()-1,result);

                updateListView();
            }
            if(input.equals("/")){
                int result = this.stackCalculette.get(this.stackCalculette.size()-1) / this.stackCalculette.get(this.stackCalculette.size()-2);
                this.stackCalculette.remove(this.stackCalculette.size()-2);
                this.stackCalculette.set(this.stackCalculette.size()-1,result);
                updateListView();
            }
        }
    }
    private void emptyInputView(){
        this.inputView.setText("");
    }


    private void stackTransformation(View v) {
        String input = v.getTag().toString();

        if(input.equals("enter")){
            String number = this.inputView.getText().toString();

            if(!number.equals("")){
                addNumber();
                displayStack();
            }
        }
        if(input.equals("pop")){
            if(stackCalculette.size()!=0){
                stackCalculette.remove(stackCalculette.size()-1);
                updateListView();
            }
        }
        if(input.equals("swap")){
            if(stackCalculette.size()>1){
                int previous = stackCalculette.get(stackCalculette.size()-2);
                Log.d("stack", "l'avant dernier nombre de la stack est : "+previous );
                stackCalculette.set(stackCalculette.size()-2, stackCalculette.get(stackCalculette.size()-1));
                stackCalculette.set(stackCalculette.size()-1, previous);
                updateListView();
            }
        }

        if(input.equals("clear")){
            this.inputView.setText("");
        }

        if(input.equals("random")){
            Random random = new Random();
            int nombreAleatoire = random.nextInt(9999 - 0 );
            this.inputView.setText(String.valueOf(nombreAleatoire));
        }

    }

    private void updateListView(){
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(MainActivity.this,
                android.R.layout.simple_list_item_1, stackCalculette);
        listView.setAdapter(adapter);
    }

}
