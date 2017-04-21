package com.megster.cordova;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.AsyncTask;
import android.util.Log;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

public class PairingReceiver extends BroadcastReceiver {

    private static final String TAG = "BluetoothSerialService";

    @Override
    public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "PairingReceiver started");
                    try {
                        final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        String devicePin = BluetoothSerial.getDEVICE_PIN();
                        Log.i(TAG,"pin=" + devicePin);
                        if (intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST") && devicePin != null && !devicePin.equals("")) {
                            byte[] pin = (byte[]) BluetoothDevice.class.getMethod("convertPinToBytes", String.class).invoke(BluetoothDevice.class, BluetoothSerial.getDEVICE_PIN());
                            BluetoothDevice.class.getMethod("setPin", byte[].class).invoke(device, pin);
                        }
                    } catch (Exception e) {

                        Log.e(TAG, "dunno", e);
                    }
                    Log.i(TAG, "PairingReceiver finished");

    }
}