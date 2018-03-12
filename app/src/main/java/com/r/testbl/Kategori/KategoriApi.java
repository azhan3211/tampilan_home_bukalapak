package com.r.testbl.Kategori;

import java.util.List;

/**
 * Created by Unknown on 3/9/2018.
 */

public class KategoriApi {

    private List<Categories> categories;
    public List<Categories> getCategories() { return categories;}
    public class Categories{
        private String name;
        public String getName() { return name;}
    }
}
