package kr.uncode.lifetreechurch.fm_video;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.List;

import kr.co.prnd.YouTubePlayerView;
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
    private YouTubePlayer.OnInitializedListener listener;
    private YoutubeRecyclerAdapter youtubeRecyclerAdapter;
    private static String YOUTUBE = "YOUTUBE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube);

        Intent intent = getIntent();
        String videoId = intent.getExtras().getString(YOUTUBE);

        MLog.d("youtube player :" +videoId);


      binding.youtubePlayerView.play(videoId, new YouTubePlayerView.OnInitializedListener() {
          @Override
          public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
              MLog.d("youtube seccess");
          }

          @Override
          public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

          }
      });
    }
}
