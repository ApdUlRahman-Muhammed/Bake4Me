package com.example.android.body;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.body.Model.StepsModel;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.example.android.body.IngredientsAndSteps.recipeModel;

public class VidoesActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageButton previous, next;

    private ArrayList<StepsModel> stepModels = new ArrayList<>();
    private FragmentStepDetails fragmentStepDetails;
    private int stepIndex;
    public int orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidoes);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(recipeModel.getName());
        previous = (ImageButton) findViewById(R.id.previous_step_ps);
        next = (ImageButton) findViewById(R.id.next_step);

        if (getIntent() != null && getIntent().hasExtra("steps") && getIntent().hasExtra("stepIndex")) {
            stepModels = getIntent().getParcelableArrayListExtra("steps");
            if (savedInstanceState == null) {
                stepIndex = getIntent().getIntExtra("stepIndex", 0);
            } else {
                stepIndex = savedInstanceState.getInt("stepIndex");
            }
            updateNavigation(stepIndex);
        }

        setFragment(savedInstanceState, stepIndex);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stepIndex = stepIndex - 1;
                setFragment(null, stepIndex);
                updateNavigation(stepIndex);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stepIndex =stepIndex+1;
                setFragment(null, stepIndex);
                updateNavigation(stepIndex);

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (fragmentStepDetails.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "stepDetailFragment", fragmentStepDetails);
        }
        outState.putInt("currentStepIndex", stepIndex);
        super.onSaveInstanceState(outState);
    }

    private void setFragment(Bundle savedInstanceState, int stepIndex) {
        // setting toolbar first
        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(stepModels.get(stepIndex).getShortDescription());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState != null) {
            fragmentStepDetails = (FragmentStepDetails) getSupportFragmentManager().
                    getFragment(savedInstanceState, "stepDetailFragment");
        } else {
            fragmentStepDetails = FragmentStepDetails.newInstance(stepModels.get(stepIndex));
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentStepDetails)
                .commit();
    }

    private void updateNavigation(int stepIndex) {
        if (stepModels.size() > 1) {

            if (stepIndex == 0) {

                previous.setVisibility(View.INVISIBLE);

            }

            else if (stepIndex == stepModels.size() - 1) {

                next.setVisibility(View.INVISIBLE);


            }
           else
            {
                next.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);

            }
        } else {

            previous.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        orientation = newConfig.orientation;
        onWindowFocusChanged(true);
    }
}
