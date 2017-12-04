package com.salsal.school.teacher.interfaces;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import com.salsal.school.teacher.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kasper on 11/16/2015.
 */
public abstract class CallbackHandler<T> implements Callback<T> {
    Context context;
    boolean showLoading;
    boolean showError;
    static int retrycount = 0;

    public CallbackHandler(Context context, boolean showLoading, boolean showError) {
        this.context = context;
        this.showLoading = showLoading;
        this.showError = showError;
        if (showLoading)
        {
            // MessageBox.ShowLoading(context, "", "", true);
        }
    }

    public CallbackHandler(Context context, boolean showLoading) {
        this.context = context;
        this.showLoading = this.showError = showLoading;
        if (showLoading)
        {
            //MessageBox.ShowLoading(context, "", "", true);
        }
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {


        //MessageBox.HideLoading(context);

        if (response.isSuccessful() && response.body() != null)
        {
            retrycount = 0;
            onSuccess(response);
        } else
        {
            APIErrorResult apiErrorResult = null;
            try
            {
                apiErrorResult = new Gson().fromJson(response.errorBody().string(), APIErrorResult.class);
                apiErrorResult.setCode(response.code());

            } catch (Exception e)
            {
                e.printStackTrace();
            }
            if (apiErrorResult == null)
            {

                apiErrorResult = new APIErrorResult();
                apiErrorResult.setCode(-1);
                apiErrorResult.setMessage(new JsonPrimitive(context.getResources().getString(R.string.failed_server)));
                if (retrycount < 3)
                {
                    retrycount++;
                    call.clone().enqueue(this);
                    return;
                } else
                {
                    retrycount = 0;
                }
            }
            if (showError)
            {
                //MessageBox.Show(context, context.getResources().getString(R.string.error), apiErrorResult.getMessage(), null, MessageBox.MessageBoxIcon.Error);
            }
            onFailed(apiErrorResult);

        }


    }


    @Override
    public void onFailure(Call<T> call, Throwable t) {

        Log.i("TestApiListBid", "onFailed");

    }


    public abstract void onSuccess(Response<T> response);

    public abstract void onFailed(APIErrorResult errorResult);
}
