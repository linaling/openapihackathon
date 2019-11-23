package com.shinhan.dos.bonus.PlusMoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shinhan.dos.bonus.PlusMoney.Fragment.FragCard.CardProductFragment;
import com.shinhan.dos.bonus.PlusMoney.Fragment.FragStock.StockProductFragment;
import com.shinhan.dos.bonus.PlusMoney.Fragment.FragInsurance.insuranceProductFragment;
import com.shinhan.dos.bonus.R;

public class PlusMoneyActivity extends AppCompatActivity {

    TextView product_type_txt;
    TextView plus_money_amount;
    ImageView plus_card_btn;
    ImageView plus_stock_btn;
    ImageView plus_insurance_btn;

    ViewPager vp;
    pagerAdapter vpadapter;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_money);

        vp = (ViewPager) findViewById(R.id.plus_ViewPager);
        product_type_txt = (TextView)findViewById(R.id.product_type_txt);
        plus_money_amount = (TextView)findViewById(R.id.plus_money_amount);
        plus_card_btn = (ImageView) findViewById(R.id.plus_card_btn);
        plus_stock_btn = (ImageView) findViewById(R.id.plus_stock_btn);
        plus_insurance_btn = (ImageView) findViewById(R.id.plus_insurance_btn);
        intent = getIntent();


        plus_money_amount.setText(String.valueOf(intent.getExtras().getInt("plus_card_money"))); /*String형*/


        /**
         * viewpager 잘돌아가게 하기 위함
         */
        vpadapter = new pagerAdapter(getSupportFragmentManager());
        vp.setAdapter(vpadapter);
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        plus_card_btn.setImageResource(R.drawable.icon_card_prdt);
                        plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                        plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);
                        product_type_txt.setText("카드");
                        plus_money_amount.setText(String.valueOf(intent.getExtras().getInt("plus_card_money"))); /*String형*/

                        break;
                    case 1:
                        plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                        plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt);
                        plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);
                        product_type_txt.setText("주식");
                        plus_money_amount.setText("100"); /*String형*/
                        break;
                    case 2:
                        plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                        plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                        plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt);
                        product_type_txt.setText("보험");
                        plus_money_amount.setText(String.valueOf(intent.getExtras().getInt("plus_life_money"))); /*String형*/
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        plus_card_btn.setOnClickListener(movePageListener);
        plus_card_btn.setTag(0);
        plus_stock_btn.setOnClickListener(movePageListener);
        plus_stock_btn.setTag(1);
        plus_insurance_btn.setOnClickListener(movePageListener);
        plus_insurance_btn.setTag(2);

    }

    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            switch (tag) {
                case 0:
                    plus_card_btn.setImageResource(R.drawable.icon_card_prdt);
                    plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                    plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);
                    product_type_txt.setText("카드");
                    plus_money_amount.setText(String.valueOf(intent.getExtras().getInt("plus_card_money"))); /*String형*/
                    break;
                case 1:
                    plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                    plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt);
                    plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt_dis);
                    product_type_txt.setText("주식");
                    plus_money_amount.setText("100"); /*String형*/

                    break;
                case 2:
                    plus_card_btn.setImageResource(R.drawable.icon_card_prdt_dis);
                    plus_stock_btn.setImageResource(R.drawable.icon_stock_prdt_dis);
                    plus_insurance_btn.setImageResource(R.drawable.icon_insurance_prdt);
                    product_type_txt.setText("보험");
                    plus_money_amount.setText(String.valueOf(intent.getExtras().getInt("plus_life_money"))); /*String형*/
                    break;

            }
            vp.setCurrentItem(tag);

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        vpadapter.notifyDataSetChanged();
    }


    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CardProductFragment();
                case 1:
                    return new StockProductFragment();
                case 2:
                    return new insuranceProductFragment();

                default:
                    return null;
            }
        }
        public int getItemPosition(Object item) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}