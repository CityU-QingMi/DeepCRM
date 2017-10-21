    public static String hexCharsToOctalOctets(String hexChars) {
        int chars = hexChars.length();
        if (chars != (chars / 2) * 2) {
            throw new IllegalArgumentException("Hex character lists contains "
                + "an odd number of characters: " + chars);
        }
        StringBuffer sb = new StringBuffer();
        char c;
        int octet;
        for (int i = 0; i < chars; i++) {
            octet = 0;
            c = hexChars.charAt(i);
            if (c >= 'a' && c <= 'f') {
                octet += 10 + c - 'a';
            } else if (c >= 'A' && c <= 'F') {
                octet += 10 + c - 'A';
            } else if (c >= '0' && c <= '9') {
                octet += c - '0';
            } else {
                throw new IllegalArgumentException(
                    "Non-hex character in input at offset " + i + ": " + c);
            }
            octet = octet << 4;
            c = hexChars.charAt(++i);
            if (c >= 'a' && c <= 'f') {
                octet += 10 + c - 'a';
            } else if (c >= 'A' && c <= 'F') {
                octet += 10 + c - 'A';
            } else if (c >= '0' && c <= '9') {
                octet += c - '0';
            } else {
                throw new IllegalArgumentException(
                    "Non-hex character in input at offset " + i + ": " + c);
            }

            sb.append('\\');
            sb.append((char) ('0' + (octet >> 6)));
            sb.append((char) ('0' + ((octet >> 3) & 7)));
            sb.append((char) ('0' + (octet & 7)));
        }
        return sb.toString();
    }
