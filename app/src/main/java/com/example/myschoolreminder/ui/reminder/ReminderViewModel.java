/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : The reminder view model
 */
package com.example.myschoolreminder.ui.reminder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReminderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReminderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reminder fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}