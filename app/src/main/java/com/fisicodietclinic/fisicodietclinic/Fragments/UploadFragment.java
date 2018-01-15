package com.fisicodietclinic.fisicodietclinic.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fisicodietclinic.fisicodietclinic.R;


import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * Created by manyamadan on 05/12/17.
 */


public class UploadFragment extends Fragment {


private static int RESULT_LOAD = 1;
String img_Decodable_Str;
Button loadImageButton;
ImageView imgView1,imgView2;
CardView before,after;
int BEFORE_TAG=0,IMAGE_COUNT,CASE=0;
ProgressDialog dialog;
Uri selectedImage,selectedImage1;
    private  UploadFragment parent;

public static com.fisicodietclinic.fisicodietclinic.Fragments.DietFragment newInstance() {
    com.fisicodietclinic.fisicodietclinic.Fragments.DietFragment fragment = new com.fisicodietclinic.fisicodietclinic.Fragments.DietFragment();
    return fragment;
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.upload_fragment, container, false);
    loadImageButton = (Button) view.findViewById(R.id.buttonLoadPicture);
    imgView1 = (ImageView) view.findViewById(R.id.imgView1);
    imgView2 = (ImageView) view.findViewById(R.id.imgView2);
    before = (CardView) view.findViewById(R.id.beforecard);
   after = (CardView) view.findViewById(R.id.aftercard);
    dialog=new ProgressDialog(getActivity());

    before.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //dialog.show();
            String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                BEFORE_TAG=1;
            if (EasyPermissions.hasPermissions(getActivity(), galleryPermissions)) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD);

            } else {
                EasyPermissions.requestPermissions(getActivity(), "Access for storage",
                        101, galleryPermissions);
            }



        }
    });

    after.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            BEFORE_TAG=0;
            if (EasyPermissions.hasPermissions(getActivity(), galleryPermissions)) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD);

            } else {
                EasyPermissions.requestPermissions(getActivity(), "Access for storage",
                        101, galleryPermissions);
            }



        }
    });
    loadImageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.show();
            if(selectedImage != null && selectedImage1!= null)
            {
            IMAGE_COUNT = 2;
            }
            else
            {
                IMAGE_COUNT =1;
            }
            Map config = new HashMap();
            config.put("cloud_name", "diet");
            config.put("api_key", "658118659628992");
            config.put("api_secret", "K5lcTS6bwrgFRUhVo3n1OHwWNH8");
            final Cloudinary cloudinary = new Cloudinary(config);
            try {
                final Runnable runnable = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            if( IMAGE_COUNT ==2 ) {
                                InputStream in = getActivity().getContentResolver().openInputStream(selectedImage);
                                Map uploadResult = cloudinary.uploader().upload(in, ObjectUtils.emptyMap());
                                InputStream in1 = getActivity().getContentResolver().openInputStream(selectedImage1);
                                Map uploadResult1 = cloudinary.uploader().upload(in1, ObjectUtils.emptyMap());
                                if(uploadResult1.containsKey("url") && uploadResult.containsKey("url"))
                                {
                                    CASE =1;
                                    Thread.currentThread().interrupt();
                                    getActivity().runOnUiThread(new Runnable()
                                    {
                                        public void run()
                                        {
                                            dialog.dismiss();

                                            showToast();
                                            //Do your UI operations like dialog opening or Toast here
                                        }
                                    });
                                    //Toast.makeText(getActivity(),"Images Uploaded!!",Toast.LENGTH_LONG).show();
                                }
                                else
                                {

                                    CASE =2;
                                    Thread.currentThread().interrupt();
                                    getActivity().runOnUiThread(new Runnable()
                                    {
                                        public void run()
                                        {
                                            dialog.dismiss();

                                            showToast();
                                            //Do your UI operations like dialog opening or Toast here
                                        }
                                    });

                                   // Toast.makeText(getActivity(),"Sorry!Images could not be Uploaded",Toast.LENGTH_LONG).show();
                                }

                            }
                            else
                            {

                                CASE=3;
                                Thread.currentThread().interrupt();
                                getActivity().runOnUiThread(new Runnable()
                                {
                                    public void run()
                                    {
                                        dialog.dismiss();

                                        showToast();
                                        //Do your UI operations like dialog opening or Toast here
                                    }
                                });
                               // Toast.makeText(getActivity(),"Please attach both the images",Toast.LENGTH_LONG).show();

                            }


                        } catch (IOException e) {
                            //TODO: better error handling when image uploading fails
                            e.printStackTrace();
                        }
                    }
                };

                new Thread(runnable).start();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    });





    return view;
}

public void showToast()
{

    if(CASE==1)
    {
        Toast.makeText(getActivity(),"Images Uploaded!!",Toast.LENGTH_LONG).show();

    }
    else if(CASE ==2)
    {
        Toast.makeText(getActivity(),"Sorry!Images could not be Uploaded",Toast.LENGTH_LONG).show();
    }
    else if(CASE ==3)
    {
        Toast.makeText(getActivity(),"Please attach both the images",Toast.LENGTH_LONG).show();

    }

}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if(BEFORE_TAG ==1){


            if (requestCode == RESULT_LOAD && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                 selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                img_Decodable_Str = cursor.getString(columnIndex);
                cursor.close();

                Bitmap d = BitmapFactory
                        .decodeFile(img_Decodable_Str);
                int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
                // Set the Image in ImageView after decoding the String



                    imgView1.setImageBitmap(scaled);







            } else {
                Toast.makeText(getActivity(), "Hey pick your image first",
                        Toast.LENGTH_LONG).show();
            }

            }
            else {
                if (requestCode == RESULT_LOAD && resultCode == RESULT_OK
                        && null != data) {
                    // Get the Image from data

                    selectedImage1 = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    // Get the cursor
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage1,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    img_Decodable_Str = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap d = BitmapFactory
                            .decodeFile(img_Decodable_Str);
                    int nh = (int) (d.getHeight() * (512.0 / d.getWidth()));
                    Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
                    // Set the Image in ImageView after decoding the String


                    imgView2.setImageBitmap(scaled);

                }
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), e+"", Toast.LENGTH_LONG)
                    .show();
        }

    }





}