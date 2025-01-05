package com.example.adhd_wid;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import java.util.Random;

public class jam_interface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jam_board);

        // Get reference to the small button
        Button smallButton = findViewById(R.id.button);

        // Get reference to the FlexboxLayout
        FlexboxLayout flexboxLayout = findViewById(R.id.flexbox);

        // Set the FlexboxLayout direction to COLUMN to arrange buttons vertically
        flexboxLayout.setFlexDirection(com.google.android.flexbox.FlexDirection.COLUMN);

        // Set an OnClickListener on the small button
        smallButton.setOnClickListener(v -> addNewButton(flexboxLayout));

        // Add a layout change listener to ensure all buttons remain square
        flexboxLayout.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            adjustButtonSizes(flexboxLayout);
        });
    }

    private void addNewButton(FlexboxLayout flexboxLayout) {
        // Create a new button
        Button newButton = new Button(this);
        newButton.setText("cue");

        // Measure the button to calculate the minimum size based on the text
        newButton.measure(0, 0);
        int minTextWidth = newButton.getMeasuredWidth();
        int minTextHeight = newButton.getMeasuredHeight();

        // Set the minimum button size based on the text dimensions
        int minButtonSize = Math.max(minTextWidth, minTextHeight);

        // Define the maximum size (e.g., 2 times the minimum size)
        int maxButtonSize = minButtonSize * 2;

        // Generate a random button size within the min and max range
        Random random = new Random();
        int buttonSize = random.nextInt(maxButtonSize - minButtonSize + 1) + minButtonSize;

        // Create LayoutParams for the button
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                buttonSize, // Set width
                buttonSize  // Set height to ensure the button is square
        );
        params.setMargins(10, 10, 10, 10); // Optional margins for spacing

        // Apply layout parameters to the button
        newButton.setLayoutParams(params);

        // Optionally, set a background color for the button
        newButton.setBackgroundColor(Color.parseColor("#FF6200EE"));

        // Add the button to the FlexboxLayout
        flexboxLayout.addView(newButton);
    }

    private void adjustButtonSizes(FlexboxLayout flexboxLayout) {
        // Iterate through all child views of the FlexboxLayout
        for (int i = 0; i < flexboxLayout.getChildCount(); i++) {
            Button button = (Button) flexboxLayout.getChildAt(i);

            // Get the current height of the button
            int buttonHeight = button.getHeight();

            // Update the width to match the height to make it square
            FlexboxLayout.LayoutParams params = (FlexboxLayout.LayoutParams) button.getLayoutParams();
            params.width = buttonHeight; // Set width equal to height
            button.setLayoutParams(params); // Apply updated params
        }
    }
}
