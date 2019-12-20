package kr.uncode.lifetreechurch.fm_video;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.YoutubeRecyclerAdapter;
import kr.uncode.lifetreechurch.YoutubeVideoModel;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.ActivityYoutubeBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-18
 */
public class YoutubePlayerActivity extends BaseActivity {
    private YoutubeResponse.Items youtubeItems;
    ActivityYoutubeBinding binding;
    private YoutubeRecyclerAdapter youtubeRecyclerAdapter;
    private static String YOUTUBE = "YOUTUBE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube);

        Intent intent = getIntent();
        String videoId = intent.getExtras().getString(YOUTUBE);

        MLog.d("youtube player :" +videoId);


        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(videoId,0);
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                MLog.d("key 뒤로가기");
                finish();
                return true;
        }
        return false;
    }
}
