package com.example.xo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.xo.databinding.ActivityContactUsBinding;

public class ContactUs extends AppCompatActivity {

    Uri facebookUri, PhoneUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactUsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);
        //this line for hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookUri = Uri.parse("https://www.facebook.com/AhmedM.AbdElmohsen.pro");
                Intent in = new Intent(Intent.ACTION_VIEW, facebookUri);
                startActivity(in);
            }
        });

        binding.btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneUri = Uri.parse("tel:+201066507721");
                Intent Phone = new Intent(Intent.ACTION_DIAL);
                Phone.setData(PhoneUri);
                startActivity(Phone);
            }
        });

        binding.btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", "ahmedmohamedabdelmohsen96@yahoo.com", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        binding.btnBackc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
