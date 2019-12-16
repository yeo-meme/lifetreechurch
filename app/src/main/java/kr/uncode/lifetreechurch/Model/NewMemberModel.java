package kr.uncode.lifetreechurch.Model;

import java.util.List;

public class NewMemberModel {

    public List<Data> data;

    @Override
    public String toString() {
        return "NewMemberModel{" +
                "data=" + data +
                '}';
    }

    public class Data {
        public String _id;
        public String title;
        public String imgurl;

        @Override
        public String toString() {
            return "Data{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", imgurl='" + imgurl + '\'' +
                    '}';
        }
    }
}
