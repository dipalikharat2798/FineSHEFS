package com.example.finechefs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.finechefs.databinding.ActivityBreakDesBinding;
import com.squareup.picasso.Picasso;

import java.io.File;

public class Break_Des_Activity extends AppCompatActivity {
ActivityBreakDesBinding binding;
String bName , bsteps,url;
TextView steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBreakDesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        steps=findViewById(R.id.steps);
        bName=getIntent().getStringExtra("name");
        bsteps=getIntent().getStringExtra("steps");
        url=getIntent().getStringExtra("image");
        binding.name.setText(bName);
       steps.setText(bsteps);
        Picasso.get().load(url).into(binding.imageId);
       // Log.d("TAG", "onBindViewHolder: "+bsteps);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                break;
            case R.id.share:
//                ApplicationInfo api = getApplicationContext().getApplicationInfo();
//                String apkpath = api.sourceDir;
//                Intent intent= new Intent(Intent.ACTION_SEND);
//                intent.setType("application/vnd.android.package-archive");
//                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
//                startActivity(Intent.createChooser(intent,"sharevia"));
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
               // Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                Uri bitmapUri = Uri.parse(url);
                i.putExtra(Intent.EXTRA_TEXT, bitmapUri+"\n"+bName+"\n"+bsteps);
                startActivity(Intent.createChooser(i, "Share to"));
                break;
            case R.id.Logout:
                Intent intent1=new Intent(Break_Des_Activity.this,MainActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}