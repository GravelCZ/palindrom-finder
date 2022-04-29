package cz.vesely.palindromy;

import java.net.StandardSocketOptions;
import java.text.Normalizer;
import java.util.Locale;

public class PalindromFinder {

    private boolean ignoreDiacritics;
    private boolean ignoreSize;

    private static String IGNORED_CHARACTERS_REGEX = "[,\\.\\\\\\|\\/\\-_\\?!]+";

    public PalindromFinder() {
        this(false, false);
    }

    public PalindromFinder(boolean ignoreDiacritics, boolean ignoreSize) {
        this.ignoreDiacritics = ignoreDiacritics;
        this.ignoreSize = ignoreSize;
    }

    public String findAll(String text) {
        String[] words = text.split(" +");
        StringBuilder output = new StringBuilder();

        int j = 0;;

        for (int i = 0; i < words.length; i++) {
            String word = this.ignoreSize ? words[i].toLowerCase() : words[i];
            word = word.replaceAll(IGNORED_CHARACTERS_REGEX, ""); // zbaví se všech znaků které se nepočítají do slova
            if (word.length() <= 1) { // znaky se čtou stejně oboustraně xd
                continue;
            }
            if (ignoreDiacritics) {
                word = replaceDiacritics(word);
            }
            if (reverse(word).contentEquals(word)) {
                j++;
                output.append(words[i].replaceAll(IGNORED_CHARACTERS_REGEX, "")); // do výstupu psát ten originální ale bez čárek
                if (i != words.length - 1) {
                    output.append(", ");
                }
            }
        }

        System.out.println(j);

        return output.toString();
    }

    /**
     * Zkontroluje pokud vstup s je normalizovaný, tedy jestli nemá žádou diakrotiku
     * @param s - vstup
     * @return - normalizovaný výstup
     */
    private String replaceDiacritics(String s) {
        if (Normalizer.isNormalized(s, Normalizer.Form.NFD)) {
            return s;
        }

        //metoda normalize "rozdělí" znaky na jejich součásti, \p{M} je regex který chytá unicode znaky jako jsou háčky a čárky
        //které se mají používat s jinými znaky a dá je pryč, když zkusíme vypsat výstup z normalize tak to konzole bude brát jako normální unicode znaky
        //a vypíše to i s diakritikou, proto když si to budeme chtít vypsat tak i s replaceAll :)
        return Normalizer.normalize(s, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
    }

    private String reverse(String in) {
        //Používám tady swap algoritmus abych nemusel vytvářet další array
        //Taky nepoužívám samotné stringy ale char[] protože String je v jave immutable
        char[] revesed = in.toCharArray();
        for (int i = 0; i < revesed.length / 2; i++) {
            char t = revesed[i];
            revesed[i] = revesed[revesed.length - i - 1];
            revesed[revesed.length - i - 1] = t;
        }

        return new String(revesed);
    }

    public void setIgnoreSize(boolean ignoreSize) {
        this.ignoreSize = ignoreSize;
    }

    public void setIgnoreDiacritics(boolean ignoreDiacritics) {
        this.ignoreDiacritics = ignoreDiacritics;
    }
}
