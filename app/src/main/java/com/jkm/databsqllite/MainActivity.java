 package com.jkm.databsqllite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {

    DB obj;
    EditText e1,e2,e3,e4;
    Button b1,b2,b3,b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obj=new DB(this);
        ShowData();
        AddData();
        UpdateData();
        DeleteData();
        clear();
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder bobj=new AlertDialog.Builder(this);
        bobj.setCancelable(true);
        bobj.setTitle(title);
        bobj.setMessage(message);
        bobj.show();
    }
    public void ShowData()
    {
        b1=(Button)findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=obj.dataShow();
                if(c.getCount()==0)
                {
                    showMessage("ERROR","NO DATA FOUND!!!!!");
                    return;
                }
                StringBuffer str=new StringBuffer();
                while(c.moveToNext())
                {
                    str.append("ID : "+c.getString(0)+"\n");
                    str.append("Item : "+c.getString(1)+"\n");
                    str.append("Batch No. : "+c.getString(2)+"\n");
                    str.append("Price : "+c.getString(3)+"\n");
                }
                showMessage("ITEMS : ",str.toString());
            }
        });
    }
    public void AddData()
    {
        b2=(Button)findViewById(R.id.btn2);
        e1=(EditText)findViewById(R.id.edt1);
        e2=(EditText)findViewById(R.id.edt2);
        e3=(EditText)findViewById(R.id.edt3);
        e4=(EditText)findViewById(R.id.edt4);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean add=obj.dataInsert(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
                if(add==true)
                {
                    Toast.makeText(MainActivity.this,"DATA INSERTED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"DATA NOT INSERTED",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void UpdateData()
    {
        b3=(Button)findViewById(R.id.btn3);
        e1=(EditText)findViewById(R.id.edt1);
        e2=(EditText)findViewById(R.id.edt2);
        e3=(EditText)findViewById(R.id.edt3);
        e4=(EditText)findViewById(R.id.edt4);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean upd=obj.dataUpdate(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
                if(upd==true)
                    Toast.makeText(MainActivity.this,"DATA UPDATED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"DATA NOT UPDATED",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void DeleteData()
    {
        b4=(Button)findViewById(R.id.btn4);
        e1=(EditText)findViewById(R.id.edt1);
        /*e2=(EditText)findViewById(R.id.edt2);
        e3=(EditText)findViewById(R.id.edt3);
        e4=(EditText)findViewById(R.id.edt4);*/
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer del=obj.dataDelete(e1.getText().toString());
                if(del>0)
                    Toast.makeText(MainActivity.this,"DATA DELETED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"DATA NOT DELETED",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void clear()
    {
        b5=(Button)findViewById(R.id.btn5);
        e1=(EditText)findViewById(R.id.edt1);
        e2=(EditText)findViewById(R.id.edt2);
        e3=(EditText)findViewById(R.id.edt3);
        e4=(EditText)findViewById(R.id.edt4);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
            }
        });
    }
}
