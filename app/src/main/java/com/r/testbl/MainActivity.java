package com.r.testbl;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.r.testbl.Banners.BannerAdapter;
import com.r.testbl.Banners.BannersApi;
import com.r.testbl.Kategori.KategoriAdapter;
import com.r.testbl.Kategori.KategoriApi;
import com.r.testbl.Popular.PopularAdapter;
import com.r.testbl.Popular.PopularApi;
import com.r.testbl.Popular.PopularData;
import com.r.testbl.Promo.PromoAdapter;
import com.r.testbl.Promo.PromoApi;
import com.r.testbl.Promo.PromoData;
import com.r.testbl.Service.InterfaceBukalapak;
import com.r.testbl.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    BannerAdapter adapter;
    List<String> images;
    ImageView[] imageViews;
    LinearLayout linearLayout;
    RecyclerView kategoriRV, promoRV, popularRV;
    RecyclerView.Adapter kategoriAdapter, promoAdapter, popularAdapter;
    int dotCount;
    int bannerPosition = 0;
    RetrofitService builder;
    Retrofit retrofit;
    InterfaceBukalapak interfaceBukalapak;
    List<String> namaKategori;
    PromoData promo;
    List<PromoData> promos;
    TextView title;
    List<PopularData> popularDatas;
    PopularData popularData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialVariable();
        getBanners();
        getCategories();
        getPromos();
        getPopular();
    }

    private void getPromos() {
        Call<PromoApi> call = interfaceBukalapak.getPromos();
        call.enqueue(new Callback<PromoApi>() {
            @Override
            public void onResponse(Call<PromoApi> call, Response<PromoApi> response) {
                if(response.isSuccessful()){
                    PromoApi result = response.body();
                    for(int i = 0; i < result.getPromo_banners().size(); i++){
                        promo = new PromoData(
                                result.getPromo_banners().get(i).getImage(),
                                result.getPromo_banners().get(i).getDescription()
                        );
                        promos.add(promo);
                    }
                    promoAdapter = new PromoAdapter(MainActivity.this, promos);
                    promoRV.setAdapter(promoAdapter);
                }
            }

            @Override
            public void onFailure(Call<PromoApi> call, Throwable t) {

            }
        });
    }

    private void automaticVP(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(dotCount == bannerPosition){
                    bannerPosition = 0;
                }
                viewPager.setCurrentItem(bannerPosition++, true);
            }
        };

        Timer swiper = new Timer();
        swiper.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000,3000);
    }

    private void dotSetup() {
        dotCount = images.size();
        Log.d("jumlah view pager", String.valueOf(images.size()));
        imageViews = new ImageView[dotCount];
        for(int i = 0; i < dotCount ; i++){
            imageViews[i] = new ImageView(MainActivity.this);
            imageViews[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,0,0);
            linearLayout.addView(imageViews[i], params);
        }
        Log.d("jumlah", String.valueOf(dotCount));
        imageViews[0].setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.active_dot));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bannerPosition = position;
                for(int i = 0; i < dotCount; i++){
                    imageViews[i].setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.non_active_dot));
                }
                imageViews[position].setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getBanners() {
        Call<BannersApi> call = interfaceBukalapak.getImages();
        call.enqueue(new Callback<BannersApi>() {
            @Override
            public void onResponse(Call<BannersApi> call, Response<BannersApi> response) {
                if(response.isSuccessful()){
                    BannersApi result = response.body();
                    for(int i = 0; i < result.getBanners().size(); i++){
                        images.add(result.getBanners().get(i).getImage());
                    }
                    adapter = new BannerAdapter(MainActivity.this, images);
                    viewPager.setAdapter(adapter);

                    dotSetup();
                    automaticVP();
                }
            }

            @Override
            public void onFailure(Call<BannersApi> call, Throwable t) {

            }
        });
    }

    private void getCategories(){
        Call<KategoriApi> call = interfaceBukalapak.getKategoris();
        call.enqueue(new Callback<KategoriApi>() {
            @Override
            public void onResponse(Call<KategoriApi> call, Response<KategoriApi> response) {
                if(response.isSuccessful()){
                    KategoriApi result = response.body();
                    for(int i = 0; i < result.getCategories().size(); i++){
                        namaKategori.add(result.getCategories().get(i).getName());
                    }
                    kategoriAdapter = new KategoriAdapter(MainActivity.this, namaKategori);
                    kategoriRV.setAdapter(kategoriAdapter);
                }
            }

            @Override
            public void onFailure(Call<KategoriApi> call, Throwable t) {

            }
        });
    }

    private void getPopular(){
        Call<PopularApi> call = interfaceBukalapak.getPopular();
        call.enqueue(new Callback<PopularApi>() {
            @Override
            public void onResponse(Call<PopularApi> call, Response<PopularApi> response) {
                if(response.isSuccessful()){
                    PopularApi result = response.body();
                    Log.d("title", result.getPopulars().get(0).getTitle());
                    Log.d("nama barang", result.getPopulars().get(0).getProducts().get(0).getName());
                    Log.d("harga", result.getPopulars().get(0).getProducts().get(0).getPrice());
                    Log.d("review", result.getPopulars().get(0).getProducts().get(0).getRating().getAverage_rating());
                    Log.d("user count", result.getPopulars().get(0).getProducts().get(0).getRating().getUser_count());
                    title.setText(result.getPopulars().get(0).getTitle());
                    String namaBarang;
                    for(int i = 0; i < result.getPopulars().get(0).getProducts().size(); i++){
                        if(result.getPopulars().get(0).getProducts().get(i).getName().length() > 25)
                            namaBarang = result.getPopulars().get(0).getProducts().get(i).getName().substring(0,25)+"...";
                        else
                            namaBarang = result.getPopulars().get(0).getProducts().get(i).getName();
                        popularData = new PopularData(
                                namaBarang,
                                result.getPopulars().get(0).getProducts().get(i).getPrice(),
                                result.getPopulars().get(0).getProducts().get(i).getRating().getAverage_rating(),
                                result.getPopulars().get(0).getProducts().get(i).getRating().getUser_count(),
                                result.getPopulars().get(0).getProducts().get(i).getSmall_images().get(0)
                        );
                        popularDatas.add(popularData);
                    }
                    popularAdapter = new PopularAdapter(MainActivity.this, popularDatas);
                    popularRV.setAdapter(popularAdapter);
                }
            }

            @Override
            public void onFailure(Call<PopularApi> call, Throwable t) {

            }
        });
    }

    private void initialVariable() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        linearLayout = (LinearLayout) findViewById(R.id.dotContainer);
        kategoriRV = (RecyclerView) findViewById(R.id.recycler_view_kategori);
        promoRV = (RecyclerView) findViewById(R.id.recycler_view_promo);
        popularRV = (RecyclerView) findViewById(R.id.recycler_view_popular);
        title = (TextView) findViewById(R.id.title_popular);
        kategoriRV.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false));
        promoRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));
        popularRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));
        images = new ArrayList<>();
        promos = new ArrayList<>();
        popularDatas = new ArrayList<>();
        namaKategori = new ArrayList<>();
        builder = new RetrofitService();
        retrofit = builder.getService();
        interfaceBukalapak = retrofit.create(InterfaceBukalapak.class);;
    }
}
