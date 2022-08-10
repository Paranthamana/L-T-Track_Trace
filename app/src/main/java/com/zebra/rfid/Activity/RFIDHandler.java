package com.zebra.rfid.Activity;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zebra.rfid.api3.ACCESS_OPERATION_CODE;
import com.zebra.rfid.api3.ACCESS_OPERATION_STATUS;
import com.zebra.rfid.api3.Antennas;
import com.zebra.rfid.api3.ENUM_TRANSPORT;
import com.zebra.rfid.api3.ENUM_TRIGGER_MODE;
import com.zebra.rfid.api3.HANDHELD_TRIGGER_EVENT_TYPE;
import com.zebra.rfid.api3.InvalidUsageException;
import com.zebra.rfid.api3.OperationFailureException;
import com.zebra.rfid.api3.RFIDReader;
import com.zebra.rfid.api3.ReaderDevice;
import com.zebra.rfid.api3.Readers;
import com.zebra.rfid.api3.RfidEventsListener;
import com.zebra.rfid.api3.RfidReadEvents;
import com.zebra.rfid.api3.RfidStatusEvents;
import com.zebra.rfid.api3.START_TRIGGER_TYPE;
import com.zebra.rfid.api3.STATUS_EVENT_TYPE;
import com.zebra.rfid.api3.STOP_TRIGGER_TYPE;
import com.zebra.rfid.api3.TagData;
import com.zebra.rfid.api3.TriggerInfo;

import java.util.ArrayList;

class RFIDHandler implements Readers.RFIDReaderEventHandler {

    final static String TAG = "RFID_SAMPLE";
    private static Readers readers;
    private static ArrayList<ReaderDevice> availableRFIDReaderList;
    private static ReaderDevice readerDevice;
    private static RFIDReader reader;
    private EventHandler eventHandler;
    TextView textView;
    private MainActivity context;
    private ScanLocationActivity mActivity;
    private int MAX_POWER = 1;
    String readerName = "RFD8500123";

    void onCreate(MainActivity activity) {
        context = activity;
        textView = activity.statusTextViewRFID;
        InitSDK();
        Test1();
    }

    public String Test1() {
        if (!isReaderConnected())
            return "Not connected";
        try {
            Antennas.AntennaRfConfig config = null;
            config = reader.Config.Antennas.getAntennaRfConfig(1);
            config.setTransmitPowerIndex(1);
            config.setrfModeTableIndex(0);
            config.setTari(0);
            reader.Config.Antennas.setAntennaRfConfig(1, config);
        } catch (InvalidUsageException e) {
            e.printStackTrace();
        } catch (OperationFailureException e) {
            e.printStackTrace();
            return e.getResults().toString() + " " + e.getVendorMessage();
        }
        return "Antenna power Set to 220";
    }

    private boolean isReaderConnected() {
        if (reader != null && reader.isConnected())
            return true;
        else {
            Log.d(TAG, "reader is not connected");
            return false;
        }
    }

    String onResume() {
        return connect();
    }

    void onPause() {
        disconnect();
    }

