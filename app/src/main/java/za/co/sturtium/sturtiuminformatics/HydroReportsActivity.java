package za.co.sturtium.sturtiuminformatics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HydroReportsActivity extends AppCompatActivity {

    private RecyclerView mHydroRecyclerView;
    private HydroImageAdapter mHydroImageAdaper;

    private DatabaseReference mWasteDatabaseRef;
    private List<WasteUpload> mWasteUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_reports);

        mHydroRecyclerView = findViewById(R.id.hydrorecycler_view);
        mHydroRecyclerView.setHasFixedSize(true);
        mHydroRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWasteUploads = new ArrayList<>();
        mWasteDatabaseRef = FirebaseDatabase.getInstance().getReference("hydrodata");

        mWasteDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    WasteUpload wasteUpload = postSnapshot.getValue(WasteUpload.class);
                    mWasteUploads.add(wasteUpload);
                }

                mHydroImageAdaper = new HydroImageAdapter(HydroReportsActivity.this, mWasteUploads);

                mHydroRecyclerView.setAdapter(mHydroImageAdaper);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HydroReportsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
