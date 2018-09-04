package com.example.sean.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button create_dataBase = (Button) findViewById(R.id.createDB);  //创建数据库按钮事件
        create_dataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
                Toast.makeText(MainActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
            }
        });


        Button addData = (Button) findViewById(R.id.addData);   //添加数据按钮事件
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();  //创建一个表的对象
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });

        Button updateData = (Button) findViewById(R.id.updateData);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");
            }
        });

        Button deleteData = (Button) findViewById(R.id.deleteData);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                LitePal.deleteAll(Book.class,"price < ?","15");
            }
        });

        Button selectData = (Button) findViewById(R.id.selectData);
        selectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for(Book book:books) {
                    Toast.makeText(MainActivity.this,book.getName(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,book.getAuthor(),Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(MainActivity.this,book.getPages(),Toast.LENGTH_SHORT).show();
                   // Toast.makeText(MainActivity.this,book.getPrice(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,book.getPress(),Toast.LENGTH_SHORT).show();
                    //v Toast.makeText(MainActivity.this,book.getId(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
