/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// The method generating the blurbs has to be recursive
package ortiz_hw8;

/**
 *
 * @author joshortiz
 */
import java.util.Random;

public class LanguageChecker {

    private Random randGenerator = new Random();
    private int randInt = randGenerator.nextInt(10);

    public String yCount(int amount) {
        if (amount == 0) {
            return "";
        } else {
            return "y" + yCount(amount - 1);
        }
    }

    public String Whoozit() {
        //Here we know a whoozit has at LEAST 1 y and no more than 10
        //so we'll limit the randGenerator to 0-10exclusive and include the 1st y
        String whoozit = "xy" + yCount(randGenerator.nextInt(10));
        return whoozit;
    }

    public String Whatzit() {
        //again, we only want a random value from
        // 2 given values, so the randgenerator will return
        //either a 1 or 0 for 'z' or 'd'
        int rand2 = randGenerator.nextInt(2);
        String zORd = null;

        if (rand2 == 0) {
            zORd = "z";
        } else if (rand2 == 1) {
            zORd = "d";
        }
        String whatzit = "q" + zORd + Whoozit();
        return whatzit;

    }

    //Will return a random blurb
    public String Blurb() {
        String blurb = Whoozit();
        int whatzitAmount = randGenerator.nextInt(11);
        while (whatzitAmount > 0) {
            blurb += Whatzit();
            whatzitAmount--;
        }
        return blurb;
    }

    public static void main(String[] args) {
        LanguageChecker x = new LanguageChecker();
        int y = 0;
        while (y < 10) {
            System.out.println(x.Blurb() + "\n");
            y++;
        }
    }

}
