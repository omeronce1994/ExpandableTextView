package expandabletv.omerco.com.expandabletv;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Creation Date: 13/11/2018
 * Created By: Omer Cohen (omeronce1994)
 * Expandable TextView that expanding by clicking on text
 */

public class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {

    public static final String DEFUALT_EXPANDING_TEXT = "more...";
    public static final int DEFAULT_EXPANDING_TEXT_COLOR= 0xff9e9e9e;
    public static final int DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR= 0x00000000;

    private String expandingText;
    private ColorStateList expandingTextColor;
    private boolean underLineExpandingText;
    private SpannableString fullText;
    private int tvMaxLines = 0;

    public ExpandableTextView(Context context) {
        super(context);
        expandingText = DEFUALT_EXPANDING_TEXT;
        expandingTextColor = ColorStateList.valueOf(DEFAULT_EXPANDING_TEXT_COLOR);
        underLineExpandingText = false;

        tvMaxLines = getMaxLines();
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(ColorStateList.valueOf(DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR).getDefaultColor());
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ExpandableTextView,
                0, 0);
        try {
            underLineExpandingText = a.getBoolean(R.styleable.ExpandableTextView_setUnderLine, false);
            expandingText = a.getString(R.styleable.ExpandableTextView_expandedText);
            if(TextUtils.isEmpty(expandingText)){
                expandingText = DEFUALT_EXPANDING_TEXT;
            }
            expandingTextColor = a.getColorStateList(R.styleable.ExpandableTextView_expandedTextColor);
            if(expandingTextColor == null){
                expandingTextColor = ColorStateList.valueOf(DEFAULT_EXPANDING_TEXT_COLOR);
            }
        } finally {
            a.recycle();
        }
        tvMaxLines = getMaxLines();
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(ColorStateList.valueOf(DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR).getDefaultColor());
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ExpandableTextView,
                0, 0);
        try {
            underLineExpandingText = a.getBoolean(R.styleable.ExpandableTextView_setUnderLine, false);
            expandingText = a.getString(R.styleable.ExpandableTextView_expandedText);
            if(TextUtils.isEmpty(expandingText)){
                expandingText = DEFUALT_EXPANDING_TEXT;
            }
            expandingTextColor = a.getColorStateList(R.styleable.ExpandableTextView_expandedTextColor);
            if(expandingTextColor == null){
                expandingTextColor = ColorStateList.valueOf(DEFAULT_EXPANDING_TEXT_COLOR);
            }
        } finally {
            a.recycle();
        }
        tvMaxLines = getMaxLines();
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(ColorStateList.valueOf(DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR).getDefaultColor());
    }


    /**
     * @return Integer value of expanding text
     */
    public int getExpandingTextColor() {
        return expandingTextColor.getDefaultColor();
    }

    /**
     * @param expandingTextColor set expanding text color
     */
    public void setExpandingTextColor(int expandingTextColor) {
        this.expandingTextColor = ColorStateList.valueOf(expandingTextColor);
        if(getLineCount()>0){
            requestLayout();
            invalidate();
        }
    }

    /**
    * @return boolean if expanding text is underlined
     */
    public boolean isUnderLineExpandingText() {
        return underLineExpandingText;
    }

    /**
     *
     * @param underLineExpandingText
     */
    public void setUnderLineExpandingText(boolean underLineExpandingText) {
        this.underLineExpandingText = underLineExpandingText;
        if(getLineCount()>0){
            requestLayout();
            invalidate();
        }
    }

    /**
     *
     * @return expandingText
     */
    public String getExpandingText() {
        return expandingText;
    }

    /**
     *
     * @param expandingText
     */
    public void setExpandingText(String expandingText) {
        this.expandingText = expandingText;
        if(getLineCount()>0){
            requestLayout();
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(getLineCount()>getMaxLines()){
            fullText = new SpannableString(getText());
            int secondLineStart = getLayout().getLineStart(1);
            int secondLineEnd = getLayout().getLineEnd(1);
            String moreStr = expandingText;
            StringBuilder shortStringBuilder = new StringBuilder(getText().toString().substring(0,getLayout().getLineEnd(0)));
            if(getText().toString().substring(secondLineStart,secondLineEnd).contains("\n")){
                String secondLineString = getText().toString().substring(secondLineStart,secondLineEnd).replace("\n"," "+moreStr);
                shortStringBuilder.append(secondLineString);
            } else {
                String secondLineString="";
                if(secondLineStart<secondLineEnd-moreStr.length()-1) {
                    secondLineString = getText().toString().substring(secondLineStart, secondLineEnd - moreStr.length()-1);
                }
                else {
                    int lineLength = secondLineEnd-secondLineStart;
                    secondLineString = getText().toString().substring(secondLineStart, secondLineEnd -lineLength+1);
                }
                secondLineString =secondLineString.concat(" "+moreStr);
                shortStringBuilder.append(secondLineString);
            }
            SpannableString spannableString = new SpannableString(shortStringBuilder.toString());
            StringBuilder spannedStringBuilder = new StringBuilder(spannableString);
            int startSpan = spannedStringBuilder.toString().length() - moreStr.length();
            int endSpan = startSpan + moreStr.length();
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    setTextFromSuper(fullText, BufferType.SPANNABLE);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(expandingTextColor.getDefaultColor());
                    ds.setUnderlineText(underLineExpandingText);
                }
            },startSpan,endSpan,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            super.setMaxLines(Integer.MAX_VALUE);
            super.setText(spannableString, BufferType.SPANNABLE);
        }

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(tvMaxLines!=0) {
            super.setMaxLines(tvMaxLines);
        }
        super.setText(text, type);
    }

    @Override
    public void setMaxLines(int maxLines) {
        tvMaxLines = maxLines;
        super.setMaxLines(maxLines);
    }

    private void setTextFromSuper(CharSequence text,BufferType type){
        super.setText(text,type);
    }
}

