package com.wdl.roomlicycledemo.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wdl.roomlicycledemo.R;
import com.wdl.roomlicycledemo.entity.Word;


/**
 * Create by: wdl at 2019/10/30 11:08
 */
public class WordsAdapter extends PagedListAdapter<Word, WordsAdapter.WordViewHolder>
{
    private OnListener mListener;

    public WordsAdapter()
    {
        super(DIFF_CALLBACK);
    }

    public void setmListener(OnListener mListener)
    {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_layout, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position)
    {
        final Word word = getItem(position);
        if (word == null) return;
        if (!TextUtils.isEmpty(word.getWord()))
            holder.tvWord.setText(word.getWord());
        else
            holder.tvWord.setText("无单词");
        if (mListener != null)
        {
            holder.tvWord.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mListener.click(v, word);
                }
            });
            holder.tvWord.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    mListener.longClick(v, word);
                    return false;
                }
            });
        }
    }

    class WordViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView tvWord;

        WordViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tv_word);
        }
    }

    /**
     * 点击监听
     */
    public interface OnListener
    {
        void click(View v, Word word);

        void longClick(View v, Word word);
    }

    private static DiffUtil.ItemCallback<Word> DIFF_CALLBACK = new DiffUtil.ItemCallback<Word>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem)
        {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem)
        {
            return oldItem.getWord().equals(newItem.getWord());
        }
    };
}
