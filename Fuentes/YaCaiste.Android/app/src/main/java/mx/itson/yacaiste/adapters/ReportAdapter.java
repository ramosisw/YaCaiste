package mx.itson.yacaiste.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.List;

import mx.itson.yacaiste.R;
import mx.itson.yacaiste.modelos.ReportEntity;
import mx.itson.yacaiste.vistas.ReporteActivity;


/**
 * @author ramos.isw@gmail.com
 *         Created by Julio on 15/04/2015.
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private final List<ReportEntity> items;
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;

    public ReportAdapter(Context context, List<ReportEntity> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reports_row, viewGroup, false);
        v.setBackgroundResource(mBackground);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        ReportEntity report = items.get(i);
        viewHolder.imagen.setImageResource(R.drawable.avatar_contact);
        viewHolder.address.setText(report.getDireccion());
        viewHolder.numero_reportes.setText(report.getNumReportes() + (report.getNumReportes() == 1 ? " Reporte" : " Reportes"));
        viewHolder.proceso.setText("Atencion Inmediata");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");

        viewHolder.fecha.setText(format.format(report.getFecha()));
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent reporteIntent = new Intent(context, ReporteActivity.class);
                reporteIntent.putExtra("ID_REPORTE", items.get(i).getId());
                context.startActivity(reporteIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    /**
     * @param imageView imagen que se pulsara para abrir de manera mas grande
     */
    private void loadPhoto(ImageView imageView) {
        //AlertDialog.Builder imageDialog = new AlertDialog.Builder(this.activity);
        //LayoutInflater inflater = (LayoutInflater) this.activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CircularImageView imagen;
        private final TextView address;
        private final TextView numero_reportes;
        private final TextView proceso;
        private final TextView fecha;
        private final View mView;

        ViewHolder(View view) {
            super(view);
            mView = view;
            imagen = (CircularImageView) view.findViewById(R.id.report_photo_circle);
            address = (TextView) view.findViewById(R.id.report_row_address);
            numero_reportes = (TextView) view.findViewById(R.id.report_row_num_reportes);
            proceso = (TextView) view.findViewById(R.id.report_row_process);
            fecha = (TextView) view.findViewById(R.id.conversations_row_date);
        }
    }
}
