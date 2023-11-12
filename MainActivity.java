import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//main class for UI kicks things off here.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pianoButton = findViewById(R.id.pianoButton);
        pianoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a PianoSoundGenerator instance and play a piano sound
                PianoSoundGenerator piano = new PianoSoundGenerator();
                piano.playNote(261.63, 500); // Middle C
                // You can add more notes or customize the behavior here
            }
        });

        Button synthButton = findViewById(R.id.synthButton);
        synthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the SynthActivity for synth sound UI
                Intent intent = new Intent(MainActivity.this, SynthActivity.class);
                startActivity(intent);
            }
        });
    }
}
