package com.music.songcreator.java_operations;
import androidx.appcompat.app.AppCompatActivity;

public class BeatFileSelector extends AppCompatActivity
{
    String path;

    public String FileSelector(int num)
    {
        switch (num)
        {
            case 1:
               path = "HipHop_Beat#1.mp3";
               break;
            case 2:
                path = "HipHop_Beat#2.mp3";
                break;
            case 3:
                path = "HipHop_Beat#3.mp3";
                break;
            case 24:
                path = "HipHop_Beat#4.mp3";
                break;
            case 25:
                path = "HipHop_Beat#5.mp3";
                break;
            case 26:
                path = "HipHop_Beat#6.mp3";
                break;
            case 27:
                path = "HipHop_Beat#7.mp3";
                break;
            case 4:
                path = "Rock_Beat#1.mp3";
                break;
            case 5:
                path = "Rock_Beat#2.mp3";
                break;
            case 6:
                path = "Rock_Beat#3.mp3";
                break;
            case 7:
                path = "Rock_Beat#4.mp3";
                break;
            case 29:
                path = "Rock_Beat#5.mp3";
                break;
            case 8:
                path = "R&B_Beat#1.mp3";
                break;
            case 9:
                path = "R&B_Beat#2.mp3";
                break;
            case 10:
                path = "R&B_Beat#3.mp3";
                break;
            case 11:
                path = "R&B_Beat#4.mp3";
                break;
            case 35:
                path = "R&B_Beat#5.mp3";
                break;
            case 36:
                path = "R&B_Beat#6.mp3";
                break;
            case 13:
                path = "Country_Beat#1.mp3";
                break;
            case 14:
                path = "Country_Beat#2.mp3";
                break;
            case 15:
                path = "Country_Beat#3.mp3";
                break;
            case 16:
                path = "Country_Beat#4.mp3";
                break;
            case 17:
                path = "Country_Beat#5.mp3";
                break;
        }
        return path;
    }
}
