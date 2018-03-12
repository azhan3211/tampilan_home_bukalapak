package com.r.testbl.Promo;

import java.util.List;

/**
 * Created by Unknown on 3/9/2018.
 */

public class PromoApi {

    private List<Promo_banners> promo_banners;
    public List<Promo_banners> getPromo_banners() { return promo_banners;}
    public class Promo_banners{
        private String image;
        private String description;
        public String getImage() { return image;}
        public String getDescription() { return description; }
    }
}
