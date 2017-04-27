package rtrk.aplikacija;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    private Button dugme1;
    private Button dugme2;
    private Button dugme3;
    private Intent intent;
    private EditText set;
    private EditText get;
    private ServiceConnection mServiceConnection;
    private IMyAidlInterface mBinderInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dugme1 = (Button) findViewById(R.id.button);
        dugme2 = (Button) findViewById(R.id.button2);
        dugme3 = (Button) findViewById(R.id.button3);
        intent = new Intent(this,BinderService.class);
        set = (EditText) findViewById(R.id.editText);
        get = (EditText) findViewById(R.id.editText2);
        mServiceConnection = this;
        dugme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });

        dugme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("SET",String.valueOf(set.getText()));
                    mBinderInterface.setValue(Integer.parseInt(set.getText().toString()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        dugme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("GET", String.valueOf(mBinderInterface.getValue()));
                    get.setText(Integer.toString(mBinderInterface.getValue()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("HELLO","HELLO WORLD");
        mBinderInterface = IMyAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
