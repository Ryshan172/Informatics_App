package za.co.sturtium.sturtiuminformatics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import io.paperdb.Paper;

public class HydroManagement extends AppCompatActivity {

    private ImageView ReportHydroButton;
    private ImageView HydroIssueButton;
    private ImageView HelpHydroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_management);

        ReportHydroButton = (ImageView) findViewById(R.id.reporthydro_btn);
        HydroIssueButton = (ImageView) findViewById(R.id.checkhydro_btn);

        ReportHydroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HydroManagement.this, HydroDataActivity.class);
                startActivity(intent);
            }
        });

        HydroIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HydroManagement.this, HydroReportsActivity.class);
                startActivity(intent);
            }
        });

        HelpHydroButton = (ImageView) findViewById(R.id.helphydro_btn);
        HelpHydroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HydroManagement.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    //Weird menu code//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigate_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.homenav:
                Intent intent = new Intent(HydroManagement.this, HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.wastenav:
                Intent i = new Intent(HydroManagement.this, WasteManagement.class);
                startActivity(i);
                return true;


            case R.id.powernav:
                Intent j = new Intent(HydroManagement.this, PowerManagement.class);
                startActivity(j);
                return true;

            case R.id.helpnav:
                Intent k = new Intent(HydroManagement.this, HelpActivity.class);
                startActivity(k);
                return true;

            case R.id.logoutnav:
                Intent l = new Intent(HydroManagement.this, MainActivity.class);
                startActivity(l);
                Paper.book().destroy();
                return true;
            default:
        }
        return true;


    }
    //Weird menu code
}
