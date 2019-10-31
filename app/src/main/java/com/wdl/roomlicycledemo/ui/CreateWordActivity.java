package com.wdl.roomlicycledemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wdl.roomlicycledemo.R;

public class CreateWordActivity extends AppCompatActivity
{
    public static final String EXTRA = "EXTRA";
    private EditText mEtWord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_word);

        mEtWord = findViewById(R.id.et_word);
        Button btn = findViewById(R.id.btn_create);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEtWord.getText()))
                {
                    setResult(RESULT_CANCELED, replyIntent);
                } else
                {
                    replyIntent.putExtra(EXTRA, mEtWord.getText().toString());
                    setResult(RESULT_OK, replyIntent);
                }

                finish();
            }
        });
    }
}
