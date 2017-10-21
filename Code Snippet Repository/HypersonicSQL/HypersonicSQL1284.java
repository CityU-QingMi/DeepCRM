    public static byte[] hexCharOctetsToBytes(String hexChars) {
        int chars = hexChars.length();
        if (chars != (chars / 2) * 2)
            throw new NumberFormatException("Hex character lists contains "
                + "an odd number of characters: " + chars);
        byte[] ba = new byte[chars/2];
        int offset = 0;
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
                throw new NumberFormatException(
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
                throw new NumberFormatException(
                    "Non-hex character in input at offset " + i + ": " + c);
            }

            ba[offset++] = (byte) octet;
        }
        assert ba.length == offset:
            "Internal accounting problem.  Expected to fill buffer of "
            + "size "+ ba.length + ", but wrote only " + offset + " bytes";
        return ba;
    }
