package com.example.abhishekshah1.entrydatabase;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    String Table_Name="STUDENT";
    String StudentName;
    String FatherName;
    String MotherName;
    EditText editText;
    EditText editText2;
    EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         editText=(EditText)findViewById(R.id.editText);
         editText2=(EditText)findViewById(R.id.editText2);
         editText3=(EditText)findViewById(R.id.editText3);
        String name="STUDENT_NAME";
        String fname="FATHER_NAME";
        String mname="MOTHER_NAME";
        String CONTENT_URI="content://com.example.abhishekshah1.entrydatabase.provider/STUDENT";
        final ContentResolver resolver = getContentResolver();
        final String[] projection = new String[]{name,fname,mname};
        final String selection = null;
       final String[] selectionArguments = null;
       final String sortOrder = "ASC";
       final Uri uri = Uri.parse(CONTENT_URI);
        String myType = resolver.getType(uri);
       StudentName=""+editText.getText();
         FatherName=""+editText.getText();
      MotherName=""+editText.getText();
        Button button=(Button)findViewById(R.id.button);
        try {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DataBase(getApplicationContext());
                    StudentName=""+editText.getText();
                    FatherName=""+editText2.getText();
                   MotherName=""+editText3.getText();
                   new DataBase(getApplicationContext()).insertIntoTableValue(StudentName, FatherName, MotherName);
                 // Cursor cursor = new ContentProvide(MainActivity.this).query(uri, projection, selection, selectionArguments, sortOrder);
                    Toast.makeText(MainActivity.this,"DATA INSERTED ", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "ERROR " + e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
