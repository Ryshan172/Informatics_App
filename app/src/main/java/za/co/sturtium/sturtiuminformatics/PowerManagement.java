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

public class PowerManagement extends AppCompatActivity {

    private ImageView ReportPowerButton;
    private ImageView PowerIssueButton;
    private ImageView HelpPowerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_management);

        ReportPowerButton = (ImageView) findViewById(R.id.reportpower_btn);
        PowerIssueButton = (ImageView) findViewById(R.id.checkpower_btn);


        ReportPowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PowerManagement.this, PowerDataActivity.class);
                startActivity(intent);
            }
        });

        PowerIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PowerManagement.this, PowerReportsActivity.class);
                startActivity(intent);
            }
        });


        HelpPowerButton = (ImageView) findViewById(R.id.helppower_btn);
        HelpPowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PowerManagement.this, HelpActivity.class);
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
                Intent intent = new Intent(PowerManagement.this, HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.wastenav:
                Intent i = new Intent(PowerManagement.this, WasteManagement.class);
                startActivity(i);
                return true;


            case R.id.waternav:
                Intent j = new Intent(PowerManagement.this, HydroManagement.class);
                startActivity(j);
                return true;

            case R.id.helpnav:
                Intent k = new Intent(PowerManagement.this, HelpActivity.class);
                startActivity(k);
                return true;

            case R.id.logoutnav:
                Intent l = new Intent(PowerManagement.this, MainActivity.class);
                startActivity(l);
                Paper.book().destroy();
                return true;
            default:
        }
        return true;


    }
    //Weird menu code
}
