package com.r.testbl.Banners;

import java.util.List;

/**
 * Created by Unknown on 3/8/2018.
 */

public class BannersApi {
    private List<Banners> banners;
    public List<Banners> getBanners() { return banners; }
    public class Banners{
        private String image;
        public String getImage(){return image;}
    }
}
