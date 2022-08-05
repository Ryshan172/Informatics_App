package za.co.sturtium.sturtiuminformatics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

public class HelpActivity extends AppCompatActivity {
    private Button WebSupportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        WebSupportButton = (Button) findViewById(R.id.websupport_btn);

        WebSupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://sturtium.co.za/products-and-projects/informatics/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

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
                Intent intent = new Intent(HelpActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.wastenav:
                Intent i = new Intent(HelpActivity.this, WasteManagement.class);
                startActivity(i);
                return true;


            case R.id.waternav:
                Intent j = new Intent(HelpActivity.this, HydroManagement.class);
                startActivity(j);
                return true;

            case R.id.powernav:
                Intent k = new Intent(HelpActivity.this, PowerManagement.class);
                startActivity(k);
                return true;

            case R.id.logoutnav:
                Intent l = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(l);
                Paper.book().destroy();
                return true;
            default:
        }
        return true;


    }
    //Weird menu code
}
