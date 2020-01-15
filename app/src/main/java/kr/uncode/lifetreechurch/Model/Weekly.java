package kr.uncode.lifetreechurch.Model;

import java.util.List;

public class Weekly {
    boolean success = false;
    public List<Data> data;

    public class Data {
        public String _id;
        public String title;
        public String date;
        public List<String> imgurls;


    }
}
