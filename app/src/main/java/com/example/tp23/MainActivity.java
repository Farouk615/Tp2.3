package com.example.tp23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private EditText webEdit;
private EditText locationEdit;
    private EditText mShareTextEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webEdit = findViewById(R.id.Website);
        locationEdit = findViewById(R.id.Location);
        mShareTextEditText = findViewById(R.id.Text);
    }

    public void openWebsite(View view) {
        String webEditText = webEdit.getText().toString();
        Uri webpage = Uri.parse(webEditText);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        if(intent.resolveActivity(getPackageManager())!=null){ // verification de la demande ( existe t-il au moins une activity qui peut gerer cette demande
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void openLocation(View view) {
        String locText = locationEdit.getText().toString();
        Uri locpage = Uri.parse("geo:0,0?q="+locText);
        Intent intent = new Intent(Intent.ACTION_VIEW,locpage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.ChoosterTitle)
                .setText(txt)
                .startChooser();
    }
}