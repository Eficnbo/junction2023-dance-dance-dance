import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
//accelerometeractivity class for accelerator.
public class AccelerometerActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastTime = 0;
    private static final int MIN_TIME_INTERVAL = 1000; // Minimum time interval between readings in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        // Initialize the sensor manager and accelerometer sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        // Register the sensor listener
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            // Handle the case where the accelerometer is not available on the device
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();

            // Check if enough time has passed since the last reading
            if (currentTime - lastTime > MIN_TIME_INTERVAL) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                // Calculate the magnitude of the acceleration vector
                float accelerationMagnitude = (float) Math.sqrt(x * x + y * y + z * z);

                // Check if the acceleration is close to zero
                if (accelerationMagnitude < 1.0) {
                    // Acceleration is approximately zero, do something
                    // You can add your logic here to handle the zero acceleration event
                }

                lastTime = currentTime;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the sensor listener to prevent memory leaks
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}