    void onDestroy() {
        dispose();
        try {
            if (reader != null) {
                reader.Events.removeEventsListener(eventHandler);
                reader.disconnect();
                Toast.makeText(getApplicationContext(), "Disconnecting reader", Toast.LENGTH_LONG).show();
                reader = null;
                readers.Dispose();
                readers = null;
            }
        } catch (InvalidUsageException e) {
            e.printStackTrace();
        } catch (OperationFailureException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void InitSDK() {
        Log.d(TAG, "InitSDK");
        if (readers == null) {
            new CreateInstanceTask().execute();
        } else
            new ConnectionTask().execute();
    }


    private class CreateInstanceTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "CreateInstanceTask");
            // Based on support available on host device choose the reader type
            try {
                InvalidUsageException invalidUsageException = null;
                readers = new Readers(context, ENUM_TRANSPORT.ALL);
                //------------
                availableRFIDReaderList = readers.GetAvailableRFIDReaderList();
                if (invalidUsageException != null) {
                    readers.Dispose();
                    readers = null;
                    if (readers == null) {
                        readers = new Readers(mActivity, ENUM_TRANSPORT.BLUETOOTH);//------------
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new ConnectionTask().execute();
        }
    }

    private class ConnectionTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG, "ConnectionTask");
            GetAvailableReader();
            if (reader != null)
                return connect();
            return "Failed to find or connect reader";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            textView.setText(result);
        }
    }

    private synchronized void GetAvailableReader() {
        Log.d(TAG, "GetAvailableReader");
        if (readers != null) {
            readers.attach(this);
            if (readers.GetAvailableRFIDReaderList() != null) {
                availableRFIDReaderList = readers.GetAvailableRFIDReaderList();
                if (availableRFIDReaderList.size() != 0) {
                    // if single reader is available then connect it
                    if (availableRFIDReaderList.size() == 1) {
                        readerDevice = availableRFIDReaderList.get(0);
                        reader = readerDevice.getRFIDReader();
                    } else {
                        // search reader specified by name
                        for (ReaderDevice device : availableRFIDReaderList) {
                            if (device.getName().equals(readerName)) {
                                readerDevice = device;
                                reader = readerDevice.getRFIDReader();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void RFIDReaderAppeared(ReaderDevice readerDevice) {
        Log.d(TAG, "RFIDReaderAppeared " + readerDevice.getName());
        new ConnectionTask().execute();
    }

    @Override
    public void RFIDReaderDisappeared(ReaderDevice readerDevice) {
        Log.d(TAG, "RFIDReaderDisappeared " + readerDevice.getName());
        if (readerDevice.getName().equals(reader.getHostName()))
            disconnect();
    }


    private synchronized String connect() {
        if (reader != null) {
            Log.d(TAG, "connect " + reader.getHostName());
            try {
                if (!reader.isConnected()) {
                    // Establish connection to the RFID Reader
                    try {
                        reader.connect();
                        ConfigureReader();
                        return "Reader Connected";

                    } catch (InvalidUsageException e) {
                        e.printStackTrace();
                    } catch (OperationFailureException e) {
                        e.printStackTrace();
                        Log.d(TAG, "OperationFailureException " + e.getVendorMessage());
                        String des = e.getResults().toString();
                        return "Connection failed";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private void ConfigureReader() {
        try {
            Log.d(TAG, "ConfigureReader " + reader.getHostName());
            if (reader.isConnected()) {
                TriggerInfo triggerInfo = new TriggerInfo();
                triggerInfo.StartTrigger.setTriggerType(START_TRIGGER_TYPE.START_TRIGGER_TYPE_IMMEDIATE);
                triggerInfo.StopTrigger.setTriggerType(STOP_TRIGGER_TYPE.STOP_TRIGGER_TYPE_IMMEDIATE);
                try {
                    // receive events from reader
                    if (eventHandler == null)
                        eventHandler = new EventHandler();
                    reader.Events.addEventsListener(eventHandler);
                    // HH event
                    reader.Events.setHandheldEvent(true);
                    // tag event with tag data
                    reader.Events.setTagReadEvent(true);
                    reader.Events.setAttachTagDataWithReadEvent(false);
                    // set trigger mode as rfid so scanner beam will not come
                    reader.Config.setTriggerMode(ENUM_TRIGGER_MODE.RFID_MODE, true);
                    // set start and stop triggers
                    reader.Config.setStartTrigger(triggerInfo.StartTrigger);
                    reader.Config.setStopTrigger(triggerInfo.StopTrigger);

                } catch (InvalidUsageException | OperationFailureException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void disconnect() {
        Log.d(TAG, "disconnect " + reader);
        try {
            if (reader != null) {
                reader.Events.removeEventsListener(eventHandler);
                reader.disconnect();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Disconnected");
                    }
                });
            }
        } catch (InvalidUsageException e) {
            e.printStackTrace();
        } catch (OperationFailureException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void dispose() {
        try {
            if (readers != null) {
                reader = null;
                readers.Dispose();
                readers = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized void performInventory() {
        if (!isReaderConnected())
            return;
        try {
            reader.Actions.Inventory.perform();
        } catch (InvalidUsageException | OperationFailureException e) {
            e.printStackTrace();
        }
    }

    synchronized void stopInventory() {
        if (!isReaderConnected())
            return;
        try {
            reader.Actions.Inventory.stop();
        } catch (InvalidUsageException | OperationFailureException e) {
            e.printStackTrace();
        }
    }

    // Read/Status Notify handler
    // Implement the RfidEventsLister class to receive event notifications
    public class EventHandler implements RfidEventsListener {
        // Read Event Notification
        public void eventReadNotify(RfidReadEvents e) {
            // Recommended to use new method getReadTagsEx for better performance in case of large tag population
            TagData[] myTags = reader.Actions.getReadTags(100);
            if (myTags != null) {
                for (int index = 0; index < myTags.length; index++) {
                    //Log.d(TAG, "Tag ID " + myTags[index].getTagID());
                    if (myTags[index].getOpCode() == ACCESS_OPERATION_CODE.ACCESS_OPERATION_READ &&
                            myTags[index].getOpStatus() == ACCESS_OPERATION_STATUS.ACCESS_SUCCESS) {
                        if (myTags[index].getMemoryBankData().length() > 0) {
                            Log.d(TAG, " Mem Bank Data " + myTags[index].getMemoryBankData());
                        }
                    }
                    if (myTags[index].isContainsLocationInfo()) {
                        short dist = myTags[index].LocationInfo.getRelativeDistance();
                        Log.d(TAG, "Tag relative distance " + dist);
                    }
                }
                // possibly if operation was invoked from async task and still busy
                // handle tag data responses on parallel thread thus THREAD_POOL_EXECUTOR
                new AsyncDataUpdate().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, myTags);
            }
        }

        // Status Event Notification
        public void eventStatusNotify(RfidStatusEvents rfidStatusEvents) {
            Log.d(TAG, "Status Notification: " + rfidStatusEvents.StatusEventData.getStatusEventType());
            if (rfidStatusEvents.StatusEventData.getStatusEventType() == STATUS_EVENT_TYPE.HANDHELD_TRIGGER_EVENT) {
                if (rfidStatusEvents.StatusEventData.HandheldTriggerEventData.getHandheldEvent() == HANDHELD_TRIGGER_EVENT_TYPE.HANDHELD_TRIGGER_PRESSED) {

                    new AsyncTask<Void, Void, Void>() {
                        @SuppressLint("StaticFieldLeak")
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                context.handleTriggerPress(true);
                                //------------
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }.execute();

                }
                if (rfidStatusEvents.StatusEventData.HandheldTriggerEventData.getHandheldEvent() == HANDHELD_TRIGGER_EVENT_TYPE.HANDHELD_TRIGGER_RELEASED) {
                    new AsyncTask<Void, Void, Void>() {
                        @SuppressLint("StaticFieldLeak")
                        @Override
                        protected Void doInBackground(Void... voids) {
                            context.handleTriggerPress(false);
                            //------------
                            return null;
                        }
                    }.execute();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncDataUpdate extends AsyncTask<TagData[], Void, Void> {
        @Override
        protected Void doInBackground(TagData[]... params) {
            context.handleTagdata(params[0]);
            //------------
            return null;
        }
    }

    interface ResponseHandlerInterface {
        void handleTagdata(TagData[] tagData);

        void handleTriggerPress(boolean pressed);
        //void handleStatusEvents(Events.StatusEventData eventData);
    }


}
