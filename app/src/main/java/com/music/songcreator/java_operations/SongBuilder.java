package com.music.songcreator.java_operations;

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
        rapsong = null;
        rapverse = new String[7];
        rapverse2 = new String[7];
        rapbridge = new String[3];
        rapchorus = new String[4];
        String bridgebunch = "The sun is shinin' today\n" +
                "The sun is shinin' today\n" +
                "Here it is man, I told y'all\n" +
                "Feels like summer in the winter\n" +
                "No matter what it always feels like summer in the neighborhood\n" +
                "And being that it feels like summer, let's do this man\n" +
                "Whatever day, vibe, month it is, it just feels like summer\n" +
                "You know what I'm saying\n" +
                "Now you a dead man for real\n" +
                "Nobody mention my familia\n" +
                "I'ma show 'em how to act\n" +
                "I'ma show you, show you, show you, show you\n" +
                "I'ma show 'em how to act\n" +
                "Hard labor let me pay the price\n" +
                "I think I need to let it loose\n" +
                "Ooh love ya baby oh oh\n" +
                "Love ya baby oh yeah\n" +
                "Pimp hard, party hard, body right, thing\n" +
                "Yeah, girl, know I got a girl\n" +
                "What you do, and I love you\n" +
                "What you do, and I want you\n" +
                "Know I got a check, be inside the party\n" +
                "They ask me how fly is your boy\n" +
                "Enough to make a man about a party\n" +
                "You could find me in the front row, dancing";

        String chorusbunch = "But not the kinda love that will be in vain\n" +
                "I wanna love, love you forever, girl of my dreams, I wanna hold her\n" +
                "Share my life and love you, do everything\n" +
                "You got to make sure you can love somebody\n" +
                "And no woman that I know loves me this wayLove is love God is God\n" +
                "It always comes as no surprise\n" +
                "Love never lets you go Sometimes you make me think. I love you\n" +
                "Sometimes you make me sing\n" +
                "Love makes the world go around, make a world go around\n" +
                "But I love dem\n" +
                "Where is the love? Where is love? And no woman that I know loves me this way\n" +
                "But I know some people that they really love me\n" +
                "I love everybody with their arm around them\n" +
                "Would you, love me if I lost everything\n" +
                "Would you, love me no matter what\n" +
                "Would you, love me if I lost my job\n" +
                "And everything I love was taken away\n" +
                "Love was your home\n" +
                "Love was your town\n" +
                "She never tells me she got a man at home\n" +
                "And she never tells me that our love will never end\n" +
                "They love me for my way, new style no jive\n" +
                "All the problems that seemed all mine, just stay the same\n" +
                "Wake up love, wake up love\n" +
                "Wake up love, wake up love\n" +
                "A higher love, the highest love\n" +
                "The highest love\n" +
                "They make me, laugh as I sleep, freak when I eat\n" +
                "And love a one man life, but I roll with a thousand";

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
                "In the hood on the grind, nothing aint goin so good\n" +
                "Yeah, pump the breaks and make the party rock\n" +
                "We came to party, poppin bottles\n" +
                "We came to party, dancing\n" +
                "We came to party, poppin bottles\n" +
                "Girls giving brain and lovers making plans away\n" +
                "Could this be the one and only party day\n" +
                "Young after party, young after party\n" +
                "Young after party, young after party\n" +
                "Time to make the club go\n" +
                "Party people, party people, party people, party people\n" +
                "Move your bodies to the left\n" +
                "Break away from the older brother\n" +
                "She can never throw a party\n" +
                "She can never throw a, a party for me\n" +
                "We gonna party hard, party gang, party gang\n" +
                "You still beating on your chest";

        String versebunch = "She looked so clean and good\n" +
                "She could take anybody\n" +
                "Put on a party for free\n" +
                "Take me with you, lets go\n" +
                "I went to your house, you led a whole party out\n" +
                "You my worldwide hustler, yeah, baby girl, no shame\n" +
                "I want a girl to invite me to her party\n" +
                "Whoa, show her the grill\n" +
                "Like we confuse love\n" +
                "It sounds like sirens\n" +
                "But love won the fight\n" +
                "Into the room, big shot\n" +
                "Do you need just a little love\n" +
                "Or should I take some of your time\n" +
                "Up to the sky, up to the cloud and sea, baby\n" +
                "One love, our love, one love\n" +
                "Cause it feeds the power in your heart\n" +
                "You better fight to keep your love alive\n" +
                "You better fight to keep your love alive\n" +
                "Where my love is in love\n" +
                "You never quit the fight\n" +
                "Cause it was never really worth\n" +
                "Tryin, tryin, tryin for love\n" +
                "She never tells me she got a man at home\n" +
                "And she never tells me that our love will never end\n" +
                "But she never makes it known which way the love goes\n" +
                "And sometimes when I wanna leave she says they gotta let me go\n" +
                "You look like an angel with them green eyes\n" +
                "And you dance around like the k arms\n" +
                "Just dance roll til The break of dawn\n" +
                "And I need to know girl\n" +
                "Baby would you dance with me\n" +
                "One more chance. Just one more\n" +
                "Ahhh. I wanna be. I just wanna be\n" +
                "Ahhh. I just wanna be happy\n" +
                "You should be happy you always succeed\n" +
                "Sometimes I feel lucky, happy inside\n" +
                "Sometimes I just wish I had a heart\n" +
                "Sometimes I just need to try\n" +
                "My own people came before you\n" +
                "Rest of the world changing before me\n" +
                "You and I you know that we starting now\n" +
                "You and I you and I aint no rest for the weary";

        if(randomchorus == 1);
        {
            String[] chorusparts = chorusbunch.split("\n");
            int min3 = 0;
            int max3 = chorusparts.length - 1;
        for (int j = 0; j < rapchorus.length; j++)
        {
            int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
                rapchorus[j] = chorusparts[randomnum];
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
                rapchorus[j] = chorusparts2[randomnum];
            }
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
        String display = "";
        for (int i = 0; i < rapverse.length; i++) {
            display += rapverse[i].substring(0, rapverse[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rapchorus.length; i++) {
            display += rapchorus[i].substring(0, rapchorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rapverse2.length; i++) {
            display += rapverse2[i].substring(0, rapverse2[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rapchorus.length; i++) {
            display += rapchorus[i].substring(0, rapchorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < rapbridge.length; i++) {
            display += rapbridge[i].substring(0, rapbridge[i].length() - 3);
            display += ". ";
        }
        return display;
    }
    public void CreatingRockVerse1()
    {
        int randomchorus = (int) (Math.random() * 2) + 1;
        rocksong = null;
        rockverse = new String[7];
        rockverse2 = new String[7];
        rockbridge = new String[3];
        rockchorus = new String[4];
        String versebunch = "The world is hard, so many are lost\n" +
                "How hard it is just to believe\n" +
                "I said it made me happy\n" +
                "I know it must have been hard\n" +
                "When the wind blows hard\n" +
                "Sometimes I feel just like a thief\n" +
                "Every day I walk this thin line\n" +
                "Hard to keep my cool at night. Tell me, am I crazy\n" +
                "Yeah, you look like trouble\n" +
                "Crazy people coming up with\n" +
                "Strangers trying to break into my heart\n" +
                "Welcome sign, come inside\n" +
                "Where did they take you\n" +
                "Got your book, here with me\n" +
                "Sounds like a fun day\n" +
                "Well I had a name, I had a number\n" +
                "I had a price, I thought I had hit the bottom\n" +
                "Must have meant nothing to ya\n" +
                "Cause I fall for this every time\n" +
                "And this is my experience\n" +
                "Of love in a distant, lonely world\n" +
                "I see loyalty, when I see you\n" +
                "I see hope when I see you\n" +
                "Everybody knows our love is strong\n" +
                "Nobody needs the truth\n" +
                "With lust in his heart, she took your heart and ate it whole\n" +
                "For love is like money and you know money loves pain\n" +
                "Give one day, given one day\n" +
                "To find your love, you give it all\n" +
                "I love you for the things that nobody else could know\n" +
                "For what I do when I am far from everybody else\n" +
                "And most of my friends are from the schools of hollywood\n" +
                "But they run the show and run when the party is over\n" +
                "I see you at every party\n" +
                "Stick around to see how it ends\n" +
                "Make the party go strange\n" +
                "Walking down the paths of a dark soul\n" +
                "Painting words with a venom so true";

        String chorusbunch = "I made my way by sitting on the cold hard ground\n" +
                "I used to scream like a baby, now I just scream into the night\n" +
                "Well you might be the hardest\n" +
                "With your heart in your hand the hard way. In it comes, comes and it hits\n" +
                "And it hits hard\n" +
                "Harder than it ever then\n" +
                "You'll even call it\n" +
                "I think I might go crazy, go crazy, go crazy\n" +
                "To really know where I am\n" +
                "Please put her arms around me, around me\n" +
                "She may never give me genuine love. Which is always the first one to warn you\n" +
                "Things will get pretty crazy you always know the weather well\n" +
                "Nothing to get crazy\n" +
                "That was long time ago\n" +
                "I cry, I cry, no one else can help me please\n" +
                "Lord, I wanna fall, I wanna fall, in love\n" +
                "Peace and love, would you let me know\n" +
                "In this habit, that I love so much\n" +
                "I wish you could build me up, maybe you could break me down\n" +
                "Oh yeah, boy you know that I love you\n" +
                "Oh yeah, boy you know that I love you\n" +
                "For love is not a palace\n" +
                "For love is grey\n" +
                "Oh this one said she likes me boys and I like them too\n" +
                "And every time we make sweet love\n" +
                "You got the eyes to ask me not to love you\n" +
                "But you want to hold my hand and eye away\n" +
                "Go to memory, go to memory, go to memory\n" +
                "Is there still a love in your heart\n" +
                "Give us lot love oh give us fun\n" +
                "Give up love and you could save a life\n" +
                "So long mom, so long, so long, so long\n" +
                "My first love I knew";

        String chorusbunch2 = "I just came to have a ball\n" +
                "And party with all my friends\n" +
                "A rich man must have a party\n" +
                "Party that lasts a year\n" +
                "We got a party\n" +
                "We got a party\n" +
                "Do we need a party anyway?\n" +
                "The kind I fear will come\n" +
                "We walk into a party\n" +
                "You find me late at night\n" +
                "Yes, the party that you want\n" +
                "Is the party of a lifetime\n" +
                "I look at this little party\n" +
                "And search this heart and see what lies there\n" +
                "There was a party, at the bottom of the river\n" +
                "There was a party when it all went down\n" +
                "I went to a party, just the usual\n" +
                "To have it loud, to have it proud\n" +
                "Of this farewell party\n" +
                "Not to let you in\n" +
                "Leave the party at one\n" +
                "Crawl into my room and then move right over here\n" +
                "In a fancy mansion party at the back door\n" +
                "Chic and lazy made a getaway\n" +
                "Had a nice party on the bay\n" +
                "I was happy wasted the day\n" +
                "I came to party, not to party, not to party\n" +
                "I have to go to the party\n" +
                "I have to go to the party\n" +
                "While there is no more song to be sung\n" +
                "Another party goes up in flames";

        String bridgebunch = "Where did all your fun go\n" +
                "Round and around rolling round\n" +
                "Let your party house go up in flames\n" +
                "If we just start today\n" +
                "A place in the past for love\n" +
                "Fly so high, stay so free\n" +
                "Keep that in mind, before we lose this\n" +
                "And I may never get the chance again to\n" +
                "Yes, the party that you want\n" +
                "Is the party of a lifetime\n" +
                "They went to a rave together\n" +
                "They went to a party together\n" +
                "So the party goes\n" +
                "Smile on every face, still trying to play it safe\n" +
                "We really wanna know you\n" +
                "We love to know you\n" +
                "Do you know when love begins\n" +
                "Or does it all just happen\n" +
                "In this habit, that I love so much\n" +
                "I wish you could build me up, maybe you could break me down\n" +
                "Hear our song on stereo\n" +
                "Send our love into radio\n" +
                "Every thing I fear I fear you\n" +
                "Your love so naughty";

        if(randomchorus == 1);
        {
            String[] chorusparts = chorusbunch.split("\n");
            int min3 = 0;
            int max3 = chorusparts.length - 1;
            for (int j = 0; j < rockchorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
                rockchorus[j] = chorusparts[randomnum];
            }
        }
        if(randomchorus == 2)
        {
            String[] chorusparts2 = chorusbunch2.split("\n");
            int minchourus = 0;
            int maxchourus = chorusparts2.length-1;
            for (int j = 0; j < rockchorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (maxchourus - minchourus + 1) + minchourus);
                rockchorus[j] = chorusparts2[randomnum];
            }
        }
        for(int i = 0; i < rockchorus.length; i++)
        {
            rockchorus[i] = rockchorus[i].concat("...");
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
    public void CreatingRandBVerse1()
    {
        int randomchorus = (int) (Math.random() * 2) + 1;
        randbsong = null;
        randbverse = new String[7];
        randbverse2 = new String[7];
        randbbridge = new String[3];
        randbchorus = new String[4];

        String versebunch = "He said, you do as your heart spins\n" +
                "You gotta love as you live, ya gotta love as you go\n" +
                "Still counting down the days\n" +
                "That you fall in love\n" +
                "This love has left me stranded on the edge of thrills\n" +
                "And this love has left me stuck here in the end\n" +
                "I want a perfect love\n" +
                "Because all I ever needed\n" +
                "Love and fear have filled up my head\n" +
                "Never let me see you walk away\n" +
                "Because your love says I am yours\n" +
                "Without your love I am lost\n" +
                "You gotta go and find it\n" +
                "Toast up the girls in the party\n" +
                "You gotta go and find it\n" +
                "Toast up the girls in the party\n" +
                "You know today I just called to say\n" +
                "Im going away to a certain party\n" +
                "What if I am is all that I want\n" +
                "What if I is all that you are\n" +
                "You want to have some fun\n" +
                "Burning your candle slow\n" +
                "So pour me another shot\n" +
                "If you want some fun, some fun, some fun\n" +
                "Cause when it comes to the women\n" +
                "You know better that you do\n" +
                "All I can do is be the better man\n" +
                "And I do it with a feeling that is growing more every day\n" +
                "For my birthday you stole my new car\n" +
                "And for Christmas I bought you a ring\n" +
                "The love we once had is now gone\n" +
                "The love we once shared is now gone\n" +
                "What makes you happy, baby? I gotta know\n" +
                "What makes you happy, baby\n" +
                "All I long for right now is a happy ending\n" +
                "This feeling I got is a feeling that im feeling\n" +
                "Im just so happy inside\n" +
                "That you got right over me\n" +
                "And it kept me standing there";

        String chorusbunch = "I need a love to share\n" +
                "I need a love of life\n" +
                "I need a love to share\n" +
                "I need a love of life\n" +
                "Move our love from town to town\n" +
                "From hotel to motel\n" +
                "I need you like lost puppy love\n" +
                "Lost puppy love\n" +
                "Have fun, my baby have fun\n" +
                "If you ever had somebody in your life that made you feel\n" +
                "That love was more than just little fun play\n" +
                "Feel it better, feel it better than the rest\n" +
                "Feel it better, ahh you better\n" +
                "Feel it better, feel it better\n" +
                "Baby if you want to leave me just leave me alone\n" +
                "Well you better go now, go somewhere, whatcha gonna do\n" +
                "You better come now, come on, come up here, whatcha gonna do\n" +
                "Why are you throwing, throwing, throwing, throwing away our love\n" +
                "You should be taking, making, giving, taking better\n" +
                "I think we should do this right\n" +
                "Girl you better do this right\n" +
                "If you leave baby you better do this right\n" +
                "If you left baby you better do this right\n" +
                "Love will keep you chained and look for release\n" +
                "Love will be your banner\n" +
                "Well, the very heart that lets me love you\n" +
                "In spite of everything that I know was always by your side\n" +
                "I need your love to shine through me\n" +
                "I need your love to shine through me\n" +
                "How many times did I, again, tell you, you know\n" +
                "I always love to, love to, gotta love to\n" +
                "Love stand still, love stand strong\n" +
                "Love stand though the nations rise\n" +
                "Love stand still, love stand strong\n" +
                "Still all over the world just trying to find your love before you go";

        String chorusbunch2 = "Rise to the top, get on the couch, crash the party\n" +
                "Rise to the top, get on the couch, crash the party\n" +
                "I got this party plan for a crazy tonight\n" +
                "I got this party planned for you\n" +
                "Sneak in the dark\n" +
                "Dance in the dark\n" +
                "Dance in the dark\n" +
                "Show them you know how\n" +
                "The way you danced, the way you danced\n" +
                "I try to dance to your rhythm\n" +
                "Just the way to make me dance\n" +
                "Please show me how to move my feet\n" +
                "Mountains fall in place, I better run\n" +
                "Mountains fall and break, I belong\n" +
                "Mountains fall and break, I better run\n" +
                "Sun comes and sets, I better run\n" +
                "Makes me feel so much better\n" +
                "So much better, so much better\n" +
                "No better love, just let it be\n" +
                "Comes into my room, all of the time\n" +
                "What a night to dance\n" +
                "What a night to dance\n" +
                "Oh, I wanna dance, I wanna dance\n" +
                "Oh, dance the night away\n" +
                "Dance till the morning girl\n" +
                "Its a party in this crazy town\n" +
                "Gotta party in this crazy town";
;

        String bridgebunch = "To love you, no trying\n" +
                "To love you, no trying\n" +
                "He said, you do as your heart spins\n" +
                "You gotta love as you live, ya gotta love as you go\n" +
                "In this life I give my soul to see you live\n" +
                "All that I am, let your love fill up my heart\n" +
                "When we love comes calling\n" +
                "There are no hours, no time\n" +
                "All I know is your body belongs to me\n" +
                "Dance with you forever\n" +
                "But I know your heart is hurting\n" +
                "You deserve to know that you did better\n" +
                "I know that you want a better life\n" +
                "But maybe you should be at your best\n" +
                "Open up your eyes and see my face\n" +
                "Baby you better believe me\n" +
                "I am loving you\n" +
                "I am forgetting your love\n" +
                "Its a love crisis and I want you to know\n" +
                "My love is still alive";
        if(randomchorus == 1);
        {
            String[] chorusparts = chorusbunch.split("\n");
            int min3 = 0;
            int max3 = chorusparts.length - 1;
            for (int j = 0; j < randbchorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
                randbchorus[j] = chorusparts[randomnum];
            }
        }
        if(randomchorus == 2)
        {
            String[] chorusparts2 = chorusbunch2.split("\n");
            int minchourus = 0;
            int maxchourus = chorusparts2.length-1;
            for (int j = 0; j < randbchorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (maxchourus - minchourus + 1) + minchourus);
                randbchorus[j] = chorusparts2[randomnum];
            }
        }
        for(int i = 0; i < randbchorus.length; i++)
        {
            randbchorus[i] = randbchorus[i].concat("...");
        }

        String[] bridgeparts = bridgebunch.split("\n");
        int min2 = 0;
        int max2 = bridgeparts.length - 1;

        for (int j = 0; j < randbbridge.length; j++) {
            int randomnum = (int) (Math.random() * (max2 - min2 + 1) + min2);
            randbbridge[j] = bridgeparts[randomnum];
        }
        for(int i = 0; i < randbbridge.length; i++)
        {
            randbbridge[i] = randbbridge[i].concat("...");
        }

        String[] verseparts = versebunch.split("\n");
        int min = 0;
        int max = verseparts.length - 1;

        for (int j = 0; j < randbverse.length; j++)
        {
            int randomnum = (int) (Math.random() * (max - min + 1) + min);
            int randomnum2 = (int) (Math.random() * (max - min + 1) + min);

            randbverse[j] = verseparts[randomnum];
            randbverse2[j] = verseparts[randomnum2];
        }
        for(int i = 0; i < randbverse.length; i++)
        {
            randbverse[i] = randbverse[i].concat("...");
            randbverse2[i] = randbverse2[i].concat("...");
        }
    }
    public String ReturningRandBDisplay()
    {
        String display = "";
        for (int i = 0; i < randbverse.length; i++) {
            display += randbverse[i].substring(0, randbverse[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < randbchorus.length; i++) {
            display += randbchorus[i].substring(0, randbchorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < randbverse2.length; i++) {
            display += randbverse2[i].substring(0, randbverse2[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < randbchorus.length; i++) {
            display += randbchorus[i].substring(0, randbchorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < randbbridge.length; i++) {
            display += randbbridge[i].substring(0, randbbridge[i].length() - 3);
            display += ". ";
        }
        return display;
    }
    public void CreatingCountryVerse1()
    {
        int randomchorus = (int) (Math.random() * 2) + 1;
        countrysong = null;
        countryverse = new String[7];
        countryverse2 = new String[7];
        countrybridge = new String[3];
        countrychorus = new String[4];

        String versebunch = "Gonna too much lookin love\n" +
                "I can see it burnin in your eyes\n" +
                "My hands find an old worn out sofa\n" +
                "No one in the world could do what I do\n" +
                "Just me and the pillow as I say\n" +
                "Say goodnight and slowly bed down\n" +
                "Getting me up in the morning\n" +
                "Making love to me up in the morning\n" +
                "He only wants to be sure\n" +
                "Of the warmth of his love\n" +
                "The warmth of his love\n" +
                "She just bored to love\n" +
                "All in the name of love an ton\n" +
                "In the name of God an angel took my hand\n" +
                "And I get to tell you after all these years\n" +
                "Put a ring on his finger\n" +
                "Oh me, oh my, done fell in love with a country girl\n" +
                "Me, oh my, done fell in love with a country girl\n" +
                "And it was a love song, a love song I was happy on\n" +
                "Till the day you came, tattered at the seams\n" +
                "I love the way the waves are gonna carry you home\n" +
                "I love the way the waves are gonna carry you home\n" +
                "Cause I finally found love\n" +
                "I finally found trust\n" +
                "I finally found love\n" +
                "That knocks me a mile down\n" +
                "Well this girl knows how to party\n" +
                "Well this boy knows how to party\n" +
                "A day at the pool old boy sinking sun\n" +
                "Pool party days in a big old white house\n" +
                "I want it for the free drinks in the air\n" +
                "And them girls are on the floor\n" +
                "Surround the whole party now\n" +
                "Whose farm party last fall\n" +
                "Some boys like the bourbon\n" +
                "Some boys like the girls\n" +
                "Some guys like the bad girls\n" +
                "But I like a good girl\n" +
                "In the wrong time, in the wrong place\n" +
                "At the wrong party, in the wrong house\n" +
                "And I lost my shirt at a party\n" +
                "And the light coming through the window\n" +
                "We took a drive down a dark road\n" +
                "Brakes were blazing round their heads\n" +
                "Daddy never liked momma and daddy never cared\n" +
                "They threw us a party a couple of weeks ago";

        String chorusbunch = "I hope you fall in love\n" +
                "Like I hope you fell in love\n" +
                "Like I hope you fell in love\n" +
                "Take my love, take my love for now\n" +
                "My first love was an ocean blue sky\n" +
                "Because my life is swaying\n" +
                "Your true love is crowned in blood\n" +
                "Every true love has ends, true loves has no ends\n" +
                "My love is a little machine\n" +
                "My love is a cloud of smoke\n" +
                "It might be just a couple of years\n" +
                "To harvest and restore our love\n" +
                "I wish I could stop to love you\n" +
                "I wish I could stop to love you\n" +
                "No one in the world, will love me like you do baby\n" +
                "And no one in the world, will love me like you do\n" +
                "Nothing but a good old fashioned love\n" +
                "Nothing but a good old fashioned love, love\n" +
                "I love you like a stream that loves to flow\n" +
                "I love you like a melody that sings\n" +
                "I love you like a heart that loves to beat\n" +
                "You just love this funky song, just sweet and silly\n" +
                "College girl was the one\n" +
                "There was a party at her house\n" +
                "She met a high school boy\n" +
                "And we went to the party";

        String chorusbunch2 = "Oh a party with a thousand\n" +
                "Oh a party with a thousand\n" +
                "Some people settle for money and fame\n" +
                "Some people like to party and party all night\n" +
                "Some people settle for money and fame\n" +
                "Some people like to party and party all night\n" +
                "Who love to party\n" +
                "I was on that prime time party\n" +
                "What do you do? We had a party\n" +
                "We can party, we can party for your love\n" +
                "We can party, we can party for your love\n" +
                "That night I went to a party\n" +
                "With this girl I had known\n" +
                "And the party, party went on forever\n" +
                "And the party, party went on forever\n" +
                "Hanging in the party too long\n" +
                "Staring in the party too long\n" +
                "Staring in the party too long\n" +
                "What I expect from a party\n" +
                "She just needs a party outfit\n" +
                "Something to make you want to say\n" +
                "It was all party end up in the hospital\n" +
                "It was all party end up in the hospital\n" +
                "Ride, party on\n" +
                "Ride, party all night long\n" +
                "We used to walk the graveyard graveyard road\n" +
                "September, there was a party day";

        String bridgebunch = "Now love was moving down South\n" +
                "He said if I was sleeping well\n" +
                "Sure likes to be her and she gets all her thrills\n" +
                "I love to hear her say that she loves me\n" +
                "Like the arizona rain that tastes like wine\n" +
                "You love me through sunshine or rain\n" +
                "The moment we gave up\n" +
                "The moment we gave up love\n" +
                "Ohhhhh you gotta give me back my heart\n" +
                "Ohhhh you gotta give me back my love\n" +
                "When a man tells me he loves me\n" +
                "That when he makes love me\n" +
                "Another true love\n" +
                "The only true love\n" +
                "I found love on the road\n" +
                "Heard love on the road\n" +
                "Good party, Texas thing\n" +
                "Cheap total shakes\n" +
                "I was just wondering, lord\n" +
                "So would you help me out of the party\n" +
                "That night I went to a party\n" +
                "With this girl I had known";

        if(randomchorus == 1);
        {
            String[] chorusparts = chorusbunch.split("\n");
            int min3 = 0;
            int max3 = chorusparts.length - 1;
            for (int j = 0; j < countrychorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (max3 - min3 + 1) + min3);
                countrychorus[j] = chorusparts[randomnum];
            }
        }
        if(randomchorus == 2)
        {
            String[] chorusparts2 = chorusbunch2.split("\n");
            int minchourus = 0;
            int maxchourus = chorusparts2.length-1;
            for (int j = 0; j < countrychorus.length; j++)
            {
                int randomnum = (int) (Math.random() * (maxchourus - minchourus + 1) + minchourus);
                countrychorus[j] = chorusparts2[randomnum];
            }
        }
        for(int i = 0; i < countrychorus.length; i++)
        {
            countrychorus[i] = countrychorus[i].concat("...");
        }

        String[] bridgeparts = bridgebunch.split("\n");
        int min2 = 0;
        int max2 = bridgeparts.length - 1;

        for (int j = 0; j < countrybridge.length; j++) {
            int randomnum = (int) (Math.random() * (max2 - min2 + 1) + min2);
            countrybridge[j] = bridgeparts[randomnum];
        }
        for(int i = 0; i < countrybridge.length; i++)
        {
            countrybridge[i] = countrybridge[i].concat("...");
        }

        String[] verseparts = versebunch.split("\n");
        int min = 0;
        int max = verseparts.length - 1;

        for (int j = 0; j < countryverse.length; j++)
        {
            int randomnum = (int) (Math.random() * (max - min + 1) + min);
            int randomnum2 = (int) (Math.random() * (max - min + 1) + min);

            countryverse[j] = verseparts[randomnum];
            countryverse2[j] = verseparts[randomnum2];
        }
        for(int i = 0; i < countryverse.length; i++)
        {
            countryverse[i] = countryverse[i].concat("...");
            countryverse2[i] = countryverse2[i].concat("...");
        }
    }
    public String ReturningCountryDisplay()
    {
        String display = "";
        for (int i = 0; i < countryverse.length; i++) {
            display += countryverse[i].substring(0, countryverse[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < countrychorus.length; i++) {
            display += countrychorus[i].substring(0, countrychorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < countryverse2.length; i++) {
            display += countryverse2[i].substring(0, countryverse2[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < countrychorus.length; i++) {
            display += countrychorus[i].substring(0, countrychorus[i].length() - 3);
            display += ". ";
        }
        display += "\n\n";

        for (int i = 0; i < countrybridge.length; i++) {
            display += countrybridge[i].substring(0, countrybridge[i].length() - 3);
            display += ". ";
        }
        return display;
    }
}
