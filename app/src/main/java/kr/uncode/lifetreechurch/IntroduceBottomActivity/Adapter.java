package kr.uncode.lifetreechurch.IntroduceBottomActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import kr.uncode.lifetreechurch.R;

/**
 * Created by jinu@timehub.kr on 2019-12-20
 */
public class Adapter extends PagerAdapter {


    private int[] images = {R.drawable.intro, R.drawable.introt};

    private LayoutInflater inflater;
    private Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View v = inflater.inflate(R.layout.imageslider,container,false);
       ImageView imageView = v.findViewById(R.id.imageViewSlider);
       imageView.setImageResource(images[position]);
       container.addView(v);
        return v;
    }
}
