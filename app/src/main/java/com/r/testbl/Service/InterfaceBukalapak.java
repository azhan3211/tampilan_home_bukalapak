package com.r.testbl.Service;

import com.r.testbl.Banners.BannersApi;
import com.r.testbl.Kategori.KategoriApi;
import com.r.testbl.Popular.PopularApi;
import com.r.testbl.Promo.PromoApi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Unknown on 3/8/2018.
 */

public interface InterfaceBukalapak {
    @GET("flash_banners.json")
    Call<BannersApi> getImages();

    @GET("categories.json")
    Call<KategoriApi> getKategoris();

    @GET("products/promo_banners.json?version=2")
    Call<PromoApi> getPromos();

    @GET("products/populars_v2.json")
    Call<PopularApi> getPopular();
}
