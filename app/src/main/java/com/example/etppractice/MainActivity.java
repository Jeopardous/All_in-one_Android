package com.example.etppractice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView contextMenuText;
    PagerAdapter pagerAdapter;
    Spinner spinner;
    FrameLayout frameLayout;
    Toolbar toolbar;
    List<String> spinnerList;
    Button button,frag1Btn,toggleProgressBtn,popUpMenuBtn,notificationBtn;
    ListView listView;
    ViewPager viewPager;
    TabLayout tabLayout;
    ProgressBar progressBar;
    Handler handler;
    ArrayList<ListItem> listItems=new ArrayList();
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1Btn=findViewById(R.id.fragment1_btn);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        spinner=findViewById(R.id.spinner);
        toolbar=findViewById(R.id.toolbar);
        button=findViewById(R.id.alert_btn);
        popUpMenuBtn=findViewById(R.id.popup_menu_btn);
        frameLayout=findViewById(R.id.frame_layout);
        progressBar=findViewById(R.id.progressBar_hz);
        toggleProgressBtn=findViewById(R.id.togal_progressBar);
        contextMenuText=findViewById(R.id.context_menu_text);
        notificationBtn=findViewById(R.id.notification_button);
        listView=findViewById(R.id.custom_list);
        nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        for (int i = 0; i <ListItemDetails.id.length;i++) {
            listItems.add(new ListItem(ListItemDetails.img[i],ListItemDetails.name[i]));
        }
        listView.setAdapter(new MyCustomListAdapter(listItems));

        registerForContextMenu(contextMenuText);

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.GRAY);
        toolbar.setTitle("AdarshBoss");

        spinnerList=new ArrayList<>();
        spinnerList.add("Adarsh");
        spinnerList.add("Monty");
        spinnerList.add("Guptil");
        spinnerList.add("Mishra");
        ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,spinnerList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(position<=4)
                {

                    View customeTostView=getLayoutInflater().inflate(R.layout.custom_toast_layout,null);
                    TextView textView=customeTostView.findViewById(R.id.custom_toast_text);
                    textView.setText((CharSequence)spinner.getSelectedItem());
                    Toast toast=new Toast(getApplicationContext());
                    toast.setView(customeTostView);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert Dialog Box");
                builder.setMessage("Tu Pura Sure H...?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView=findViewById(R.id.alert_text);
                        textView.setText("Ha me pura Sure Hu.");
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView=findViewById(R.id.alert_text);
                        textView.setText("");
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        });

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frame_layout,new FragmentOne());
        ft.commit();
        frag1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame_layout,new FragmentOne());
                ft.commit();
            }
        });

        pagerAdapter=new PagerAdapter(fm);
        pagerAdapter.addFragment(new FragmentOne(),"FOne");
        pagerAdapter.addFragment(new FragmentThree(),"FThree");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        toggleProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=1;i<=10;i++)
                        {
                            try{
                                Thread.sleep(1000);
                                progressBar.setProgress(10*i);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(1);
                    }

                }).start();
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1)
                {
                    toggleProgressBtn.setText("Progress Completed");
                    toggleProgressBtn.setBackgroundColor(Color.MAGENTA);

                }
            }
        };

        popUpMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pm=new PopupMenu(MainActivity.this,v);
                pm.inflate(R.menu.option_menu);
                pm.show();
            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myNotification();
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,1,intent,PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,"My Chennal");

                builder.setSmallIcon(R.drawable.ic_android_black_24dp);
                builder.setContentTitle("My Notification");
                builder.setContentText("Bhai O Bhai Notification Aaya H");
                builder.setContentIntent(pendingIntent);

                nm.notify(1,builder.build());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.option_menu,menu);
    }

    public void myNotification()
    {
        if(Build.VERSION.SDK_INT>=26) {
            String name = "My channel Name";
            String description="My Channel Discription";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("My Chennal", name,importance);
            notificationChannel.setDescription(description);

            nm.createNotificationChannel(notificationChannel);
        }

    }

}
