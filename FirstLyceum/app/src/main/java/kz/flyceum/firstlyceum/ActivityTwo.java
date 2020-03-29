package kz.flyceum.firstlyceum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    Button mContext;
    TextView mTextView;
    TextView mTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        mContext = findViewById(R.id.getContext);
        mContext.setOnClickListener(this);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = simpleDateFormat.format(new Date());

        mTextView = findViewById(R.id.txtTime);
        mTxtName = findViewById(R.id.txtName);

        Intent intent = getIntent();
        mTxtName.setText(intent.getStringExtra("name"));
        mTextView.setText(time);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.getContext) {
            // TODO: 28.03.2020 button

        }
    }
}
