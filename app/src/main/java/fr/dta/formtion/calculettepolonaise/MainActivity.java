package fr.dta.formtion.calculettepolonaise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    /*
        This is a Polish Calculator. Regarding how it works, the user can put a number between 0 and
        around 999 999 999 (limited by the size of Integer, any larger number would cause the app to
        crash).
        The user writes a number in the inputView.
        All operations are kept in the stackCalculator
        The listView is in charge of displaying the number in the stackCalculator

        Created by Thomas FOLMER with almost no coffee...  :'(
     */

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

        //Assigantion of the numerous buttons
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


        /*
            Declaration of one OnClick in charge of the buttons, on for the operators (+,-,*,/) and
            a last one targeted at the buttons that are going to manipulate the stack
         */

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


        /*
            Assignation of the onClickListener
         */
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


    /*
        Method in charge of filling the input field (where the number is created and before it is
        sent to the stack
     */

    private void displayNumber(View v){
        String number = v.getTag().toString();
        String prev = this.inputView.getText().toString();
        this.inputView.setText(prev + number);
    }


    /*
        Method in charge of adding the number to the stack
     */

    private void addNumber() {

        String num = this.inputView.getText().toString();
        int number = Integer.parseInt(num);
        this.stackCalculette.add(number);
    }


  /*
        Trigerred by the onClickListener, this method is going to perform the operation asked
        and update the stack
   */
    private void operation(View v) {
        String input = v.getTag().toString(); // the button send its function through a tag
        if(this.stackCalculette.size()>=2){   // check if a calculation can be made

            if(input.equals("+")){
                /*
                   -  Gather the last number in order to add it to the one before.
                   -  The result is set as the last number and the other is removed
                   -  The stack is updated
                 */
                int result = this.stackCalculette.get(this.stackCalculette.size()-1) + this.stackCalculette.get(this.stackCalculette.size()-2);
                this.stackCalculette.remove(this.stackCalculette.size()-2);
                this.stackCalculette.set(this.stackCalculette.size()-1,result);
                updateListView();
            }
            if(input.equals("-")){
                /*
                      Same as above
                 */
                int result = this.stackCalculette.get(this.stackCalculette.size()-1) - this.stackCalculette.get(this.stackCalculette.size()-2);
                this.stackCalculette.remove(this.stackCalculette.size()-2);
                this.stackCalculette.set(this.stackCalculette.size()-1,result);
                updateListView();
            }
            if(input.equals("*")){
                 /*
                      Same as above
                 */
                int result = this.stackCalculette.get(this.stackCalculette.size()-1) * this.stackCalculette.get(this.stackCalculette.size()-2);
                this.stackCalculette.remove(this.stackCalculette.size()-2);
                this.stackCalculette.set(this.stackCalculette.size()-1,result);
                updateListView();
            }
            if(input.equals("/")){
                 /*
                      Same as above with a check to see if the division does not include a 0
                 */
                if( this.stackCalculette.get(stackCalculette.size()-2)!=0  &&  this.stackCalculette.get(stackCalculette.size()-1)!=0  ){
                    int result = this.stackCalculette.get(this.stackCalculette.size()-1) / this.stackCalculette.get(this.stackCalculette.size()-2);
                    this.stackCalculette.remove(this.stackCalculette.size()-2);
                    this.stackCalculette.set(this.stackCalculette.size()-1,result);
                    updateListView();
                }else{

                    Toast.makeText(this, "Attention, on ne divise pas par 0", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    /*
        Clear the number being written by the user
     */
    private void emptyInputView(){
        this.inputView.setText("");
    }

    /*
        Method called by the onClickListener regarding the buttons that modify the stack
     */
    private void stackTransformation(View v) {
        String input = v.getTag().toString();

        if(input.equals("enter")){
            /*
                Method in charge of adding the number to the stack
             */
            String number = this.inputView.getText().toString();
            if(!number.equals("")){             //Check if the inputView is not empty
                if(number.length()>9){          //Check if the number is not too big (constrain of the Integers)
                    Toast.makeText(this, "Come on men, be serious! Merci de mettre un nombre plus petit", Toast.LENGTH_SHORT).show();
                }else{
                    addNumber();                //Methode that add the number to the stack
                    String num = this.inputView.getText().toString();
                    Log.d("display", "Le nombre à ajouter à la display stack est :"+num);
                    updateListView();           //Update the display
                    emptyInputView();           //Clear the inputView
                }
            }
        }
        if(input.equals("pop")){
            // Remove the last number in the stack
            if(stackCalculette.size()!=0){
                stackCalculette.remove(stackCalculette.size()-1);
                updateListView();
            }
        }
        if(input.equals("swap")){
            //Change the last number in the stack with the one right before
            if(stackCalculette.size()>1){
                int previous = stackCalculette.get(stackCalculette.size()-2);
                Log.d("stack", "l'avant dernier nombre de la stack est : "+previous );
                stackCalculette.set(stackCalculette.size()-2, stackCalculette.get(stackCalculette.size()-1));
                stackCalculette.set(stackCalculette.size()-1, previous);
                updateListView();
            }
        }

        if(input.equals("clear")){
            //Clear the inputView
            this.inputView.setText("");
        }

        if(input.equals("random")){
            //Generate a random number
            Random random = new Random();
            int nombreAleatoire = random.nextInt(99999 - 0 );
            this.inputView.setText(String.valueOf(nombreAleatoire));
        }

    }

    /*
        Method in charge of updating the List View with the stack
     */
    private void updateListView(){
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(MainActivity.this,
                android.R.layout.simple_list_item_1, stackCalculette);
        listView.setAdapter(adapter);
    }

    /*
        Methods used to save the values if the screen changes
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.stackCalculette=savedInstanceState.getIntegerArrayList("stack");
        updateListView();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("stack", (ArrayList<Integer>) this.stackCalculette);
    }
}
