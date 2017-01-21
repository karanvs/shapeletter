package com.veer.shapeletter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class ShapeLetter extends View {


    private static int SHAPE_OVAL=1;
    private static int SHAPE_RECT=2;
    private static int DEFAULT_LETTER_COLOR = Color.WHITE;
    private static int DEFAULT_SHAPE_COLOR = Color.CYAN;
    private static final int DEFAULT_VIEW_SIZE = 96;
    private static float DEFAULT_LETTER_SIZE = 25f;
    private static String DEFAULT_LETTER = "A";
    private static final int DEFAULT_SHAPE=SHAPE_OVAL;

    private int mTitleColor = DEFAULT_LETTER_COLOR;
    private int mBackgroundColor = DEFAULT_SHAPE_COLOR;
    private String mTitleText = DEFAULT_LETTER;
    private float mTitleSize = DEFAULT_LETTER_SIZE;
    private int mShape=DEFAULT_SHAPE;

    private TextPaint mTitleTextPaint;
    private Paint mBackgroundPaint;
    private RectF mInnerRectF;
    private int mViewSize;

    private Typeface mFont = Typeface.defaultFromStyle(Typeface.NORMAL);

    public ShapeLetter(Context context) {
        super(context);
        init(null, 0);
    }

    public ShapeLetter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ShapeLetter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ShapeLetter, defStyle, 0);

        if(a.hasValue(R.styleable.ShapeLetter_letter)){
            mTitleText = a.getString(R.styleable.ShapeLetter_letter);
        }

        mShape = a.getInteger(R.styleable.ShapeLetter_shape,DEFAULT_SHAPE);


        mTitleColor = a.getColor(R.styleable.ShapeLetter_letter_color, DEFAULT_LETTER_COLOR);
        mBackgroundColor = a.getColor(R.styleable.ShapeLetter_shape_color, DEFAULT_SHAPE_COLOR);
        mTitleSize = a.getDimension(R.styleable.ShapeLetter_letter_size, DEFAULT_LETTER_SIZE);
        a.recycle();

        //Title TextPaint
        mTitleTextPaint = new TextPaint();
        mTitleTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTitleTextPaint.setTypeface(mFont);
        mTitleTextPaint.setTextAlign(Paint.Align.CENTER);
        mTitleTextPaint.setLinearText(true);
        mTitleTextPaint.setColor(mTitleColor);
        mTitleTextPaint.setTextSize(mTitleSize);

        //Background Paint
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mBackgroundColor);

        mInnerRectF = new RectF();
    }

    private void invalidateTextPaints(){
        mTitleTextPaint.setTypeface(mFont);
        mTitleTextPaint.setTextSize(mTitleSize);
    }

    private void invalidatePaints(){
        mBackgroundPaint.setColor(mBackgroundColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec);
        int height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec);
        mViewSize = Math.min(width, height);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mInnerRectF.set(0, 0, mViewSize, mViewSize);
        mInnerRectF.offset((getWidth() - mViewSize) / 2, (getHeight() - mViewSize) / 2);

        float centerX = mInnerRectF.centerX();
        float centerY = mInnerRectF.centerY();

        int xPos = (int) centerX;
        int yPos = (int) (centerY - (mTitleTextPaint.descent() + mTitleTextPaint.ascent()) / 2);
        if(mShape==SHAPE_OVAL)
        {
            canvas.drawOval(mInnerRectF, mBackgroundPaint);
        }
        else if(mShape==SHAPE_RECT){
            canvas.drawRect(mInnerRectF, mBackgroundPaint);
        }
        canvas.drawText(mTitleText,
                xPos,
                yPos,
                mTitleTextPaint);
    }

    /**
     * Gets the title string attribute value.
     * @return The title string attribute value.
     */
    public String getTitleText() {
        return mTitleText;
    }

    /**
     * Sets the view's title string attribute value.
     * @param title The example string attribute value to use.
     */
    public void setTitleText(String title) {
        mTitleText = title;
        invalidate();
    }

    /**
     * Gets the background color attribute value.
     * @return The background color attribute value.
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Sets the view's background color attribute value.
     * @param backgroundColor The background color attribute value to use.
     */
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        invalidatePaints();
    }

    /**
     * Gets the title size dimension attribute value.
     * @return The title size dimension attribute value.
     */
    public float getTitleSize() {
        return mTitleSize;
    }

    /**
     * Sets the view's title size dimension attribute value.
     * @param titleSize The title size dimension attribute value to use.
     */
    public void setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        invalidateTextPaints();
    }

    /**
     * Sets the view's title typeface.
     * @param font The typeface to be used for the text.
     */
    public void setTextTypeface(Typeface font){
        this.mFont = font;
        invalidateTextPaints();
    }
}
