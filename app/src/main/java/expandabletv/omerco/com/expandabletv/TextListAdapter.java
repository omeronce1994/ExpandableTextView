package expandabletv.omerco.com.expandabletv;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TextListAdapter extends RecyclerView.Adapter<TextListAdapter.TextViewHolder> {

    private Activity activity;
    private ArrayList<String> items;

    public TextListAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_text,parent,false);
        TextViewHolder viewHolder = new TextViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        String current = items.get(position);
        holder.textView.setMaxLines(2);
        holder.textView.setText(current);
        if(position%2==0){
            holder.textView.setExpandingTextColor(activity.getResources().getColor(R.color.colorAccent));
        }
        else {
            holder.textView.setExpandingTextColor(ExpandableTextView.DEFAULT_EXPANDING_TEXT_COLOR);
        }

    }

    @Override
    public int getItemCount() {
        return items!=null ? items.size():0;
    }

    public class TextViewHolder extends RecyclerView.ViewHolder{

        private ExpandableTextView textView;

        public TextViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textList);
        }
    }
}
