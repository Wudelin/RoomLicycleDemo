package com.wdl.roomlicycledemo.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.wdl.roomlicycledemo.ExecutorKt;
import com.wdl.roomlicycledemo.dao.WordDao;
import com.wdl.roomlicycledemo.entity.Word;

import java.util.concurrent.Executor;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Create by: wdl at 2019/10/30 10:45
 * 数据库:指定实体 指定版本号
 */
@Database(entities = {Word.class}, version = 1)
public abstract class IRoomDatabase extends RoomDatabase
{
    /**
     * 必须返回
     *
     * @return WordDao DAO层
     */
    public abstract WordDao wordDao();

    private static volatile IRoomDatabase INSTANCE;

    public static IRoomDatabase getInstance(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (IRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IRoomDatabase.class, "i_database").addCallback(mInitCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化
     */
    private static RoomDatabase.Callback mInitCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    initDb();
                }
            }).start();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db)
        {
            super.onOpen(db);

        }
    };

    private static void initDb()
    {
        ExecutorKt.ioThread(new Function0<Unit>()
        {
            @Override
            public Unit invoke()
            {
                WordDao mDao = INSTANCE.wordDao();
                for (int i = 0; i < 60; i++)
                {
                    mDao.insert(new Word(0, "Hello " + i));
                }
                return Unit.INSTANCE;
            }
        });
    }
}
