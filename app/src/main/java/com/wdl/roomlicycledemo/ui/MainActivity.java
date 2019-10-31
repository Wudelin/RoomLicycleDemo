package com.wdl.roomlicycledemo.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wdl.roomlicycledemo.R;
import com.wdl.roomlicycledemo.adapter.WordsAdapter;
import com.wdl.roomlicycledemo.entity.Word;
import com.wdl.roomlicycledemo.viewmodel.IViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRv;
    private FloatingActionButton mFab;
    private WordsAdapter mAdapter;
    private IViewModel iViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
        bind();
        init();
    }

    private void init()
    {
        iViewModel = ViewModelProviders.of(this).get(IViewModel.class);
        iViewModel.getmPageLists().observe(this, new Observer<PagedList<Word>>()
        {
            @Override
            public void onChanged(PagedList<Word> words)
            {
                mAdapter.submitList(words);
            }
        });
    }

    private void bind()
    {
        mRv.setAdapter(mAdapter);
    }

    private void initAdapter()
    {
        mAdapter = new WordsAdapter();
        mAdapter.setmListener(new WordsAdapter.OnListener()
        {
            @Override
            public void click(View v, Word word)
            {

            }

            @Override
            public void longClick(View v, final Word word)
            {
                Snackbar.make(mRv, "长按删除", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        iViewModel.delete(word);
                    }
                }).show();

            }
        });
    }

    private void initView()
    {
        mRv = findViewById(R.id.rv);
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CreateWordActivity.class);
                startActivityForResult(intent, 0x01);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01 && resultCode == RESULT_OK)
        {
            if (data != null)
                iViewModel.insert(new Word(0, Objects.requireNonNull(data.getStringExtra(CreateWordActivity.EXTRA))));
        } else
        {
            Snackbar.make(mFab, "输入的单词为空", Snackbar.LENGTH_SHORT).show();
        }
    }
}
