package pandaveloper.com.websocketclient.ui.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import pandaveloper.com.websocketclient.R;
import pandaveloper.com.websocketclient.model.bean.UnitBean;
import pandaveloper.com.websocketclient.model.dto.UnitDTO;
import pandaveloper.com.websocketclient.model.enums.UNIT_CLASS_TYPE;

/**
 * Created by cesar on 1/26/17.
 */

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitViewHolder> {

    private List<UnitBean> unitBeanList;

    public UnitAdapter(List<UnitBean> unitBeanList) {
        setUnitBeanList(unitBeanList);
    }

    @Override
    public UnitAdapter.UnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit_adapter,parent,false);
        return new UnitViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(UnitAdapter.UnitViewHolder holder, int position) {
        final UnitBean unit = getUnitBeanList().get(position);
        holder.tvUnitName.setText(unit.getName());
        holder.tvUnitClass.setText(unit.getClassType().getDescription());
        if(unit.getClassType().equals(UNIT_CLASS_TYPE.ARCHER)){
            holder.ivUnit.setImageDrawable(holder.archer);
        }else if(unit.getClassType().equals(UNIT_CLASS_TYPE.KNIGHT)){
            holder.ivUnit.setImageDrawable(holder.knight);
        }else if(unit.getClassType().equals(UNIT_CLASS_TYPE.LANCER)){
            holder.ivUnit.setImageDrawable(holder.lancer);
        }else if(unit.getClassType().equals(UNIT_CLASS_TYPE.LORD)){
            holder.ivUnit.setImageDrawable(holder.lord);
        }else{
            holder.ivUnit.setImageDrawable(holder.archer);
        }
    }

    @Override
    public int getItemCount() {
        return getUnitBeanList().size();
    }

    public class UnitViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvUnitName)
        protected TextView tvUnitName;
        @BindView(R.id.tvUnitClass)
        protected TextView tvUnitClass;
        @BindView(R.id.ivUnit)
        protected ImageView ivUnit;
        @BindDrawable(R.drawable.archer)
        protected Drawable archer;
        @BindDrawable(R.drawable.knight)
        protected Drawable knight;
        @BindDrawable(R.drawable.lancer)
        protected Drawable lancer;
        @BindDrawable(R.drawable.lord)
        protected Drawable lord;

        public UnitViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public List<UnitBean> getUnitBeanList() {
        return unitBeanList;
    }

    public void setUnitBeanList(List<UnitBean> unitBeanList) {
        this.unitBeanList = unitBeanList;
    }
}
