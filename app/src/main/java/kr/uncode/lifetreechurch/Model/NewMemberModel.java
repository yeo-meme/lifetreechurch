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

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

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
