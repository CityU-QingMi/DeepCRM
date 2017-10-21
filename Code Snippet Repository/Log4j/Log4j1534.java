    public static void quoteAsString(final CharSequence input, final StringBuilder output) {
        final char[] qbuf = getQBuf();
        final int escCodeCount = ESC_CODES.length;
        int inPtr = 0;
        final int inputLen = input.length();

        outer:
        while (inPtr < inputLen) {
            tight_loop:
            while (true) {
                final char c = input.charAt(inPtr);
                if (c < escCodeCount && ESC_CODES[c] != 0) {
                    break tight_loop;
                }
                output.append(c);
                if (++inPtr >= inputLen) {
                    break outer;
                }
            }
            // something to escape; 2 or 6-char variant?
            final char d = input.charAt(inPtr++);
            final int escCode = ESC_CODES[d];
            final int length = (escCode < 0)
                    ? _appendNumeric(d, qbuf)
                    : _appendNamed(escCode, qbuf);

            output.append(qbuf, 0, length);
        }
    }
