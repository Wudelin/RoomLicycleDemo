package com.wdl.roomlicycledemo.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.wdl.roomlicycledemo.ExecutorKt;
import com.wdl.roomlicycledemo.dao.WordDao;
import com.wdl.roomlicycledemo.database.IRoomDatabase;
import com.wdl.roomlicycledemo.entity.Word;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Create by: wdl at 2019/10/30 10:50
 * Repository
 */
public class WordRepository
{
    private WordDao mDao;
    private LiveData<PagedList<Word>> mPageList;

    public WordRepository(Application application)
    {
        IRoomDatabase database = IRoomDatabase.getInstance(application);
        mDao = database.wordDao();
        mPageList = new LivePagedListBuilder<>(
                mDao.queryResultPaging(),
                new PagedList.Config.Builder()
                        .setPageSize(15)            //每页显示的词条数
                        .setEnablePlaceholders(false)
                        //.setInitialLoadSizeHint(30) //首次加载的数据量
                        //.setPrefetchDistance(5)     // 距离底部5条数据开始加载新数据
                        .build()
        ).build();
    }

    public LiveData<PagedList<Word>> getPageList()
    {
        return mPageList;
    }

    public void insert(final Word word)
    {
        ExecutorKt.ioThread(new Function0<Unit>()
        {
            @Override
            public Unit invoke()
            {
                mDao.insert(word);
                return Unit.INSTANCE;
            }
        });
    }

    public void delete(final Word word)
    {
        ExecutorKt.ioThread(new Function0<Unit>()
        {
            @Override
            public Unit invoke()
            {
                mDao.delete(word);
                return Unit.INSTANCE;
            }
        });
    }

}
