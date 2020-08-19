package training.strings;

/**
 * Online test from Gentis Solutions for 84.51 8/19/2020
 *
 * A string is given in the format of "abc, xyz"
 * Scrub characters after the comma in the substring before the comma
 * E.g.
 * "hello world, eo" => "hll wrld"
 * "blue car, abc" => "lue ar"
 */
public class Scrubber {

    public String scrub(String str) {
        int idxOfComma = str.indexOf(",");
        StringBuilder scrubbed = new StringBuilder(str.substring(0, idxOfComma));
        String charsToScrub = str.substring(idxOfComma + 1).trim();

        for (int i = 0; i < scrubbed.length(); i++) {
            if (charsToScrub.indexOf(scrubbed.charAt(i)) != -1) {
                scrubbed.deleteCharAt(i);
            }
        }

        return scrubbed.toString().trim();
    }
}
