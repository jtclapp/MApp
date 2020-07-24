package com.example.musicapp;

import java.util.HashMap;

public class SongBuilder {
    public String rapsong;
    public String[] rapverse;
    public String[] rapverse2;
    public String[] rapchorus;
    public String[] rapbridge;
    public String rocksong;
    public String[] rockverse;
    public String[] rockverse2;
    public String[] rockchorus;
    public String[] rockbridge;
    public String randbsong;
    public String[] randbverse;
    public String[] randbverse2;
    public String[] randbchorus;
    public String[] randbbridge;
    public String countrysong;
    public String[] countryverse;
    public String[] countryverse2;
    public String[] countrychorus;
    public String[] countrybridge;

    public void CreatingRapVerse1() {
        int randomchorus = (int) (Math.random() * 2) + 1;
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

        String chorusbunch2 = "Or I live another day\n" +
                "With my head against the world\n" +
                "And my heart to the grind\n" +
                "I grind hard, grind, grind hard, grind hard, grind hard\n" +
                "Hey yeah, we be on the grind\n" +
                "This too will give you a rush\n" +
                "I want ya brain and this too, put it behind me\n" +
                "Imma a grind, imma a grind, imma a grind\n" +
                "Baby girl, give it to me now\n" +
                "Bumping and grinding with the top turned\n" +
                "Both my stick fingers right up she know I grind daily\n" +
                "In the hood on the grind, nothing aint goin so good";

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
        if(randomchorus == 1);
        {
            String[] chorusparts = chorusbunch.split("\n");
            int min3 = 0;
            int max3 = chorusparts.length - 1;
        for (int j = 0; j < rapchorus.length; j++)
        {
            int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
                rapchorus[j] = chorusparts[randomnum].concat(".");
        }
        for(int i = 0; i < rapchorus.length; i++)
        {
            rapchorus[i] = rapchorus[i].concat("...");
        }
        }
        if(randomchorus == 2)
        {
            String[] chorusparts2 = chorusbunch2.split("\n");
            int minchourus = 0;
            int maxchourus = chorusparts2.length-1;
            for (int j = 0; j < rapchorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (maxchourus - minchourus + 1) + minchourus);
                rapchorus[j] = chorusparts2[randomnum].concat(".");
            }
            for(int i = 0; i < rapchorus.length; i++)
            {
                rapchorus[i] = rapchorus[i].concat("...");
            }
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
        String display = "";
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
    public void CreatingRockVerse1()
    {
        int randomchorus = (int) (Math.random() * 2) + 1;
        rockverse = new String[7];
        rockverse2 = new String[7];
        rockbridge = new String[3];
        rockchorus = new String[5];
        String versebunch = "The world is hard, so many are lost\n" +
                "How hard it is just to believe\n" +
                "I said it made me happy\n" +
                "I know it must have been hard\n" +
                "When the wind blows hard\n" +
                "Sometimes I feel just like a thief\n" +
                "Every day I walk this thin line\n" +
                "Hard to keep my cool at nightTell me, am I crazy?\n" +
                "Yeah, you look like trouble\n" +
                "Crazy people coming up with\n" +
                "Strangers trying to break into my heart\n" +
                "Welcome sign, come inside\n" +
                "Where did they take you?\n" +
                "Got your book, here with me\n" +
                "Sounds like a fun day\n" +
                "Well I had a name, I had a number\n" +
                "I had a price, I thought I had hit the bottom\n" +
                "Must have meant nothing to ya\n" +
                "Cause I fall for this every time\n" +
                "And this is my experience\n" +
                "Of love in a distant, lonely world\n" +
                "I see loyalty, when I see you\n" +
                "I see hope when I see you ";

        String chorusbunch = "I made my way by sitting on the cold hard ground\n" +
                "I used to scream like a baby, now I just scream into the night\n" +
                "Well you might be the hardest\n" +
                "With your heart in your hand the hard wayIn it comes, comes and it hits\n" +
                "And it hits hard\n" +
                "Harder than it ever then\n" +
                "Youll even call it\n" +
                "I think I might go crazy, go crazy, go crazy\n" +
                "To really know where I am\n" +
                "Please put her arms around me, around me\n" +
                "She may never give me genuine loveWhich is always the first one to warn you\n" +
                "Things will get pretty crazy you always know the weather well\n" +
                "Nothing to get crazy\n" +
                "That was long time ago\n" +
                "I cry, I cry, no one else can help me please\n" +
                "Lord, I wanna fall, I wanna fall, in love\n" +
                "Peace and love, would you let me know?";
        String chorusbunch2 = "I just came to have a ball\n" +
                "And party with all my friends\n" +
                "A rich man must have a party\n" +
                "Party that lasts a year";

        String bridgebunch = "Where did all your fun go?\n" +
                "Round and around rolling round\n" +
                "Let your party house go up in flames\n" +
                "If we just start today\n" +
                "A place in the past for love\n" +
                "Fly so high, stay so free\n" +
                "Keep that in mind, before we lose this\n" +
                "And I may never get the chance again to";

        if(randomchorus == 1);
        {
            String[] chorusparts = chorusbunch.split("\n");
            int min3 = 0;
            int max3 = chorusparts.length - 1;
            for (int j = 0; j < rockchorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
                rockchorus[j] = chorusparts[randomnum].concat(".");
            }
            for(int i = 0; i < rockchorus.length; i++)
            {
                rockchorus[i] = rockchorus[i].concat("...");
            }
        }
        if(randomchorus == 2)
        {
//            String[] chorusparts2 = chorusbunch2.split("\n");
//            int minchourus = 0;
//            int maxchourus = chorusparts2.length-1;
//            for (int j = 0; j < rockchorus.length; j++)
//            {
//                int randomnum = (int) (Math.random() * (maxchourus - minchourus + 1) + minchourus);
//                rockchorus[j] = chorusparts2[randomnum].concat(".");
//            }
//            for(int i = 0; i < rockchorus.length; i++)
//            {
//                rockchorus[i] = rockchorus[i].concat("...");
//            }
        }
        String[] bridgeparts = bridgebunch.split("\n");
        int min2 = 0;
        int max2 = bridgeparts.length - 1;

        for (int j = 0; j < rockbridge.length; j++) {
            int randomnum = (int) (Math.random() * (max2 - min2 + 1) + min2);
            rockbridge[j] = bridgeparts[randomnum];
        }
        for(int i = 0; i < rockbridge.length; i++)
        {
            rockbridge[i] = rockbridge[i].concat("...");
        }

        String[] verseparts = versebunch.split("\n");
        int min = 0;
        int max = verseparts.length - 1;

        for (int j = 0; j < rockverse.length; j++)
        {
            int randomnum = (int) (Math.random() * (max - min + 1) + min);
            int randomnum2 = (int) (Math.random() * (max - min + 1) + min);

            rockverse[j] = verseparts[randomnum];
            rockverse2[j] = verseparts[randomnum2];
        }
        for(int i = 0; i < rockverse.length; i++)
        {
            rockverse[i] = rockverse[i].concat("...");
            rockverse2[i] = rockverse2[i].concat("...");
        }
    }
    public String ReturningRockDisplay()
    {
        String display = "";
        for (int i = 0; i < rockverse.length; i++) {
            display += rockverse[i].substring(0, rockverse[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rockchorus.length; i++) {
            display += rockchorus[i].substring(0, rockchorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rockverse2.length; i++) {
            display += rockverse2[i].substring(0, rockverse2[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rockchorus.length; i++) {
            display += rockchorus[i].substring(0, rockchorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rockbridge.length; i++) {
            display += rockbridge[i].substring(0, rockbridge[i].length() - 3);
            display += ". ";
        }
        return display;
    }
    public String ReturningRock()
    {
        for (int i = 0; i < rockverse.length; i++) {
            rocksong += rockverse[i];
        }
        for (int e = 0; e < rockchorus.length; e++) {
            rocksong += rockchorus[e];
        }
        for (int j = 0; j < rockverse2.length; j++) {
            rocksong += rockverse2[j];
        }
        for (int t = 0; t < rockchorus.length; t++) {
            rocksong += rockchorus[t];
        }
        for (int k = 0; k < rockbridge.length; k++) {
            rocksong += rockbridge[k];
        }
        return rocksong;
    }
    public void CreatingRandBVerse1()
    {

    }
    public String ReturningRandBDisplay()
    {
        String display = "";



        return display;
    }
    public String ReturningRandB()
    {



        return randbsong;
    }
    public void CreatingCountryVerse1()
    {

    }
    public String ReturningCountryDisplay()
    {
        String display = "";




        return display;
    }
    public String ReturningCountry()
    {




        return countrysong;
    }
}
