    public static void stringToHtmlBytes(HsqlByteArrayOutputStream b,
                                         String s) {

        if (s == null) {
            return;
        }

        final int len = s.length();
        char[]    chars;

        if (len == 0) {
            return;
        }

        chars = s.toCharArray();

        b.ensureRoom(len);

        for (int i = 0; i < len; i++) {
            char c = chars[i];

            if (c > 0x007f || c == '"' || c == '&' || c == '<' || c == '>') {
                int codePoint = Character.codePointAt(chars, i);

                if (Character.charCount(codePoint) == 2) {
                    i++;
                }

                b.ensureRoom(16);
                b.writeNoCheck('&');
                b.writeNoCheck('#');
                b.writeBytes(String.valueOf(codePoint));
                b.writeNoCheck(';');
            } else if (c < 0x0020) {
                b.writeNoCheck(' ');
            } else {
                b.writeNoCheck(c);
            }
        }
    }
