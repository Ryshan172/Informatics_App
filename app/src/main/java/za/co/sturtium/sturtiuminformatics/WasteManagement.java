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

public class WasteManagement extends AppCompatActivity {
    private ImageView ReportWasteButton;
    private ImageView WasteIssueButton;
    private ImageView WasteHelpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste_management);

        ReportWasteButton = (ImageView) findViewById(R.id.reportwaste_btn);
        WasteIssueButton = (ImageView) findViewById(R.id.checkwaste_btn);

        ReportWasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WasteManagement.this, WasteDataActivity.class);
                startActivity(intent);
            }
        });

        WasteIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WasteManagement.this, WasteReportsActivity.class);
                startActivity(intent);
            }
        });

        WasteHelpButton = (ImageView) findViewById(R.id.wastehelp_btn);
        WasteHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WasteManagement.this, HelpActivity.class);
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
                Intent intent = new Intent(WasteManagement.this, HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.powernav:
                Intent i = new Intent(WasteManagement.this, PowerManagement.class);
                startActivity(i);
                return true;


            case R.id.waternav:
                Intent j = new Intent(WasteManagement.this, HydroManagement.class);
                startActivity(j);
                return true;

            case R.id.helpnav:
                Intent k = new Intent(WasteManagement.this, HelpActivity.class);
                startActivity(k);
                return true;

            case R.id.logoutnav:
                Intent l = new Intent(WasteManagement.this, MainActivity.class);
                startActivity(l);
                Paper.book().destroy();
                return true;
            default:
        }
        return true;


    }
    //Weird menu code
}
