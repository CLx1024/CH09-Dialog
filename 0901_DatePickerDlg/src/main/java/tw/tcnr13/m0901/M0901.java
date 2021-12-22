package tw.tcnr13.m0901;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class M0901 extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnTimePicDlg;
    private Button mBtnDatePicDlg;
    private TextView mTxtResult;
    private String u_date ="";  // 給初始值 (有東西 裡面沒有東西)(不設初始值會顯示null)
    private String u_time ="";  // 給初始值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0901);
        setupViewComponent();
    }

    private void setupViewComponent() {
        mBtnTimePicDlg = (Button) findViewById(R.id.m0901_b001);
        mBtnDatePicDlg = (Button) findViewById(R.id.m0901_b002);
        mTxtResult = (TextView) findViewById(R.id.m0901_t001);

        mBtnTimePicDlg.setOnClickListener(this);
        mBtnDatePicDlg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mTxtResult.setText("");  // 監聽一進來都先把output先清為空白或零 (因為老師喜歡用setText+ 不清的話會越來越長)
        Calendar now = Calendar.getInstance();

        switch(v.getId()){
            case R.id.m0901_b001:
                DatePickerDialog datePicDlg = new DatePickerDialog(  // 對話盒
                        this,
                        datePicDlgOnDateSelLis,  // 自定義的監聽方法
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));

                datePicDlg.setTitle(getString(R.string.m0901_datetitle));  // title
                datePicDlg.setMessage(getString(R.string.m0901_datemessage));
                datePicDlg.setIcon(android.R.drawable.star_big_on);
                // 彈出視窗的禁用返回鍵 (忘記寫這句話的話會有漏洞 可能可被從其他地方跳回去其他頁)
                datePicDlg.setCancelable(false);
                datePicDlg.show();
                break;

                case R.id.m0901_b002:
                    TimePickerDialog timePicDlg = new TimePickerDialog(  // 對話盒
                            this,
                            timePicDlgOnDateSelLis,  // 自定義的監聽方法
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            false);  // is24HourView

                    timePicDlg.setTitle(getString(R.string.m0901_timetitle));
                    timePicDlg.setMessage(getString(R.string.m0901_timemessage));
                    timePicDlg.setIcon(android.R.drawable.star_big_off);
                    // 彈出視窗的禁用返回鍵 (忘記寫這句話的話會有漏洞 可能可被從其他地方跳回去其他頁)
                    timePicDlg.setCancelable(false);
                    timePicDlg.show();
                    break;

        }



    }

    private DatePickerDialog.OnDateSetListener datePicDlgOnDateSelLis = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            u_date = Integer.toString(year) + getString(R.string.n_yy) +
                    Integer.toString(month + 1) + getString(R.string.n_mm) +
                    Integer.toString(dayOfMonth) + getString(R.string.n_dd);
            mTxtResult.setText(getString(R.string.m0901_datetitle) +
                    u_date + "\n" + u_time);

        }
    };

    private TimePickerDialog.OnTimeSetListener timePicDlgOnDateSelLis = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            u_time = getString(R.string.m0901_timetitle) +
                    Integer.toString(hourOfDay) + getString(R.string.d_hh) +
                    Integer.toString(minute) + getString(R.string.d_mm);
            mTxtResult.setText(getString(R.string.m0901_datetitle) +
                    u_date + "\n" + u_time);

        }
    };

}