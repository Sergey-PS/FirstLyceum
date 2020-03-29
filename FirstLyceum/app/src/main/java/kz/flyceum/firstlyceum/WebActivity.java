package kz.flyceum.firstlyceum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SAVED_TEXT = "Saved_text";
    Button mSave;
    Button mLoad;

    EditText mText;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mSave = findViewById(R.id.btnSave);
        mSave.setOnClickListener(this);
        mLoad = findViewById(R.id.btnLoad);
        mLoad.setOnClickListener(this);

        mText = findViewById(R.id.editText);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                save();
                break;
            case R.id.btnLoad:
                load();
        }
    }

    private void load() {
        sPref = getPreferences(MODE_PRIVATE);
        String string = sPref.getString(SAVED_TEXT, "");
        mText.setText(string);
        Toast.makeText(WebActivity.this, "Text Loaded",Toast.LENGTH_LONG);
    }

    private void save() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, mText.getText().toString());
        ed.apply();
        Toast.makeText(WebActivity.this, "Text Saved", Toast.LENGTH_LONG);
    }
}
