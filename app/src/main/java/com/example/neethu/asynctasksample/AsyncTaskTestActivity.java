package com.example.neethu.asynctasksample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class AsyncTaskTestActivity extends AppCompatActivity {
    Button click;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new PostTask().execute("http://feeds.pcworld.com/pcworld/latestnews");
            }
        });

    }

    private class PostTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            displayProgressBar();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            for (int i = 0; i <= 100; i += 5) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return "All Done";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            updateProgressBar(values[0]);

        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
            dismissProgressBar();
        }
    }

    private void displayProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void updateProgressBar(Integer value) {
        progressBar.setProgress(value);
    }

    private void dismissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


}
