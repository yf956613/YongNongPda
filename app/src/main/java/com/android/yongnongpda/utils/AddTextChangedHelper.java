package com.android.yongnongpda.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by shijie.yang on 2018/3/1.
 */

public abstract class AddTextChangedHelper  {
    public void addTextChangeLser(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                showSearch(editText,s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public abstract void showSearch(EditText editText,String s);
}
