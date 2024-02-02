package com.example.week3live;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public void SimpleAlert2(View view) {
        DialogFragment newFragment = MyAlertDialog.newInstance(R.string.alert_dialog_title);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void SimpleAlert3(View view) {
        DialogFragment newFragment = MyAlertDialogWithList.newInstance(R.string.alert_dialog_title,R.array.list_items);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void SimpleAlert4(View view) {
        DialogFragment newFragment = MyAlertDialogWithMultiList.newInstance(R.string.alert_dialog_title,R.array.list_items);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void SimpleAlert5(View view) {
        DialogFragment newFragment = MyAlertDialogWithSingleList.newInstance(R.string.alert_dialog_title,R.array.list_items);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void Next(View view) {
       startActivity(new Intent(this, SplashScreen.class));
    }
    public static class MyAlertDialog extends DialogFragment
    {
        public static MyAlertDialog newInstance(int title){
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            Bundle args = new Bundle();
            args.putInt("title", title);
            myAlertDialog.setArguments(args);
            return myAlertDialog;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_alert_foreground)
                    .setTitle(title).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "CANCEL", Toast.LENGTH_SHORT).show();
                        }
                    }).create();
        }
    }

    public static class MyAlertDialogWithList extends DialogFragment
    {
        public static MyAlertDialogWithList newInstance(int title, int list){
            MyAlertDialogWithList myAlertDialog=new MyAlertDialogWithList();
            Bundle args = new Bundle();
            args.putInt("title", title);
            args.putInt("list", list);
            myAlertDialog.setArguments(args);
            return myAlertDialog;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            int list = getArguments().getInt("list");
            return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_alert_foreground)
                    .setTitle(title).setItems(list, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "Selected Item = "+Integer.toString(which), Toast.LENGTH_SHORT).show();
                        }
                    }).create();
        }
    }

    public static class MyAlertDialogWithMultiList extends DialogFragment
    {
        ArrayList selectedItems;
        public static MyAlertDialogWithMultiList newInstance(int title, int list){
            MyAlertDialogWithMultiList myAlertDialog=new MyAlertDialogWithMultiList();
            Bundle args = new Bundle();
            args.putInt("title", title);
            args.putInt("list", list);
            myAlertDialog.setArguments(args);
            return myAlertDialog;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            int list = getArguments().getInt("list");
            selectedItems= new ArrayList();
            return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_alert_foreground)
                    .setTitle(title).setMultiChoiceItems(list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if(isChecked){
                                selectedItems.add(which);
                            }
                            else if(selectedItems.contains(which))
                            {
                                selectedItems.remove(which);
                            }
                        }
                    }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "# of items Selected = "+selectedItems.size(), Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                        }
                    }).create();
        }
    }

    public static class MyAlertDialogWithSingleList extends DialogFragment
    {
        int curr_selection;
        public static MyAlertDialogWithSingleList newInstance(int title, int list){
            MyAlertDialogWithSingleList myAlertDialog=new MyAlertDialogWithSingleList();
            Bundle args = new Bundle();
            args.putInt("title", title);
            args.putInt("list", list);
            myAlertDialog.setArguments(args);
            return myAlertDialog;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            int list = getArguments().getInt("list");

            return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_alert_foreground)
                    .setTitle(title).setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            curr_selection = which;
                        }
                    }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String[] stringArray = getResources().getStringArray(R.array.list_items);
                            Toast.makeText(getActivity(), "Selected Item = "+stringArray[curr_selection], Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                        }
                    }).create();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SendMessage(View view) {
        EditText editText=findViewById(R.id.messageText);
        String message = editText.getText().toString();
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        //To force an app chooser

        String title = "Send via";
        Intent chooser = Intent.createChooser(intent, title);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(chooser);
        }
    }

    public void StartNavigation(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=Syracuse+University,+Syracuse+NY");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");
        if(mapIntent.resolveActivity(getPackageManager())!=null){
            startActivity(mapIntent);
        } else{
            Toast.makeText(this, "We could not find an application to handle the request.", Toast.LENGTH_SHORT).show();
        }

    }

    public void SimpleAlert(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to exit?");
        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Yes, clicked.", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNeutralButton("NOT SURE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Not sure", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "No clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}