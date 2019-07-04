package com.example.myfirstapp;

import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items );
        lvItems = (ListView) findViewById(R.id. lvItems);
        lvItems.setAdapter(itemsAdapter);

        //mock data
        items.add("First item");
        items.add("Second item");
    }
    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewitem);
        String itemText =etNewItem.getText().toString();
        etNewItem.setText(" ");
        Toast.makeText(getApplicationContext(), "Item added to list", Toast.LENGTH_SHORT).show();
    }
    private void setupListViewListener() {
        Log.i("MainActivity", "Setting up Listener on list view");
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
         public boolean onItemLongClick(AdapterView<?> parent, View view, int position,long id){
                log.i("MainActivity", "Item removed from list: " + position);
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    private File getDataFile(){
        return new File(getFilesDir(), "todo.txt");
    }

    private void readItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading file". e);
            items = new Arraylist<>();
        }
        private void writeItems() {
            try {
                FilleUtils.writeLines(getDataFile(), items);
            } catch (IOException e) {
                Log.e("MainActivity", "Error wrintin file". e);

        }
    }
}
