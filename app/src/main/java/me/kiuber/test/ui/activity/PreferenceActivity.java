package me.kiuber.test.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.kiuber.base.utils.PreferenceUtil;
import me.kiuber.base.utils.ToastUtil;
import me.kiuber.test.R;

/**
 * Created 2017/4/28 0028 20:29
 * Author Kiuber
 * Description
 */

public class PreferenceActivity extends AppCompatActivity {

    private EditText mEtKey;
    private EditText mEtValue;
    private TextView mTvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_preference);

        mEtKey = (EditText) findViewById(R.id.et_key);
        mEtValue = (EditText) findViewById(R.id.et_value);
        mTvResult = (TextView) findViewById(R.id.tv_result);
    }

    public void writeTest(View view) {
        String key = mEtKey.getText().toString();
        key = key.equals("") ? "key值" : key;
        String value = mEtValue.getText().toString();
        value = value.equals("") ? "value值" : value;
        PreferenceUtil.get().putOneValue(PreferenceUtil.FILE_NAME_APP_CONFIG, key, value);
    }

    public void readTest(View view) {
        String key = mEtKey.getText().toString();
        key = key.equals("") ? "key值" : key;
        String oneValue = PreferenceUtil.get().getOneValue(PreferenceUtil.FILE_NAME_APP_CONFIG, key);
        mTvResult.setText(oneValue);
    }

    public void readAll(View view) {
        Map<String, ?> all    = PreferenceUtil.get().getAll(PreferenceUtil.FILE_NAME_APP_CONFIG);
        String         result = "";
        assert all != null;
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            result = result + entry.getKey() + " : " + entry.getValue() + "\n";
        }
        mTvResult.setText(result);
    }

    public void readMul(View view) {
        Set<String> keys = new HashSet<>();
        keys.add("1");
        keys.add("2");
        keys.add("3");
        keys.add("4");
        keys.add("5");
        Map<String, Object> multipleValue = PreferenceUtil.get().getMultipleValue(PreferenceUtil.FILE_NAME_APP_CONFIG, keys);

        String result = "";
        assert multipleValue != null;
        for (Map.Entry<String, ?> entry : multipleValue.entrySet()) {
            result = result + entry.getKey() + " : " + entry.getValue() + "\n";
        }
        mTvResult.setText(result);
    }

    public void writeMul(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "String");
        map.put("2", true);
        map.put("3", 10.00000F);
        map.put("4", 521);
        map.put("5", 10000000000L);
        boolean b = PreferenceUtil.get().putMultipleValue(PreferenceUtil.FILE_NAME_APP_CONFIG, map);
    }

    public void clear(View view) {
        boolean clear = PreferenceUtil.get().clear(PreferenceUtil.FILE_NAME_APP_CONFIG);
        ToastUtil.get().showShortToast(clear);
    }
}
