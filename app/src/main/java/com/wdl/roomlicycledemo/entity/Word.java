package com.wdl.roomlicycledemo.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Create by: wdl at 2019/10/30 10:37
 * 实体类，对应数据库中对应的表
 */
@Entity(tableName = "table_word")
@SuppressWarnings("unused")
public class Word
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    // 主键必须加上 @NonNull 注解
    @ColumnInfo(name = "word")
    @NonNull
    private String mWord;

    public Word(int id, @NonNull String mWord)
    {
        this.id = id;
        this.mWord = mWord;
    }

    public String getWord()
    {
        return mWord;
    }

    public void setWord(String mWord)
    {
        this.mWord = mWord;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
