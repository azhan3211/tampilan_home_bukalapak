package com.r.testbl.Popular;

import java.util.List;

/**
 * Created by Unknown on 3/9/2018.
 */

public class PopularApi {
    private List<Populars> populars;
    public List<Populars> getPopulars() { return populars;}
    public class Populars{
        private String title;
        public String getTitle() {return title;}

        private List<Products> products;
        public List<Products> getProducts() { return products;}

        public class Products {
            private String name;
            private String price;
            public String getName() { return name;}
            public String getPrice() { return price;}

            private Rating rating;
            public Rating getRating() { return rating;}

            public class Rating {
                private String average_rate;
                private String user_count;
                public String getAverage_rating() { return average_rate;}
                public String getUser_count() { return user_count;}
            }

            private List<String> small_images;
            public List<String> getSmall_images() {return small_images;}
        }
    }
}
