package mx.itson.yacaiste.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import mx.itson.yacaiste.R;
import mx.itson.yacaiste.modelos.ReportEntity;


/**
 * @author  ramos.isw@gmail.com
 * Created by Julio on 15/04/2015.
 */
public class ReportAdapter extends BaseAdapter {
    private final Activity activity;
    private final List<ReportEntity> items;

    public ReportAdapter(Activity activity, List<ReportEntity> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ReportEntity getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.reports_row, null);
        }
        final ReportEntity report = items.get(i);
        ImageView imagen = ((ImageView) view.findViewById(R.id.report_photo_circle));
        imagen.setImageResource(R.drawable.avatar_contact);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView img = new ImageView(activity);
                img.setImageResource(R.drawable.avatar_contact);
                //loadPhoto(img);
            }
        });
        ((TextView) view.findViewById(R.id.report_row_address)).setText(report.getDireccion());
        ((TextView) view.findViewById(R.id.report_row_num_reportes)).setText(report.getNumReportes() + (report.getNumReportes() == 1 ? " Reporte" : " Reportes"));
        ((TextView) view.findViewById(R.id.report_row_process)).setText("Atencion Inmediata");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");

        ((TextView) view.findViewById(R.id.conversations_row_date)).setText(format.format(report.getFecha()));

        return view;
    }

    /**
     * @param imageView imagen que se pulsara para abrir de manera mas grande
     */
    private void loadPhoto(ImageView imageView) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this.activity);
        LayoutInflater inflater = (LayoutInflater) this.activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        /*View layout = inflater.inflate(R.layout.custom_image_show,
                (ViewGroup) this.activity.findViewById(R.id.layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        TextView tv = (TextView) layout.findViewById(R.id.custom_fullimage_placename);
        tv.setText("Prueba...");
        tv.setVisibility(View.GONE);
        image.setImageDrawable(imageView.getDrawable());
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        imageDialog.create();
        imageDialog.show();*/
    }
}
