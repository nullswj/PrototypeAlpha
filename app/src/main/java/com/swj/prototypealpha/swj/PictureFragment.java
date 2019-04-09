package com.swj.prototypealpha.swj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swj.prototypealpha.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_FIRST_USER;


public class PictureFragment extends Fragment {

    private final String TAG = "PictureFragment";

    private FloatingActionButton fabtn_picture;

    private RecyclerView recv_photo;

    public static final int TAKE_PHOTO = 1;

    public static Uri imageUri;

    private File outputImage;

    public static ImageAdapter adapter;

    public static List<Picture> pictureList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fabtn_picture = getActivity().findViewById(R.id.fabtn_takephoto);


        fabtn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        recv_photo = getActivity().findViewById(R.id.recv_photo);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recv_photo.setLayoutManager(layoutManager);
        adapter = new ImageAdapter(pictureList);

        recv_photo.setAdapter(adapter);


    }



    private void takePhoto()
    {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");

        String filename = dateFormat.format(date)+".jpg";
        outputImage = new File(getActivity().getExternalCacheDir(),filename);
        try
        {
            if(outputImage.exists())
                outputImage.delete();
            outputImage.createNewFile();
        }
        catch (IOException e)
        {
            Log.e(TAG, "takephoto: "+ "IO异常");
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24)
            imageUri = FileProvider.getUriForFile(getActivity(),"com.example.dms.fileprovider",outputImage);
        else
            imageUri = Uri.fromFile(outputImage);

        Intent intent =  new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case TAKE_PHOTO:
                if(requestCode == RESULT_FIRST_USER)
                {
                    try
                    {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        Picture picture = new Picture(bitmap);
                        pictureList.add(picture);
                        int len = pictureList.size();
                        adapter.notifyItemChanged(len-1);
                        adapter.notifyItemChanged(0,len);
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
