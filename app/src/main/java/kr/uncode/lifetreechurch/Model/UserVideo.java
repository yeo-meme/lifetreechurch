package kr.uncode.lifetreechurch.Model;

import io.realm.RealmObject;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public class UserVideo extends RealmObject {
    private String videoId;

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
                '}';
    }
}
