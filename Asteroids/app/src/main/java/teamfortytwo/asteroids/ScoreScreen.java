package teamfortytwo.asteroids;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ScoreScreen extends Activity implements OnClickListener{

    private ImageButton backButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);

        backButton = (ImageButton) findViewById(R.id.backbutton);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backbutton:{
                finish();
                break;
            }
            default:
                break;
        }
    }
}
