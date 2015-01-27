package jp.example.bluetooth_01;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private BluetoothAdapter mBluetoothAdapter;				//BTアダプタ
	private BluetoothDevice  mBtDevice;						//BTデバイス
	private BluetoothSocket  mBtSocket;						//BTソケット	
	private OutputStream     mOutput;						//出力ストリーム
	private Button           btn1, btn2, btn3, btn4, btn5 ;	//ボタン	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ボタンのインスタンスを取得
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
		btn5 = (Button) findViewById(R.id.button5);
		
		//BTの準備------------------------------------------------------------------
		//BTアダプタのインスタンスを取得
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		//相手のBTデバイスのインスタンスを取得
		mBtDevice = mBluetoothAdapter.getRemoteDevice("00:17:A0:01:87:7A");
		
		//BTソケットのインスタンスを取得
		try {
			//接続に使用するプロファイルを指定
			mBtSocket = mBtDevice.createRfcommSocketToServiceRecord(
					UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//ソケットを接続する
		try {
			mBtSocket.connect();
			mOutput = mBtSocket.getOutputStream();	//出力ストリームオブジェクトを得る。
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//ボタン１のイベント
		btn1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				try {
					mOutput.write('f');
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
			}			
		});
		
		//ボタン２のイベント
		btn2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				try {
					mOutput.write('l');
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		//ボタン３のイベント
		btn3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				try {
					mOutput.write('r');
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});

		//ボタン４のイベント
		btn4.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				try {
					mOutput.write('b');
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		
		//ボタン５のイベント
		btn5.setOnClickListener(new View.OnClickListener(){
		public void onClick(View v) {
			
			try {
				mOutput.write('s');
			}
			catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
		});
		
		
		
	}
	
	


	
	private void setContentView(int activityMain) {
		// TODO Auto-generated method stub
		
	}





	@Override	
	protected void onDestroy() {
		super.onDestroy();
		
		//ソケットを閉じる
		try {
			mBtSocket.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
