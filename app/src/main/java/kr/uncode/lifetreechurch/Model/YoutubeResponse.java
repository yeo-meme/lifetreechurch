package kr.uncode.lifetreechurch.Model;

import java.util.List;

public class YoutubeResponse {
    public List<Items> items;

    @Override
    public String toString() {
        return "YoutubeResponse{" +
                "items=" + items +
                '}';
    }

    public class Items {
        public Id id;
        public SnippetResponse snippet;

        @Override
        public String toString() {
            return "Items{" +
                    "id=" + id +
                    ", snippet=" + snippet +
                    '}';
        }

        public class Id {
            public String videoId;

            @Override
            public String toString() {
                return "Id{" +
                        "videoId='" + videoId + '\'' +
                        '}';
            }
        }
    }
}
