package kr.uncode.lifetreechurch.Model;

import io.realm.RealmObject;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public class UserVideo extends RealmObject {
    public String videoId;
    public String title;
    public String image_Url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_Url() {
        return image_Url;
    }

    public void setImage_Url(String image_Url) {
        this.image_Url = image_Url;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "UserVideo{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", image_Url='" + image_Url + '\'' +
                '}';
    }
}
