package com.example.revolut_test;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private ArrayList<Currency> mContacts;
    private LinkedHashMap<String , EditText> listofedittextes;
    private Activity activity;

    CurrencyAdapter(ArrayList<Currency> currencies , Activity activity){
        this.mContacts = currencies;
        listofedittextes = new LinkedHashMap<>();
        this.activity = activity;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public CurrencyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.currency_view_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        ButterKnife.bind(this,contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CurrencyAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Currency contact = mContacts.get(position);

        // Set item views based on your views and data model

        final String[] tempvalue = {null};
        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getmCurrency());
        EditText editText = viewHolder.editText;
        editText.setText(contact.getmValue());
        listofedittextes.put(contact.getmCurrency(),editText);
        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    float k = Float.parseFloat(s.toString()) / Float.parseFloat(tempvalue[0]);
                    editText.removeTextChangedListener(this);
                    String l;
                    int iter = 0;
                    for (String x : listofedittextes.keySet()) {
                        float i = Float.parseFloat(mContacts.get(iter).getmValue()) * k;
                        l = String.valueOf(i);
                        if (!x.equals(mContacts.get(0).getmCurrency()))
                            listofedittextes.get(x).setText(l);
                        iter++;
                    }
                    viewHolder.editText.setSelection(viewHolder.editText.length());
                    editText.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ((MainActivity) activity).stop();
                 int pos = viewHolder.getAdapterPosition();
                Currency temp = mContacts.remove(viewHolder.getAdapterPosition());
                mContacts.add(0, temp);
                notifyItemMoved(pos, 0);
                tempvalue[0] = viewHolder.editText.getText().toString();
                editText.addTextChangedListener(textWatcher);
            }
            else{
                ((MainActivity) activity).start();
                 editText.removeTextChangedListener(textWatcher);
            }
        });
    }

    public void updatecurrencies(ArrayList<Currency> currencies){
        activity.runOnUiThread(() -> {
            int i = 0;
            for (String temp : listofedittextes.keySet()){
                Objects.requireNonNull(listofedittextes.get(temp)).
                                                         setText(currencies.get(i).getmValue());
                mContacts.get(i).setmValue(currencies.get(i).getmValue());
                Objects.requireNonNull(listofedittextes.get(temp)).setSelection(Objects.
                                        requireNonNull(listofedittextes.get(temp)).length());
                i++;
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        @BindView(R.id.val_txt)
        public TextView nameTextView;

        @BindView(R.id.curr_edit)
        public EditText editText;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            ButterKnife.bind(this, itemView);

           // nameTextView =  itemView.findViewById(R.id.val_txt);
          //  editText = itemView.findViewById(R.id.curr_edit);
        }
    }
}
