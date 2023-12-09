// Generated by view binder compiler. Do not edit!
package com.example.whatsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.whatsapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySettingsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView backArrow;

  @NonNull
  public final EditText etAbout;

  @NonNull
  public final LinearLayout linear;

  @NonNull
  public final ImageView plus;

  @NonNull
  public final CircleImageView profileImage;

  @NonNull
  public final AppCompatButton savebtn;

  @NonNull
  public final TextView tvabout;

  @NonNull
  public final TextView tvhelp;

  @NonNull
  public final TextView tvinvite;

  @NonNull
  public final TextView tvnotification;

  @NonNull
  public final TextView tvprivacy;

  @NonNull
  public final EditText txtusername;

  private ActivitySettingsBinding(@NonNull RelativeLayout rootView, @NonNull ImageView backArrow,
      @NonNull EditText etAbout, @NonNull LinearLayout linear, @NonNull ImageView plus,
      @NonNull CircleImageView profileImage, @NonNull AppCompatButton savebtn,
      @NonNull TextView tvabout, @NonNull TextView tvhelp, @NonNull TextView tvinvite,
      @NonNull TextView tvnotification, @NonNull TextView tvprivacy,
      @NonNull EditText txtusername) {
    this.rootView = rootView;
    this.backArrow = backArrow;
    this.etAbout = etAbout;
    this.linear = linear;
    this.plus = plus;
    this.profileImage = profileImage;
    this.savebtn = savebtn;
    this.tvabout = tvabout;
    this.tvhelp = tvhelp;
    this.tvinvite = tvinvite;
    this.tvnotification = tvnotification;
    this.tvprivacy = tvprivacy;
    this.txtusername = txtusername;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backArrow;
      ImageView backArrow = ViewBindings.findChildViewById(rootView, id);
      if (backArrow == null) {
        break missingId;
      }

      id = R.id.etAbout;
      EditText etAbout = ViewBindings.findChildViewById(rootView, id);
      if (etAbout == null) {
        break missingId;
      }

      id = R.id.linear;
      LinearLayout linear = ViewBindings.findChildViewById(rootView, id);
      if (linear == null) {
        break missingId;
      }

      id = R.id.plus;
      ImageView plus = ViewBindings.findChildViewById(rootView, id);
      if (plus == null) {
        break missingId;
      }

      id = R.id.profile_image;
      CircleImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.savebtn;
      AppCompatButton savebtn = ViewBindings.findChildViewById(rootView, id);
      if (savebtn == null) {
        break missingId;
      }

      id = R.id.tvabout;
      TextView tvabout = ViewBindings.findChildViewById(rootView, id);
      if (tvabout == null) {
        break missingId;
      }

      id = R.id.tvhelp;
      TextView tvhelp = ViewBindings.findChildViewById(rootView, id);
      if (tvhelp == null) {
        break missingId;
      }

      id = R.id.tvinvite;
      TextView tvinvite = ViewBindings.findChildViewById(rootView, id);
      if (tvinvite == null) {
        break missingId;
      }

      id = R.id.tvnotification;
      TextView tvnotification = ViewBindings.findChildViewById(rootView, id);
      if (tvnotification == null) {
        break missingId;
      }

      id = R.id.tvprivacy;
      TextView tvprivacy = ViewBindings.findChildViewById(rootView, id);
      if (tvprivacy == null) {
        break missingId;
      }

      id = R.id.txtusername;
      EditText txtusername = ViewBindings.findChildViewById(rootView, id);
      if (txtusername == null) {
        break missingId;
      }

      return new ActivitySettingsBinding((RelativeLayout) rootView, backArrow, etAbout, linear,
          plus, profileImage, savebtn, tvabout, tvhelp, tvinvite, tvnotification, tvprivacy,
          txtusername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}