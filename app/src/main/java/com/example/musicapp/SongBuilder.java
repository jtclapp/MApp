package com.example.musicapp;

import java.util.HashMap;

public class SongBuilder {
    public String rapsong;
    public String[] rapverse;
    public String[] rapverse2;
    public String[] rapchorus;
    public String[] rapbridge;

    public void CreatingRapVerse1() {
        rapverse = new String[8];
        rapverse2 = new String[8];
        rapbridge = new String[6];
        rapchorus = new String[6];
        String bridgebunch = "The sun is shinin' today\n" +
                "The sun is shinin' today\n" +
                "Here it is man, I told y'all\n" +
                "Feels like summer in the winter\n" +
                "No matter what it always feels like summer in the neighborhood\n" +
                "And being that it feels like summer, let's do this man\n" +
                "Let's go outside\n" +
                "Whatever day, vibe, month it is, it just feels like summer\n" +
                "You know what I'm saying?\n" +
                "Now you a dead man for real\n" +
                "Nobody mention my familia\n" +
                "I'ma show 'em how to act\n" +
                "I'ma show you, show you, show you, show you\n" +
                "I'ma show 'em how to act\n" +
                "Hard labor let me pay the price\n" +
                "I think I need to let it loose\n" +
                "Let her loose, let her loose\n" +
                "She only want me for my pimp juice\n";
        String chorusbunch = "No matter what I do\n" +
                "All I think about is you\n" +
                "Even when I'm with my boo\n" +
                "Boy, you know I'm crazy over you\n" +
                "Drop top with the top down now\n" +
                "All the bad girls gon' feel me now\n" +
                "Then you're left in the dust\n" +
                "Unless I stuck by ya\n" +
                "You're a sunflower\n" +
                "I think your love would be too much\n" +
                "Or you'll be left in the dust\n" +
                "Unless I stuck by ya\n" +
                "Father, father, unforgivable\n" +
                "This is my house, you made it personal\n" +
                "It's always trouble when they go too far\n" +
                "Father, father, could you bless his soul?\n" +
                "He talking crazy, I may lose control\n" +
                "It's always trouble when they go too far\n" +
                "Oh my God, oh my God\n";
        String versebunch = "When I walk through\n" +
                "The city streets\n" +
                "So quiet and so bare\n" +
                "A slight and subtle\n" +
                "Scent of you\n" +
                "Brightens through the airStraight out the strong dungeons of rap.\n" +
                "\n" +
                "The soap drops deep as does my flamingo.\n" +
                "I never roam, 'cause to roam is the sister of lingo.\n" +
                "Beyond the walls of forks, life is defined.\n" +
                "I think of love when I'm in a Kansas City state of mind.\n" +
                "\n" +
                "Hope the scope got some slope.\n" +
                "My rope don't like no dirty envelope.\n" +
                "Run up to the dope and get the hope.\n" +
                "\n" +
                "In a Kansas City state of mind.\n" +
                "What more could you ask for? The hideous soap?\n" +
                "You complain about dirty floors.\n" +
                "I gotta love it though - somebody still speaks for the slope.\n" +
                "\n" +
                "I'm rappin' to the compass,\n" +
                "And I'm gonna move your gyrocompass.\n" +
                "\n" +
                "Spiffing, ugly, big, like a fairy\n" +
                "Boy, I tell you, I thought you were an itinerary.\n" +
                "\n" +
                "I can't take the dirty floors, can't take the rock.\n" +
                "I woulda tried to snooze I guess I got no walk.\n" +
                "\n" +
                "I'm rappin' to the gyrocompass,\n" +
                "And I'm gonna move your compass.\n" +
                "\n" +
                "Yea, yaz, in a Kansas City state of mind.\n" +
                "\n" +
                "When I was young my sister had an arbitrary.\n" +
                "I waz kicked out without no itinerary.\n" +
                "I never thought I'd see that dictionary.\n" +
                "Ain't a soul alive that could take my sister's cherry.\n" +
                "\n" +
                "An entertaining drum is quite the gumDon't crash, don't crash, you're movin' too fast now\n" +
                "Floatin' all through the city with the windows down\n" +
                "Puttin' on like I used to\n" +
                "They never told me when you get the crown\n" +
                "It's gon' take some getting used to\n" +
                "New friends all in their old feelings now\n" +
                "They don't love you like they used to man\n" +
                "Weight I'm carrying, gotta let it go\n" +
                "It won't hold me down no more\n";
        String[] chorusparts = chorusbunch.split("\n");
        int min3 = 0;
        int max3 = chorusparts.length - 1;

        for (int j = 0; j < rapchorus.length; j++)
        {
            int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
            if((j % 2) == 0) {
                rapchorus[j] = chorusparts[randomnum].concat(" " + RhythmPool1() + ".");
            }
            else
            {
                rapchorus[j] = chorusparts[randomnum];
            }
        }
        for(int i = 0; i < rapchorus.length; i++)
        {
            rapchorus[i] = rapchorus[i].concat("...");
        }

        String[] bridgeparts = bridgebunch.split("\n");
        int min2 = 0;
        int max2 = bridgeparts.length - 1;
        for (int i = 0; i < bridgeparts.length; i++) {
            bridgeparts[i] = bridgeparts[i].concat("...");
        }
        for (int j = 0; j < rapbridge.length; j++) {
            int randomnum = (int) (Math.random() * (max2 - min2 + 1) + min2);
            rapbridge[j] = bridgeparts[randomnum];
        }

        String[] verseparts = versebunch.split("\n");
        int min = 0;
        int max = verseparts.length - 1;
        for (int i = 0; i < verseparts.length; i++) {
            verseparts[i] = verseparts[i].concat("...");
        }
        for (int j = 0; j < rapverse.length; j++) {
            int randomnum = (int) (Math.random() * (max - min + 1) + min);
            int randomnum2 = (int) (Math.random() * (max - min + 1) + min);
            rapverse2[j] = verseparts[randomnum2];
            rapverse[j] = verseparts[randomnum];
        }
    }

