package kz.flyceum.firstlyceum;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Annotation;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView mResult;
    TextView mAnime;
    Button mButAdd;
    Button mButSub;
    Button mButMul;
    Button mButDel;
    Button mNewActive;
    EditText mTextOne;
    EditText mTextTwo;

    final int MENU_ALPHA_ID = 1;
    final int MENU_SCALE_ID = 2;
    final int MENU_TRANSLATE_ID = 3;
    final int MENU_ROTATE_ID = 4;
    final int MENU_COMBO_ID = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mResult = findViewById(R.id.textResult);
    mAnime = findViewById(R.id.textAnim);

    mButAdd = findViewById(R.id.butAdd);
    mButSub = findViewById(R.id.butSub);
    mButMul = findViewById(R.id.butMul);
    mButDel = findViewById(R.id.butDel);
    mNewActive = findViewById(R.id.newActiv);

    mTextOne = findViewById(R.id.editOne);
    mTextTwo = findViewById(R.id.editTwo);


    registerForContextMenu(mAnime);

    mButAdd.setOnClickListener(this);
    mButSub.setOnClickListener(this);
    mButMul.setOnClickListener(this);
    mButDel.setOnClickListener(this);
    mNewActive.setOnClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.textAnim) {
            menu.add(0, MENU_ALPHA_ID, 0, "Alpha");
            menu.add(0, MENU_SCALE_ID, 0, "Scale");
            menu.add(0, MENU_TRANSLATE_ID, 0, "Translate");
            menu.add(0, MENU_ROTATE_ID, 0, "Rotate");
            menu.add(0, MENU_COMBO_ID, 0, "Combo");

        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation anim = null;
        switch (item.getItemId()) {
            case MENU_ALPHA_ID:
                anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
                break;
            case MENU_SCALE_ID:
                anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case MENU_TRANSLATE_ID:
                anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
                break;
            case MENU_ROTATE_ID:
                anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
            case MENU_COMBO_ID:
                anim = AnimationUtils.loadAnimation(this, R.anim.mycombo);
                break;
        }
        mAnime.startAnimation(anim);
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_reset: {
                mTextOne.setText("");
                mTextTwo.setText("");
                mResult.setText("");
                break;
            }
            case R.id.action_quit: {
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        float num1;
        float num2;
        String oper = ".";
        float result = 0;

        if (v.getId() == R.id.newActiv) {
            Intent intent = new Intent(this, ActivityTwo.class);
            startActivity(intent);
        }
        if (TextUtils.isEmpty(mTextOne.getText().toString()) || TextUtils.isEmpty(mTextTwo.getText().toString())) {
            Toast.makeText(MainActivity.this, " Поля пустые", Toast.LENGTH_SHORT);
            return;
        }

        num1 = Float.parseFloat(mTextOne.getText().toString());
        num2 = Float.parseFloat(mTextTwo.getText().toString());

        switch (v.getId()) {
            case R.id.butAdd:
                oper = "+";
                result = num1 + num2;
                break;
            case R.id.butSub:
                oper = "-";
                result = num1 - num2;
                break;
            case R.id.butMul:
                oper = "*";
                result = num1 * num2;
                break;
            case R.id.butDel:
                if (num2 == 0) {
                    mResult.setText("Деление на 0");
                    return;
                }
                oper = "/";
                result = num1 / num2;
                break;
            default:
        }

        mResult.setText(num1 + " " + oper + " " + num2 + " = " + result);

    }
}
