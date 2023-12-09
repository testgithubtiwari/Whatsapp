// Generated by view binder compiler. Do not edit!
package com.example.whatsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.whatsapp.R;
import com.hbb20.CountryCodePicker;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEntrymobileBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btngetOtp;

  @NonNull
  public final CountryCodePicker ccp;

  @NonNull
  public final ImageView imageview;

  @NonNull
  public final EditText phone;

  private ActivityEntrymobileBinding(@NonNull LinearLayout rootView, @NonNull Button btngetOtp,
      @NonNull CountryCodePicker ccp, @NonNull ImageView imageview, @NonNull EditText phone) {
    this.rootView = rootView;
    this.btngetOtp = btngetOtp;
    this.ccp = ccp;
    this.imageview = imageview;
    this.phone = phone;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEntrymobileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEntrymobileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_entrymobile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEntrymobileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btngetOtp;
      Button btngetOtp = ViewBindings.findChildViewById(rootView, id);
      if (btngetOtp == null) {
        break missingId;
      }

      id = R.id.ccp;
      CountryCodePicker ccp = ViewBindings.findChildViewById(rootView, id);
      if (ccp == null) {
        break missingId;
      }

      id = R.id.imageview;
      ImageView imageview = ViewBindings.findChildViewById(rootView, id);
      if (imageview == null) {
        break missingId;
      }

      id = R.id.phone;
      EditText phone = ViewBindings.findChildViewById(rootView, id);
      if (phone == null) {
        break missingId;
      }

      return new ActivityEntrymobileBinding((LinearLayout) rootView, btngetOtp, ccp, imageview,
          phone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