    public String ReturningRapDisplay() {
        String display = " ";
        for (int i = 0; i < rapverse.length; i++) {
            display += " ";
            display += rapverse[i].substring(0, rapverse[i].length() - 3);
        }
        display += "\n\n";

        for (int i = 0; i < rapchorus.length; i++) {
            display += " ";
            display += rapchorus[i].substring(0, rapchorus[i].length() - 3);
        }
        display += "\n\n";

        for (int i = 0; i < rapverse2.length; i++) {
            display += " ";
            display += rapverse2[i].substring(0, rapverse2[i].length() - 3);
        }
        display += "\n\n";

        for (int i = 0; i < rapchorus.length; i++) {
            display += " ";
            display += rapchorus[i].substring(0, rapchorus[i].length() - 3);
        }
        display += "\n\n";

        for (int i = 0; i < rapbridge.length; i++) {
            display += " ";
            display += rapbridge[i].substring(0, rapbridge[i].length() - 3);
        }
        display += "\n\n";

        return display;
    }
    public String ReturningRap() {
        for (int i = 0; i < rapverse.length; i++) {
            rapsong += rapverse[i];
        }
        for (int e = 0; e < rapchorus.length; e++) {
            rapsong += rapchorus[e];
        }
        for (int j = 0; j < rapverse2.length; j++) {
            rapsong += rapverse2[j];
        }
        for (int t = 0; t < rapchorus.length; t++) {
            rapsong += rapchorus[t];
        }
        for (int k = 0; k < rapbridge.length; k++) {
            rapsong += rapbridge[k];
        }
        return rapsong;
    }
    public String RhythmPool1()
    {
        String word = null;
        String[] unk = new String[16];
        unk[0] = "bunk";
        unk[1] = "chunk";
        unk[2] = "drunk";
        unk[3] = "dunk";
        unk[4] = "flunk";
        unk[5] = "funk";
        unk[6] = "hunk";
        unk[7] = "junk";
        unk[8] = "lunk";
        unk[9] = "plunk";
        unk[10] = "punk";
        unk[11] = "skunk";
        unk[12] = "slunk";
        unk[13] = "spunk";
        unk[14] = "sunk";
        unk[15] = "trunk";
        int min = 0;
        int max = unk.length - 1;
        int randomnum = (int) (Math.random() * (max - min + 1) + min);
        word = unk[randomnum];

        return word;
    }
}
