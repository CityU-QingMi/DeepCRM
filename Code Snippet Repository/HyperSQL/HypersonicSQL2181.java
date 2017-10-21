    public static byte[] toBinaryUUID(String s) {

        if (s == null) {
            return null;
        }

        if (s.length() != 36) {
            throw new NumberFormatException();
        }

        byte[] bytes = new byte[16];

        for (int i = 0, j = 0; i < bytes.length; ) {
            char c    = s.charAt(j++);
            int  high = getNibble(c);

            c        = s.charAt(j++);
            bytes[i] = (byte) ((high << 4) + getNibble(c));

            i++;

            if (i >= 4 && i <= 10 && (i % 2) == 0) {
                c = s.charAt(j++);

                if (c != '-') {
                    throw new NumberFormatException();
                }
            }
        }

        return bytes;
    }
