package com.steekse;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
            The objective of this exercise is to draw a "hike".

            A hike is represented by a string composed of 'U' and 'D' (up and down) delimiting the path.
            A hike should always end at the same height it starts.
            We can assume the path to always only be composed of U and D. (no tricky inputs)
            When we have a "U" , we are climbing and we represent it with a "/"
            When we have a "D" , we are descending and we represent it with a "\"
            The starting and end point of the hike are instead represented by "_"

            Below there is an example, given the input "UUDUUDDDDU" it should be drawn as below
         */

        /*
                                 /\
                              /\/  \
           UUDUUDDDDU   ->  _/      \  _
                                     \/

         */

        String hike = "UUDUUDDDDU";
        Map<Integer, String> draw = new HashMap<>();
        draw.put(0, "_");

        boolean goingUp = false;
        int high = 0;

        for(int i = 0; i < hike.length(); i++){
            char step = hike.charAt(i);
            String toDraw = draw.getOrDefault(high, "");

            // Handle going up
            if(step == 'U'){
                if(i != 0 && goingUp) {
                    toDraw += " ".repeat(i + 1);
                }
                toDraw += "/";
                draw.put(high, toDraw);
                high++;
                goingUp = true;
            }
            // Handle going down
            else{
                high--;
                toDraw = draw.getOrDefault(high, "");
                if(!goingUp){
                    for(int j = 0; j <= i + 1; j++){
                        if(toDraw.isEmpty()) {
                            toDraw = toDraw + " ";
                        }else{
                            toDraw = toDraw + " ".repeat(i - toDraw.length() + 1);
                        }
                    }
                }
                toDraw += "\\";
                draw.put(high, toDraw);
                goingUp = false;
            }
        }

        // Add final underscore to the level 0
        String finalTouch = draw.get(0) + " ".repeat(hike.length() - draw.get(0).length() + 1) + "_";
        draw.put(0, finalTouch);

        // Extract the keys and sort them in descending order
        List<Integer> keys = new ArrayList<>(draw.keySet());
        keys.sort(Collections.reverseOrder());

        // Print
        for (Integer key : keys) {
            System.out.println(draw.get(key));
        }
    }
}

