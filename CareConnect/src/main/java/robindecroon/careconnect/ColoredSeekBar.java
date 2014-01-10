package robindecroon.careconnect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.SeekBar;

/**
* Created by robindecroon on 08/01/14.
*/
public class ColoredSeekBar extends SeekBar {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Bitmap thumbImage = BitmapFactory.decodeResource(getResources(), R.drawable.seek_thumb_normal);
    private final float thumbWidth = thumbImage.getWidth();
    private final float thumbHalfWidth = 0.5f * thumbWidth;
    private final float thumbHalfHeight = 0.5f * thumbImage.getHeight();
    private final float lineHeight = 0.3f * thumbHalfHeight;
    private final float padding = thumbHalfWidth;

    public ColoredSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ColoredSeekBar(Context context) {
        super(context);
    }

    public ColoredSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw seek bar background line
        final RectF rect = new RectF(padding, 0.5f * (getHeight() - lineHeight), getWidth() - padding, 0.5f * (getHeight() + lineHeight));

        float width = getResources().getDimension(R.dimen.seekbarwidth);
        Resources resources = getResources();
        LinearGradient test = new LinearGradient(0.f, 0.f,width, 0.0f,
                new int[] {resources.getColor(android.R.color.holo_blue_light), resources.getColor(android.R.color.holo_green_light), resources.getColor(android.R.color.holo_red_light)},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(new float[] { 10, 10, 10, 10, 10, 10, 10, 10}, null, null));
        shape.getPaint().setShader(test);

        paint.setShader(test);

        canvas.drawRect(rect, paint);
    }

}
