package com.example.musicapp;

import java.util.HashMap;

public class SongBuilder {
    public String rapsong;
    public String[] rapverse;
    public String[] rapverse2;
    public String[] rapchorus;
    public String[] rapbridge;

    public void CreatingRapVerse1() {
        rapverse = new String[7];
        rapverse2 = new String[7];
        rapbridge = new String[3];
        rapchorus = new String[5];
        String bridgebunch = "The sun is shinin' today\n" +
                "The sun is shinin' today\n" +
                "Here it is man, I told y'all\n" +
                "Feels like summer in the winter\n" +
                "No matter what it always feels like summer in the neighborhood\n" +
                "And being that it feels like summer, let's do this man\n" +
                "Whatever day, vibe, month it is, it just feels like summer\n" +
                "You know what I'm saying?\n" +
                "Now you a dead man for real\n" +
                "Nobody mention my familia\n" +
                "I'ma show 'em how to act\n" +
                "I'ma show you, show you, show you, show you\n" +
                "I'ma show 'em how to act\n" +
                "Hard labor let me pay the price\n" +
                "I think I need to let it loose\n" +
                "Ooh love ya baby oh oh\n" +
                "Love ya baby oh yeah";

        String chorusbunch = "But not the kinda love that will be in vain\n" +
                "I wanna love, love you foreverGirl of my dreams, I wanna hold her\n" +
                "Share my life and love you, do everything\n" +
                "You got to make sure you can love somebody\n" +
                "And no woman that I know loves me this wayLove is love God is God\n" +
                "It always comes as no surprise\n" +
                "Love never lets you goSometimes you make me think. I love you\n" +
                "Sometimes you make me sing\n" +
                "Love makes the world go around, make a world go around\nCause I love dem\n" +
                "But I love dem\n" +
                "Where is the love? Where is love?And no woman that I know loves me this way\n" +
                "But I know some people that they really love me\n" +
                "I love everybody with their arm around them";

        String versebunch = "Straight out the pointy dungeons of rap.\n" +
                "The angel drops deep as does my money.\n" +
                "I never smile, 'cause to smile is the son of blini.\n" +
                "Beyond the walls of arms, life is defined.\n" +
                "I think of muppets when I'm in a Prague state of mind.\n" +
                "Hope the trick got some stick.\n" +
                "My sic don't like no dirty dick.\n" +
                "Run up to the tick and get the kick.\n" +
                "In a Prague state of mind.\n" +
                "What more could you ask for? The moist angel?\n" +
                "You complain about famine.\n" +
                "I gotta love it though - somebody still speaks for the black archangel.\n" +
                "I'm rappin' to the cat.\n" +
                "And I'm gonna move your a.\n" +
                "Chewy, great, rancid, like a joystick\n" +
                "Boy, I tell you, I thought you were a sic.\n" +
                "I can't take the famine, can't take the apple.\n" +
                "I woulda tried to sleep I guess I got no dapple.\n" +
                "I'm rappin' to the streets.\n" +
                "And I'm gonna move your cat.\n" +
                "Yea, yaz, in a Prague state of mind.\n" +
                "When I was young my son had a honey.\n" +
                "I waz kicked out without no blini.\n" +
                "I never thought I'd see that sunny.\n" +
                "Ain't a soul alive that could take my son's gunny.\n" +
                "A little banana is quite the americana.\n" +
                "Thinking of muppets. Yaz, thinking of muppets.\n" +
                "The snail drops deep as does my heart.\n" +
                "I never tickle, 'cause to tickle is the husband of start.\n" +
                "Beyond the walls of shoes, life is defined.\n" +
                "I think of cheese when I'm in a Washington state of mind.\n" +
                "Hope the art got some start.\n" +
                "My smart don't like no dirty part.\n" +
                "Run up to the counterpart and get the chart.\n" +
                "In a Washington state of mind.\n" +
                "What more could you ask for? The pointless snail?\n" +
                "You complain about chickens.\n" +
                "I gotta love it though - somebody still speaks for the trail.\n" +
                "I'm rappin' to the head,\n" +
                "And I'm gonna move your lead.\n" +
                "Wild, mild, crazy, like a shoe\n" +
                "Boy, I tell you, I thought you were an eschew.\n" +
                "I can't take the chickens, can't take the flamingo.\n" +
                "I woulda tried to sing I guess I got no dingo.\n" +
                "I'm rappin' to the lead,\n" +
                "And I'm gonna move your head.\n" +
                "Yea, yaz, in a Washington state of mind.\n" +
                "When I was young my husband had an entail.\n" +
                "I waz kicked out without no scale.\n" +
                "I never thought I'd see that rail.\n" +
                "Ain't a soul alive that could take my husband's hail.\n" +
                "A smooth pineapple is quite the snapple.";

        String[] chorusparts = chorusbunch.split("\n");
        int min3 = 0;
        int max3 = chorusparts.length - 1;

        for (int j = 0; j < rapchorus.length; j++)
        {
            int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
                rapchorus[j] = chorusparts[randomnum];
        }
        for(int i = 0; i < rapchorus.length; i++)
        {
            rapchorus[i] = rapchorus[i].concat("...");
        }

        String[] bridgeparts = bridgebunch.split("\n");
        int min2 = 0;
        int max2 = bridgeparts.length - 1;

        for (int j = 0; j < rapbridge.length; j++) {
            int randomnum = (int) (Math.random() * (max2 - min2 + 1) + min2);
                rapbridge[j] = bridgeparts[randomnum];
        }
        for(int i = 0; i < rapbridge.length; i++)
        {
            rapbridge[i] = rapbridge[i].concat("...");
        }

        String[] verseparts = versebunch.split("\n");
        int min = 0;
        int max = verseparts.length - 1;

        for (int j = 0; j < rapverse.length; j++)
        {
            int randomnum = (int) (Math.random() * (max - min + 1) + min);
            int randomnum2 = (int) (Math.random() * (max - min + 1) + min);

                rapverse[j] = verseparts[randomnum];
                rapverse2[j] = verseparts[randomnum2];
        }
        for(int i = 0; i < rapverse.length; i++)
        {
            rapverse[i] = rapverse[i].concat("...");
            rapverse2[i] = rapverse2[i].concat("...");
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
}
