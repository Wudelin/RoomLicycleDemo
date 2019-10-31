package com.wdl.roomlicycledemo.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wdl.roomlicycledemo.entity.Word;

import java.util.List;

/**
 * Create by: wdl at 2019/10/30 10:40
 * Dao层
 */
@Dao
@SuppressWarnings("unused")
public interface WordDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Query("DELETE FROM table_word")
    void deleteAll();

    @Delete
    void delete(Word word);

    @Query("SELECT * FROM table_word")
    List<Word> queryAll();

    @Query("SELECT * FROM table_word ORDER BY word ASC")
    LiveData<List<Word>> queryAsc();

    @Query("SELECT * FROM table_word ORDER BY word COLLATE NOCASE ASC")
    DataSource.Factory<Integer, Word> queryResultPaging();
}
