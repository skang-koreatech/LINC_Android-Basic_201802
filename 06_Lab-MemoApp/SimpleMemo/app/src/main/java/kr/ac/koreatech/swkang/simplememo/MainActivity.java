package kr.ac.koreatech.swkang.simplememo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> m_Adapter;
    ListView m_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> values = new ArrayList<>();

        // Android에서 제공하는 String 문자열 하나를 출력하는 layout으로 어댑터 생성
        m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        // Xml에서 추가한 ListView의 객체
        m_ListView = (ListView) findViewById(R.id.list);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트를 처리할 리스너 설정
        m_ListView.setOnItemClickListener(onClickListItem);

    }

    @Override
    protected void onResume() {
        super.onResume();

        addMemoList();
    }

    private void addMemoList() {
        m_Adapter.clear();

        // 저장공간에 있는 메모 파일의 이름을 읽어와서 m_Adapter에 add를 해준다
        String[] memos = fileList();
        for(int i = 0; i < memos.length; i++) {
            m_Adapter.add(memos[i]);
        }
    }

    // 아이템 터치 이벤트 리스너 구현
    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 이벤트 발생 시 해당 아이템 위치를 텍스트로 출력
            //Toast.makeText(getApplicationContext(), m_Adapter.getItem(position), Toast.LENGTH_SHORT).show();

            // MemoReadActivity를 실행시킨다
            // Intent 객체를 만든다. startActivity() 메소드 콜
            Intent intent = new Intent(getApplicationContext(), MemoReadActivity.class);
            // Intent 객체에 전달하고자 하는 데이터를 담는다
            intent.putExtra("filename", m_Adapter.getItem(position));
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.write:
                //Toast.makeText(getApplicationContext(), "Memo write", Toast.LENGTH_SHORT).show();
                // MemoWriteActivity를 시작시킨다
                Intent intent = new Intent(this, MemoWriteActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
