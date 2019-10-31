package com.wdl.roomlicycledemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.wdl.roomlicycledemo.entity.Word;
import com.wdl.roomlicycledemo.repository.WordRepository;

/**
 * Create by: wdl at 2019/10/30 10:55
 * ViewModel
 */
public class IViewModel extends AndroidViewModel
{
    private WordRepository mRepository;
    private LiveData<PagedList<Word>> mPageLists;

    public IViewModel(@NonNull Application application)
    {
        super(application);
        mRepository = new WordRepository(application);
        // mAllWords = mRepository.getAllWords();
        mPageLists = mRepository.getPageList();
    }

    public LiveData<PagedList<Word>> getmPageLists()
    {
        return mPageLists;
    }

    public void insert(Word word)
    {
        mRepository.insert(word);
    }

    public void delete(Word word)
    {
        mRepository.delete(word);
    }

}
