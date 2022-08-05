package za.co.sturtium.sturtiuminformatics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class WasteDataActivity extends AppCompatActivity {

    private final static int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private Button mButtonGetlocation;
    private EditText mEditTextCityName;
    private EditText mEditTextIssueDescription;
    private EditText mEditTextGeolocation;

    //geolocation code//
    private LocationListener locationListener;
    private LocationManager locationManager;
    //geolocation code//

    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference mWasteStorageRef;
    private DatabaseReference mWasteDatabaseRef;

    private StorageTask mWasteUploadTask;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste_data);

        mButtonChooseImage = (Button) findViewById(R.id.button_choose_image);
        mButtonUpload = (Button) findViewById(R.id.button_upload);
        mButtonGetlocation = (Button) findViewById(R.id.button_getlocation);

        mEditTextCityName = (EditText) findViewById(R.id.edit_text_city_name);
        mEditTextIssueDescription = (EditText) findViewById(R.id.edit_text_description);
        mEditTextGeolocation = (EditText) findViewById(R.id.edit_text_geolocation);

        mImageView = findViewById(R.id.wasteimage_view);
        mProgressBar = findViewById(R.id.progress_bar);

        mWasteStorageRef = FirebaseStorage.getInstance().getReference("wastedata");
        mWasteDatabaseRef = FirebaseDatabase.getInstance().getReference("wastedata");

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWasteUploadTask != null && mWasteUploadTask.isInProgress()){
                    Toast.makeText(WasteDataActivity.this, "Upload in Progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    wasteuploadFile();
                }

            }
        });




        //geolocation code//


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mEditTextGeolocation.append("\n " + location.getLatitude() + " " + location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 10);
            return;
        }else {
            configurebutton();
        }


        locationManager.requestLocationUpdates("gps", 500000, 0, locationListener);


        mButtonGetlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurebutton();



            }
        });


        //geolocation code//






    }


    //GEOLOCATION CODE//
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configurebutton();
                return;
        }
    }

    private void configurebutton() {
        mButtonGetlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 500000, 0, locationListener);

            }
        });


    }
    //GEOLOCATION CODE//



    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    private void wasteuploadFile() {

        if (mImageUri != null){
            StorageReference foodfileReference = mWasteStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mWasteUploadTask = foodfileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 5000);

                            Toast.makeText(WasteDataActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                            //actual upload method
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            WasteUpload upload = new WasteUpload(mEditTextCityName.getText().toString().trim(),
                                    mEditTextIssueDescription.getText().toString().trim(), mEditTextGeolocation.getText().toString().trim(),downloadUrl.toString());

                            String uploadId = mWasteDatabaseRef.push().getKey();

                            mWasteDatabaseRef.child(uploadId).setValue(upload);

                            //Possible move to back activity//
                            Intent intent = new Intent(WasteDataActivity.this, WasteManagement.class);
                            startActivity(intent);
                            ////


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(WasteDataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);

                        }
                    });


        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();

        }



    }


}
