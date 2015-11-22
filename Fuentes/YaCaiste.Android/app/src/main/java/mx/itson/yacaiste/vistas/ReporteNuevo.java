package mx.itson.yacaiste.vistas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mx.itson.yacaiste.R;
import mx.itson.yacaiste.entidades.FotoEntity;
import mx.itson.yacaiste.modelos.ReportEntity;
import mx.itson.yacaiste.utils.Utils;

public class ReporteNuevo extends AppCompatActivity implements View.OnClickListener {
    ReportEntity reportEntity;
    List<FotoEntity> fotoEntityList;
    private LinearLayout ll_reportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_nuevo);
        reportEntity = new ReportEntity();
        fotoEntityList = new ArrayList<>();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton add_picture = (ImageButton) findViewById(R.id.add_picture);
        add_picture.setOnClickListener(this);
        ll_reportes = (LinearLayout) findViewById(R.id.ll_reportes);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception ex) {
            Log.d("S_ACTION_BAR", ex.getMessage());
        }
        addImage(getIntent().getStringExtra("photo_path"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_picture:
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
            String path_photo = Utils.getmCurrentPhotoPath();
            addImage(path_photo);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addImage(String path) {
        FotoEntity fotoEntity = new FotoEntity();
        fotoEntity.setPath(getIntent().getStringExtra("photo_path"));
        fotoEntityList.add(fotoEntity);
        ImageView new_image = new ImageView(this);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen.size_image_reporte), //Width
                getResources().getDimensionPixelSize(R.dimen.size_image_reporte)  //Height
        );
        lp.setMargins(
                fotoEntityList.size() > 1 ? 0 : getResources().getInteger(R.integer.margin_image_reporte),
                0,
                getResources().getInteger(R.integer.margin_image_reporte),
                0
        );
        new_image.setLayoutParams(lp);
        new_image.setImageURI(Uri.parse("file:" + path));
        ll_reportes.addView(new_image);
    }
}
