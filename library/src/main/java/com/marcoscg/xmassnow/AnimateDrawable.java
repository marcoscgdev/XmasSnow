package com.marcoscg.xmassnow;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;

class AnimateDrawable extends ProxyDrawable {

    private Animation mAnimation;
    private Transformation mTransformation = new Transformation();

    AnimateDrawable(Drawable target) {
        super(target);
    }

    AnimateDrawable(Drawable target, Animation animation) {
        super(target);
        mAnimation = animation;
    }

    Animation getAnimation() {
        return mAnimation;
    }

    void setAnimation(Animation anim) {
        mAnimation = anim;
    }

    boolean hasStarted() {
        return mAnimation != null && mAnimation.hasStarted();
    }

    boolean hasEnded() {
        return mAnimation == null || mAnimation.hasEnded();
    }

    @Override
    public void draw(Canvas canvas) {
        Drawable dr = getProxy();
        if (dr != null) {
            int sc = canvas.save();
            Animation anim = mAnimation;
            if (anim != null) {
                anim.getTransformation(
                        AnimationUtils.currentAnimationTimeMillis(),
                        mTransformation);
                canvas.concat(mTransformation.getMatrix());
            }
            dr.draw(canvas);
            canvas.restoreToCount(sc);
        }
    }
}