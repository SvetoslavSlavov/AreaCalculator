package applications.editablelistview;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd,btnView;
    EditText editTextBase;
    TextView resultT;
    EditText editTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextBase = (EditText) findViewById(R.id.editTextBase);
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);
        resultT = (TextView) findViewById(R.id.resultView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String base = editTextBase.getText().toString();
                String height = editTextHeight.getText().toString();

                double baseV =  Double.parseDouble(base);
                double heightV =  Double.parseDouble(height);
                double result = (baseV * heightV) / 2;

                String finalResult = Double.toString(result);

                if(base.length()!= 0 && height.length() != 0){
                    AddData(finalResult);
                    editTextBase.setText("");
                    editTextHeight.setText("");
                    resultT.setText("Result: " + finalResult);

                }else{
                    Toast.makeText(MainActivity.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });


    }

    public void AddData(String newEntry) {

        boolean insertData = myDB.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }
}
