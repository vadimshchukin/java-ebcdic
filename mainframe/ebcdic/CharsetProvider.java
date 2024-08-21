package mainframe.ebcdic;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetProvider extends java.nio.charset.spi.CharsetProvider {
    private List<Charset> charsets = new ArrayList<>();

    public CharsetProvider() {
        Set<Integer> encodingsEBCDIC = new TreeSet<>(Arrays.asList(
            256, 273, 277, 278, 280, 282, 284, 285, 286, 290, 297, 420, 421, 423, 424, 500, 803, 833, 834, 835, 836,
            837, 838, 839, 870, 871, 875, 880, 905, 918, 930, 931, 933, 939, 1025, 1027, 1097, 4133, 4369, 4370, 4371,
            4372, 4373, 4374, 4376, 4378, 4380, 4381, 4386, 4393, 4396, 4516, 4519, 4520, 4596, 4929, 4932, 4934, 4967,
            4976, 5014, 5026, 5029, 5031, 5033, 5035, 8229, 8448, 8476, 8489, 8612, 8692, 9025, 9026, 9122, 9124, 12325,
            12544, 12788, 13218, 13219, 13221, 16421, 16884, 20517, 20980, 24613, 25076, 28709, 29172, 32805, 33058,
            33268, 37364, 41460, 45556, 49652, 53748, 59748, 61696, 61711, 61712
        ));

        for (Integer encoding : encodingsEBCDIC) {
            this.charsets.add(new EBCDIC("cp" + Integer.toString(encoding), new String[] {}));
        }
    }

    public Charset charsetForName(String charsetName) {
        charsetName = charsetName.toLowerCase(Locale.US);
        for (Iterator iterator = charsets.iterator(); iterator.hasNext();) {
            Charset charset = (Charset)iterator.next();
            if (charset.name().equals(charsetName))
                return charset;
            if (charset.aliases().contains(charsetName))
                return charset;
        }
        return null;
    }

    public Iterator<Charset> charsets() {
        return charsets.iterator();
    }
}

class EBCDIC extends Charset {
    private Charset charset = Charset.forName("cp037");

    public EBCDIC(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    public boolean contains(final Charset cs) {
        return false;
    }

    public CharsetEncoder newEncoder() {
        return charset.newEncoder();
    }

    public CharsetDecoder newDecoder() {
        return charset.newDecoder();
    }
}