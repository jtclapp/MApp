package com.music.songcreator.java_operations;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.music.songcreator.R;

public class LoadingHelper
{
    Activity activity;
    AlertDialog dialog;

    public LoadingHelper(Activity myActivity)
    {
        this.activity = myActivity;
    }

    public void startLoadingDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }
    public void dismissDialog()
    {
        dialog.dismiss();
    }
}
