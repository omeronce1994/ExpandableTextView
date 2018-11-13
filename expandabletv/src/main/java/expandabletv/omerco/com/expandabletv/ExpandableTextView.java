package expandabletv.omerco.com.expandabletv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {

    private static final String DEFUALT_EXPANDING_TEXT = "more...";
    private static final int DEFAULT_EXPANDING_TEXT_COLOR= 0xff9e9e9e;
    private static final int DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR= 0x00000000;

    private String expandingText;
    private int expandingTextColor;
    private int expandingTextHighlightColor;
    private boolean underLineExpandingText;
    private SpannableString fullText;

    public ExpandableTextView(Context context) {
        super(context);
        expandingText = DEFUALT_EXPANDING_TEXT;
        expandingTextColor = DEFAULT_EXPANDING_TEXT_COLOR;
        underLineExpandingText = false;
        expandingTextHighlightColor = DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR;
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        expandingText = DEFUALT_EXPANDING_TEXT;
        expandingTextColor = DEFAULT_EXPANDING_TEXT_COLOR;
        underLineExpandingText = false;
        expandingTextHighlightColor = DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR;
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        expandingText = DEFUALT_EXPANDING_TEXT;
        expandingTextColor = DEFAULT_EXPANDING_TEXT_COLOR;
        underLineExpandingText = false;
        expandingTextHighlightColor = DEFAULT_EXPANDING_TEXT_HIGHTLIGHT_COLOR;
    }


    public SpannableString getFullText() {
        return fullText;
    }

    public void setFullText(SpannableString fullText) {
        this.fullText = fullText;
    }

    public int getExpandingTextColor() {
        return expandingTextColor;
    }

    public void setExpandingTextColor(int expandingTextColor) {
        this.expandingTextColor = expandingTextColor;
    }

    public boolean isUnderLineExpandingText() {
        return underLineExpandingText;
    }

    public void setUnderLineExpandingText(boolean underLineExpandingText) {
        this.underLineExpandingText = underLineExpandingText;
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
                if(secondLineStart>secondLineEnd-moreStr.length()) {
                    secondLineString = getText().toString().substring(secondLineStart, secondLineEnd - moreStr.length());
                }
                else {
                    int lineLength = secondLineEnd-secondLineStart;
                    secondLineString = getText().toString().substring(secondLineStart, secondLineEnd -lineLength+1);
                }
                secondLineString =secondLineString.concat(" "+moreStr);
                shortStringBuilder.append(secondLineString);
            }

            //shortStringBuilder.append(" "+moreStr);
            //SpannableString spannableString = spanPostsText(getContext(),shortStringBuilder.toString(),userName);
            SpannableString spannableString = new SpannableString(shortStringBuilder);
            StringBuilder spannedStringBuilder = new StringBuilder(spannableString);
            int startSpan = spannedStringBuilder.toString().length() - moreStr.length();
            int endSpan = startSpan + moreStr.length();
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    setText(fullText);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(expandingTextColor);
                    ds.setUnderlineText(underLineExpandingText);
                }
            },startSpan,endSpan,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setMaxLines(Integer.MAX_VALUE);
            //setEllipsize(null);
            setHighlightColor(expandingTextHighlightColor);
            setMovementMethod(LinkMovementMethod.getInstance());
            setText(spannableString);
            requestLayout();
            invalidate();
            return;
        }
        super.onLayout(changed, left, top, right, bottom);
    }

}

