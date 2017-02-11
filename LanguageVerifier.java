/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ortiz_hw8;

/**
 *
 * @author joshortiz
 */
import java.util.*;
import java.io.*;

public class LanguageVerifier {

    int invalid = -1;

    //will return an integer which we can use to determine the validity
    // Ex: 1 is invalid because then the string only starts with x
    // 2-11 is valid because we verify the string starts with x and increment
    // as the string continues to have y. -1 is an invalid sentence
    public int hasWhoozit(String s) {
        int i = 0;
        if (s.charAt(0) == 'x') {
            i++;
            while (i < s.length() && s.charAt(i) == 'y') {
                i++;
            }
            if (i == s.length()) {
                //uncomment line for tracing
                //System.out.println("s is a valid whoozit");
                return i;
            } else {
                //uncomment line for tracing
                //System.out.println("s haswhoozit");
                return i;
            }

        } else {
            return invalid;
        }
    }

    public int hasWhatzit(String s) {

        //let's us know which char to start at, since it counts valid characters
        // and returns that count, we then use that count to reference the
        // index of the next char, since the index count starts at 0.
        int startsWithWhoozit = hasWhoozit(s);

        //if the strings starts with a whoozit then it's a good candidate for a blurb
        if (((startsWithWhoozit) >= 2) && ((startsWithWhoozit) <= 11)) {

            String cutOutWhoozit = s.substring(startsWithWhoozit);

            // if the string entered has the beginnings of a valid blurb (has whoozits)
            // then we'll check that the next sequence after the whoositz is a whatzit
            // Returns 0 if sequence ends with qz
            if (cutOutWhoozit.startsWith("qd") || cutOutWhoozit.startsWith("qz")) {
                String postValidSequence = cutOutWhoozit.substring(2);

                //System.out.println("sequence starts with whoozit andd may have whatzit");
                if (postValidSequence.length() != 0) {
                    if (hasWhoozit(postValidSequence) >= 2
                            && hasWhoozit(postValidSequence) <= 11
                            && hasWhoozit(postValidSequence) == postValidSequence.length()) {
                        //System.out.println("has whatzit and is blurb");

                        return postValidSequence.length();
                    } else {
                        int potentiallyValid = hasWhatzit(postValidSequence);
                        if (potentiallyValid == -1) {
                            return invalid;
                        } else {
                            return potentiallyValid;
                        }
                    }
                } else {
                    return invalid;
                }

            } else {
                return invalid;
            }

        } else if (s.startsWith("qd") && s.startsWith("qz")) {
            System.out.println("string s starts with qz/d\nbut is not a blurb");
            return 2;
        } else { // else return the invalid value
            return invalid;
        }
    }

    public boolean isBlurb(String s) {
        int whoozitCount = hasWhoozit(s);
        if (whoozitCount == s.length() && whoozitCount < 12) {
            return true;
        } else if (whoozitCount < s.length() && whoozitCount >= 2) {

            int checkRest = hasWhatzit(s);
            if (checkRest == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        LanguageVerifier test = new LanguageVerifier();
        boolean DONE = false;

        while (!DONE) {
            System.out.println("Enter a String to verify: ");
            Scanner input = new Scanner(System.in);
            String enteredInput = input.next();
            if (enteredInput.equals("DONE")) {
                DONE = true;
            } else {
                if (test.isBlurb(enteredInput)) {
                    System.out.println("The word is fine");
                } else {
                    System.out.println("This word is a mess!");
                }
            }
        }
    }
}
