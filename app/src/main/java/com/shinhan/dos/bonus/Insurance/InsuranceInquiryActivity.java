package com.shinhan.dos.bonus.Insurance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.QuestionActivity;
import com.shinhan.dos.bonus.R;
import com.shinhan.dos.bonus.Stock.ResultDataBody;
import com.shinhan.dos.bonus.Stock.StockAdapter;
import com.shinhan.dos.bonus.Stock.StockData;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsuranceInquiryActivity extends AppCompatActivity {

    private RecyclerView insurancekRecyclerview;
    private InsuranceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<InsuranceData> insuranceData = new ArrayList<>();

    ImageView question_btn;
    private TextView tv_name;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_inquiry);
        insurancekRecyclerview = (RecyclerView) findViewById(R.id.recyclerview_insurance);

        question_btn =(ImageView)findViewById(R.id.question_img);
        question_btn.setOnClickListener(question);
        tv_name = findViewById(R.id.stock_username_txt);

        //레이아웃 매니저 설정
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        insurancekRecyclerview.setLayoutManager(linearLayoutManager);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        String hpno = pref.getString("hpno", "");
        if (!"".equals(hpno) && null != hpno) {
            Map params = new LinkedHashMap<>();
            params.put("hpno", hpno);
            DataResult dataResult = new DataResultImpl();
            dataResult.getLifeUsage(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
                    if(response.isSuccessful()){

                        Gson gson = new Gson();
                        ResultInsuranceData result = gson.fromJson(response.body().get("dataBody"), ResultInsuranceData.class);

                        result.getInsuranceList();
                        insuranceData.addAll(result.insuranceList);
                        adapter = new InsuranceAdapter(insuranceData, clickEvent);
                        insurancekRecyclerview.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                        tv_name.setText(result.getCustomerName());

                    }

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("STOCKJSON", "FAIL....");

                }
            }, params);
        }




        adapter = new InsuranceAdapter(insuranceData, clickEvent);
        insurancekRecyclerview.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            final int itemPosition = insurancekRecyclerview.getChildPosition(v);
            //Toast.makeText(getContext(), itemPosition + "번 리스트 클릭!!", Toast.LENGTH_SHORT).show();


        }
    };
    public View.OnClickListener question = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
            intent.putExtra("to","보험");
            startActivity(intent);

        }
    };

}