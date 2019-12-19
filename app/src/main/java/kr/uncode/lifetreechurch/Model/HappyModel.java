package kr.uncode.lifetreechurch.Model;

import java.util.List;

/**
 * Created by jinu@timehub.kr on 2019-12-19
 */
public class HappyModel {

    public List<Data> data;

    @Override
    public String toString() {
        return "HappyModel{" +
                "data=" + data +
                '}';
    }

    public class Data {
        public String _id;
        public String title;
        public String content;
        public String date;

        @Override
        public String toString() {
            return "Data{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }
}
