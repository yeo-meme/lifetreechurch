package kr.uncode.lifetreechurch.Model;

import java.util.List;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public class UnCodeVideoModel {

    public List<Data> data;

    @Override
    public String toString() {
        return "UnCodeVideoModel{" +
                "data=" + data +
                '}';
    }

    public class  Data {
        public String _id;
        public String title;
        public String description;
        public String videoId;
        public String thumbnail;
        public String publishedAt;

        @Override
        public String toString() {
            return "Data{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", videoId='" + videoId + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    '}';
        }
    }
}
