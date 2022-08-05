package za.co.sturtium.sturtiuminformatics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private ImageView WasteButton;
    private ImageView HydroButton;
    private ImageView PowerButton;
    private ImageView InfoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WasteButton = (ImageView) findViewById(R.id.waste_btn);

        WasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WasteManagement.class);
                startActivity(intent);
            }
        });

        HydroButton = (ImageView) findViewById(R.id.water_btn);

        HydroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HydroManagement.class);
                startActivity(intent);
            }
        });

        PowerButton = (ImageView) findViewById(R.id.power_btn);

        PowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PowerManagement.class);
                startActivity(intent);
            }
        });

        InfoButton = (ImageView) findViewById(R.id.infohome_btn);

        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InformationActivity.class);
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

            case R.id.wastenav:
                Intent i = new Intent(HomeActivity.this, WasteManagement.class);
                startActivity(i);
                return true;

            case R.id.waternav:
                Intent intent = new Intent(HomeActivity.this, HydroManagement.class);
                startActivity(intent);
                return true;

            case R.id.powernav:
                Intent j = new Intent(HomeActivity.this, PowerManagement.class);
                startActivity(j);
                return true;

            case R.id.helpnav:
                Intent k = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(k);
                return true;

            case R.id.logoutnav:
                Intent l = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(l);
                Paper.book().destroy();
                return true;
            default:
        }
        return true;


    }
    //Weird menu code
}
