package kr.uncode.lifetreechurch.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

/**
 * Created by jinu@timehub.kr on 2019-12-20
 */
public class CircleIndicator extends LinearLayout {

    private Context mContext;

    private int itemMargin = 10;

    private int animDuration = 250;

    private int mDefaultCircle;
    private int mSelectCircle;

    private ImageView[] imageDot;

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
    }

    public void setItemMargin(int itemMargin) {
        this.itemMargin = itemMargin;
    }

    public CircleIndicator(Context context) {
        super(context);
        mContext = context;
    }


    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context,attrs);
        mContext = context;
    }

    public void createDotPanel(int count, int defaultCircle, int selectCircle) {
        mDefaultCircle = defaultCircle;
        mSelectCircle = selectCircle;

        imageDot = new ImageView[count];

        for (int i = 0; i < count; i++) {

            imageDot[i] = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
            );


            if (itemMargin != 0) {
                params.topMargin = itemMargin;
                params.bottomMargin = itemMargin;
                params.leftMargin = itemMargin;
                params.rightMargin = itemMargin;
                params.gravity = Gravity.CENTER;

                imageDot[i].setLayoutParams(params);
                imageDot[i].setImageResource(defaultCircle);
                imageDot[i].setTag(imageDot[i].getId(), false);
                this.addView(imageDot[i]);
            }
        }

        selectDot(0);
    }

    public void selectDot(int position) {

        for (int i = 0; i < imageDot.length; i++) {
            if (i == position) {
                imageDot[i].setImageResource(mSelectCircle);
                selectScaleAnim(imageDot[i], 1f, 5f);
            } else {

                if ((boolean) imageDot[i].getTag(imageDot[i].getId()) == true) {
                    imageDot[i].setImageResource(mDefaultCircle);
                    defaultScaleAnim(imageDot[i], 1.5f, 1f);
                }
            }
        }
    }

    private void defaultScaleAnim(ImageView imageView, float startScale, float endScale) {

    Animation anim = new ScaleAnimation(
            startScale,endScale,startScale,endScale,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f
    );
    anim.setFillAfter(true);
    anim.setDuration(animDuration);
    imageView.startAnimation(anim);
    imageView.setTag(imageView.getId(),false);
    }

    private void selectScaleAnim(ImageView imageView, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        anim.setFillAfter(true);
        anim.setDuration(animDuration);
        imageView.startAnimation(anim);
        imageView.setTag(imageView.getId(), true);
    }
}
