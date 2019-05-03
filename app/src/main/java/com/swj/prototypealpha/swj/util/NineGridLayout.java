package com.swj.prototypealpha.swj.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.swj.prototypealpha.R;
import com.swj.prototypealpha.swj.Picture;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * <pre>
 *     author : marin
 *     time   : 2019/05/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NineGridLayout extends ViewGroup {
    private static final float DEFUALT_SPACING = 3f;

    class MyGesture implements OnTouchListener, GestureDetector.OnGestureListener {

        GestureDetector myGesture = new GestureDetector(getContext(),this);
        private Picture picture;
        public MyGesture (Picture url) {
            picture=url;
        }

        @Override
        public boolean onDown (MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress (MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp (MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll (MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress (MotionEvent e) {
            System.out.println("sdfsafdsfgasfsadf");
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle("照片");
            dialog.setMessage("确认删除吗？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick (DialogInterface dialog, int which) {
                    pictures.remove(picture);
                    notifyDataSetChanged();
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick (DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        @Override
        public boolean onFling (MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onTouch (View v, MotionEvent event) {
            System.out.println("----------------------------");
            myGesture.onTouchEvent(event);
            return false;
        }
    }
    protected     Context                    mContext;
    private       float                      image_ratio     = 1.0f;//默认图片长宽比例
    private       int                        oneImageWidth;//一张图的宽度
    private       int                        oneImageHeight;//一张图的高度
    private       float                      mSpacing        = DEFUALT_SPACING;
    private       int                        mColumns;
    private       int                        mRows;
    private       int                        mTotalWidth;
    private       int                        mSingleWidth;
    private       int                        itemPosition;
    private       OnItemPictureClickListener listener;
    private       boolean                    mIsShowAll      = false;
    private       List<Picture>              pictures        = new ArrayList<>();

    public NineGridLayout (Context context) {
        this(context, null);
    }

    public NineGridLayout (Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridLayout);
        mSpacing = typedArray.getDimension(R.styleable.NineGridLayout_sapcing, DEFUALT_SPACING);
        oneImageWidth = (int) typedArray.getDimension(R.styleable.NineGridLayout_oneImageWidth, 0);
        oneImageHeight = (int) typedArray.getDimension(R.styleable.NineGridLayout_oneImageHeight,
                0);
        image_ratio = typedArray.getFloat(R.styleable.NineGridLayout_image_ratio, image_ratio);
        typedArray.recycle();
        init(context);
    }

    private void init (Context context) {
        mContext = context;
        if (getListSize(pictures) == 0) {setVisibility(GONE);} else {setVisibility(VISIBLE);}
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        mTotalWidth = right - left;
        mSingleWidth = (int) ((mTotalWidth - mSpacing * (3 - 1)) / 3);
        notifyDataSetChanged();
    }

    /**
     * 设置间隔
     *
     * @param spacing
     */
    public void setSpacing (float spacing) {
        mSpacing = spacing;
    }

    /**
     * 设置是否显示所有图片（超过最大数时）
     *
     * @param isShowAll
     */
    public void setIsShowAll (boolean isShowAll) {
        mIsShowAll = isShowAll;
    }

    public void setUrlList (List<Picture> urlList) {
        pictures = urlList;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged () {
        post(new TimerTask() {
            @Override
            public void run () {
                refresh();
            }
        });
    }

    private void refresh () {
        removeAllViews();
        int size = getListSize(pictures);
        if (size > 0) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
            return;
        }

        generateChildrenLayout(size);
        layoutParams();
        for (int i = 0; i < size; i++) {
            Picture url = pictures.get(i);
            RatioImageView imageView = createImageView(i, url);
            layoutImageView(imageView, i, url);
        }
    }

    private void layoutParams () {
        int singleHeight = mSingleWidth;

        //根据子view数量确定高度
        LayoutParams params = getLayoutParams();
        params.height = (int) (singleHeight * mRows + mSpacing * (mRows - 1));
        setLayoutParams(params);
    }

    @SuppressLint("ClickableViewAccessibility")
    private RatioImageView createImageView (final int i, final Picture url) {
        final RatioImageView imageView = new RatioImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //        imageView.setOnClickListener(new OnClickListener() {
        //            @Override
        //            public void onClick (View v) {
        //                onClickAImage(i, url, pictures, imageView);
        //            }
        //
        //            private void onClickAImage (int imageIndex, Picture url, List<Picture>
        //            urlList,
        //                                        ImageView imageView) {
        //                listener.onItemPictureClick(itemPosition, imageIndex, url, urlList,
        //                imageView);
        //            }
        //        });
        imageView.setOnTouchListener(new MyGesture(url));
        return imageView;
    }

    /**
     * @param imageView
     * @param url
     */
    private void layoutImageView (RatioImageView imageView, int num, Picture url) {
        final int singleWidth = (int) ((mTotalWidth - mSpacing * (3 - 1)) / 3);
        int singleHeight = singleWidth;

        int[] position = findPosition(num);
        int left = (int) ((singleWidth + mSpacing) * position[1]);
        int top = (int) ((singleHeight + mSpacing) * position[0]);
        int right = left + singleWidth;
        int bottom = top + singleHeight;

        imageView.layout(left, top, right, bottom);
        addView(imageView);
        displayImage(num, imageView, url);
    }

    public void setItemPosition (int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public void setListener (OnItemPictureClickListener listener) {
        this.listener = listener;
    }

    private void displayImage (int num, RatioImageView imageView, Picture url) {
        if (mContext != null) {
            imageView.setImageBitmap(url.getImageID());
            imageView.setTag(String.valueOf(num));
            imageView.setTransitionName(String.valueOf(num));
        }
    }

    private int[] findPosition (int childNum) {
        return new int[]{childNum / mColumns, childNum - (childNum / mColumns) * mColumns};
    }

    /**
     * 根据图片个数确定行列数量
     *
     * @param length
     */
    private void generateChildrenLayout (int length) {
        if (length <= 3) {
            mRows = 1;
            mColumns = length;
        } else if (length <= 6) {
            mRows = 2;
            mColumns = 3;
            if (length == 4) {
                mColumns = 2;
            }
        } else {
            mColumns = 3;
            mRows = 3;
            if (mIsShowAll) {
                mRows = (int) Math.ceil(length / 3);
            }
        }
    }

    private int getListSize (List list) {
        return list == null ? 0 : list.size();
    }
}
