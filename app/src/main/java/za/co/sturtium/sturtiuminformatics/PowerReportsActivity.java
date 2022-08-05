package za.co.sturtium.sturtiuminformatics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PowerReportsActivity extends AppCompatActivity {

    private RecyclerView mPowerRecyclerView;
    private PowerImageAdapter mPowerImageAdaper;

    private DatabaseReference mWasteDatabaseRef;
    private List<WasteUpload> mWasteUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_reports);

        mPowerRecyclerView = findViewById(R.id.powerrecycler_view);
        mPowerRecyclerView.setHasFixedSize(true);
        mPowerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWasteUploads = new ArrayList<>();
        mWasteDatabaseRef = FirebaseDatabase.getInstance().getReference("powerdata");

        mWasteDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    WasteUpload wasteUpload = postSnapshot.getValue(WasteUpload.class);
                    mWasteUploads.add(wasteUpload);
                }

                mPowerImageAdaper = new PowerImageAdapter(PowerReportsActivity.this, mWasteUploads);

                mPowerRecyclerView.setAdapter(mPowerImageAdaper);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PowerReportsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
