package com.example.exitpoll;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exitpoll.db.DatabaseHelper;
import com.example.exitpoll.model.VoteItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.exitpoll.db.DatabaseHelper.COL_ID;
import static com.example.exitpoll.db.DatabaseHelper.COL_IMAGE;
import static com.example.exitpoll.db.DatabaseHelper.COL_NUMBER;
import static com.example.exitpoll.db.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<VoteItem> VoteItemLists;

    private long Id;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();
        loadPhoneData();


        Button resultButton = findViewById(R.id.btnResult);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        ResultActivity.class);

                startActivity(intent);
            }
        });

        Button notvote = findViewById(R.id.NotVote);
        notvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoteItem noVote = VoteItemLists.get(0);
                count = noVote.num;
                count+=1;
                Id=1;
                updateVote();
                Toast.makeText(MainActivity.this,"งดออกเสียง",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button btnNo1 = findViewById(R.id.btnNo1);
        btnNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoteItem voteNo1 = VoteItemLists.get(1);
                count = voteNo1.num;
                count+=1;
                Id=2;
                updateVote();
                Toast.makeText(MainActivity.this,"เลือกผู้สมัครหมายเลข 1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button btnNo2 = findViewById(R.id.btnNo2);
        btnNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoteItem voteNo2 = VoteItemLists.get(2);
                count = voteNo2.num;
                count+=1;
                Id=3;
                updateVote();
                Toast.makeText(MainActivity.this,"เลือกผู้สมัครหมายเลข 2",Toast.LENGTH_SHORT).show();
            }
        });
        Button btnNo3 = findViewById(R.id.btnNo3);
        btnNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoteItem voteNo3 = VoteItemLists.get(3);
                count = voteNo3.num;
                count+=1;
                Id=4;
                updateVote();
                Toast.makeText(MainActivity.this,"เลือกผู้สมัครหมายเลข 3",Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void updateVote(){
        ContentValues contentV = new ContentValues();

        contentV.put(COL_NUMBER, count);

        mDb.update(
                TABLE_NAME,
                contentV,
                COL_ID + " = ?",
                new String[]{String.valueOf(Id)}
        );
        loadPhoneData();
    }

    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        VoteItemLists = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));

            int number = c.getInt(c.getColumnIndex(COL_NUMBER));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            VoteItem item = new VoteItem(id, number, image);
            VoteItemLists.add(item);
        }
        c.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPhoneData();
    }
}
