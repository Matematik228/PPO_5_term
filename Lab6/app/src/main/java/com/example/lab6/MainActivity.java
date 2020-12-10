package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import ru.bartwell.exfilepicker.ExFilePicker;
import ru.bartwell.exfilepicker.data.ExFilePickerResult;

public class MainActivity extends AppCompatActivity {
    private static final int EX_FILE_PICKER_RESULT = 0;
    private String path = "/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView vw = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        vw.setMediaController(mediaController);
        mediaController.setMediaPlayer(vw);
    }

    public void onChoseFileClick(View view) {
        ExFilePicker exFilePicker = new ExFilePicker();
        exFilePicker.setCanChooseOnlyOneItem(true);
        exFilePicker.start(this, EX_FILE_PICKER_RESULT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EX_FILE_PICKER_RESULT) {
            ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
            if (result != null && result.getCount() > 0) {
                path = result.getPath() + result.getNames().get(0);
                ((TextView) findViewById(R.id.textView)).setText(path);
                VideoView vw = (VideoView) findViewById(R.id.videoView);
                vw.setVideoPath(path);
            }
        }
        else super.onActivityResult(requestCode, resultCode, data);
    }
    public void onPlayClicked(View view) {
        try {
            VideoView vw = (VideoView) findViewById(R.id.videoView);
            //vw.setVideoPath(path);
            vw.start();
        }
        catch (Exception exc)
        {
            ;
        }
    }
    public void onPauseClicked(View view) {
        VideoView vw = (VideoView) findViewById(R.id.videoView);
        if(vw.canPause())
            vw.pause();
    }

    public void onStopClicked(View view) {
        VideoView vw = (VideoView) findViewById(R.id.videoView);
        vw.stopPlayback();
        vw.setVideoPath(path);
    }

}