package com.qingmei2.sample_androidtest.a_espresso.a06_async_dialog_fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;

import java.lang.ref.WeakReference;

public class LoadingDialogFragment extends DialogFragment {
  public static final String TAG = "Loading";

  private static final long DELAY = DateUtils.SECOND_IN_MILLIS * 3;

  private final LoadingHandler handler;

  public LoadingDialogFragment() {
    this.handler = new LoadingHandler(this);
  }

  public boolean isDone = false;

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    handler.sendEmptyMessageDelayed(LoadingHandler.MSG_DISMISS, DELAY);

    return new AlertDialog.Builder(getActivity())
        .setTitle("loading")
        .setMessage("wait")
        .create();
  }

  @Override
  public void onDestroyView() {
    handler.removeMessages(LoadingHandler.MSG_DISMISS);
    super.onDestroyView();
  }

  private static class LoadingHandler extends Handler {
    private static final int MSG_DISMISS = 0;
    private final WeakReference<DialogFragment> ref;

    public LoadingHandler(DialogFragment fragment) {
      ref = new WeakReference<>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
      DialogFragment fragment = ref.get();
      if (fragment != null) {
        fragment.dismiss();
        Activity activity = fragment.getActivity();
        if (activity instanceof A06AsyncActivity2) {
          ((A06AsyncActivity2) activity).onLoadingFinished();
        }
      }
    }
  }
}