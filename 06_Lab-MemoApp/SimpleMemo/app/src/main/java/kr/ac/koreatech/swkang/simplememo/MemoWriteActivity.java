package kr.ac.koreatech.swkang.simplememo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by admin on 2018-02-13.
 */

public class MemoWriteActivity extends AppCompatActivity {

    EditText title;
    EditText content;
    TextFileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        fileManager = new TextFileManager(getApplicationContext());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.writeBtn:
                // 1. EditText에 작성된 제목과 내용을 읽어와야
                String titleStr = title.getText().toString();
                String contentStr = content.getText().toString();

                // 2. 제목을 이름으로 하는 파일 생성, 파일에다가 내용을 write
                // TextFileManager의 메소드 콜 하는 걸로
                fileManager.save(titleStr, contentStr);

                // 4. Toast 메시지 표시
                Toast.makeText(getApplicationContext(), "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();

                // 5. 액티비티 종료
                finish();

            case R.id.cancelBtn:
                // EditText 작성된 제목, 내용 초기화 --> EditText 값을 빈문자열로 변경
                title.setText("");
                content.setText("");
        }
    }
}
