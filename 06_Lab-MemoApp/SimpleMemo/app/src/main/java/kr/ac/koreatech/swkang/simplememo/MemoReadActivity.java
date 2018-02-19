package kr.ac.koreatech.swkang.simplememo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by admin on 2018-02-19.
 */

public class MemoReadActivity extends AppCompatActivity {

    TextView content;
    TextFileManager fileManager;
    String file;
    private static final int DIALOG_DELETE_MESSAGE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Intent intent = getIntent();
        file = intent.getStringExtra("filename");

        fileManager = new TextFileManager(getApplicationContext());
        content = (TextView)findViewById(R.id.memotext);

        String memo = fileManager.load(file);
        content.setText(memo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete:
                //Toast.makeText(getApplicationContext(), "Memo delete", Toast.LENGTH_SHORT).show();
                // 메모 파일 삭제 확인 대화상자를 시작
                showDialog(DIALOG_DELETE_MESSAGE);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DELETE_MESSAGE:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("삭제 확인 대화 상자")
                        .setMessage("메모 파일을 정말로 삭제하시겠습니까?")
                        //.setPositiveButton("Yes", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 현재 메모 파일을 삭제하고 현재 activity를 종료
                                fileManager.delete(file);
                                finish();
                            }
                        })
                        //.setNegativeButton("No", null);
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                return alert;
        }
        return null;
    }
}
