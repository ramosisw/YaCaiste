package mx.itson.yacaiste.vistas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.itson.yacaiste.R;
import mx.itson.yacaiste.adapters.ReportAdapter;
import mx.itson.yacaiste.modelos.ReportEntity;
import mx.itson.yacaiste.utils.Utils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Header views
    private ReportAdapter mAdapter;
    private List<ReportEntity> items;
    private FloatingActionButton headerFab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ReportEntity report = new ReportEntity();
            report.setDireccion("Direccion numero " + i);
            report.setFecha(new Date(2015, 07, (int) (Math.random() * 5 + 1)));
            report.setNumReportes(i + 1);
            report.setRank(Math.random() * 5);
            report.setRank(i);
            items.add(report);
        }
        mAdapter = new ReportAdapter(this, items);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(mAdapter);

        headerFab = (FloatingActionButton) findViewById(R.id.fab);
        headerFab.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent takePichture = Utils.TakePichture(this);
                if (takePichture != null)
                    startActivityForResult(takePichture, Crop.REQUEST_PICK);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop();
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, data);
        }
    }

    private void beginCrop() {
        Uri source = Uri.fromFile(new File(Utils.getmCurrentPhotoPath()));
        Crop.of(source, source).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            //resultView.setImageURI(Crop.getOutput(result));
            Intent reporteNuevo = new Intent(this, ReporteNuevo.class);
            reporteNuevo.putExtra("photo_path", Utils.getmCurrentPhotoPath());
            startActivity(reporteNuevo);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
