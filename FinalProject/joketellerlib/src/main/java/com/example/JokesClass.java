package com.example;
import java.util.Random;
/*
    Thanks to kickasshumor.com for all the jokes
 */
public class JokesClass {
    public static String getJoke(){

    String jokes[]={
            "Boy: *calls 911* Hello? I need your help! \n"+
                    "911: Alright, What is it? \n"+
                    "Boy: Two girls are fighting over me! \n"+
                    "911: So what's your emergency? \n"+
                    "Boy: The ugly one is winning.\n",
            "I was in in the public restroom \n" +
                    "I was barely sitting down when I heard a voice in the other stall: \n" +
                    "\"Hi, how are you?\" \n" +
                    "Me: (embarrassed) \"Doin' fine!\" \n" +
                    "Stall: \"So what are you up to?\" \n" +
                    "Me: \"Uhhh, I'm like you, just sitting here.\" \n" +
                    "Stall: \"Can I come over?\" \n" +
                    "Me: (attitude) \"No, I'm a little busy right now!!\" \n" +
                    "Stall: \"Listen, I'll have to call you back. There's an idiot in the other stall who keeps answering all my questions!",
            "Husband (watching a video):\n" +
                    "Don't do it! I swear you gonna regret it for the rest of your life. You stupid idiot! Don't say yes. No! No! NOOO!! Aw dang, he actually did it! What a dumb ass!\n" +
                    "Wife: Honey, why you so mad? What'aya watching?\n" +
                    "Husband: Our wedding ceremony.",
            "Dad: Say daddy!\n" +
                    "Baby: Mommy!\n" +
                    "Dad: Come on, say daddy!\n" +
                    "Baby: Mommy!\n" +
                    "Dad: F*ck you, say daddy!\n" +
                    "Baby: F*ck you, Mommy!\n" +
                    "Mom: Honey, I'm home!\n" +
                    "Baby: F*ck you!\n" +
                    "Mom: Who taught you that?\n" +
                    "Baby: Daddy!\n" +
                    "Dad: Son of a b*tch",
            "Boy: Dad, I got expelled from school.\n" +
                    "Dad: WHAT?!?! WHY?!\n" +
                    "Boy: A kid said \"sticks and stones may break my bones but words will never hurt me.\" \n" +
                    "Dad: So?\n" +
                    "Boy: So I threw a dictionary at him.\n" +
                    "Dad: That's my boy",
            "Love is like a fart,\n"+
                    "if you force it, it's probably crap.",
            "boy: your teeth are like the stars.\n" +
                    "girl: wow omg they are qute right.\n" +
                    "boy: no, they are far from each other.",
            "A man asks a farmer near a field, \n"+
                    "\"Sorry sir, would you mind if I crossed your field instead of going around it?\n"+
                    " You see, I have to catch the 4:23 train.\"\n" +
                    "The farmer says, \"Sure, go right ahead. And if my bull sees you, you\'ll even catch the 4:11 one.\""
    };
        //Choosing a random joke id
        Random random = new Random();
        int randomJokeId = random.nextInt(jokes.length);
        return jokes[randomJokeId];
    }

}
