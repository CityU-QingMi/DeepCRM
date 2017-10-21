    public static byte[] hexStringToByteArray(String s) throws IOException {

        int     l    = s.length();
        byte[]  data = new byte[l / 2 + (l % 2)];
        int     n,
                b    = 0;
        boolean high = true;
        int     i    = 0;

        for (int j = 0; j < l; j++) {
            char c = s.charAt(j);

            if (c == ' ') {
                continue;
            }

            n = getNibble(c);

            if (n == -1) {
                throw new IOException(
                    "hexadecimal string contains non hex character");    //NOI18N
            }

            if (high) {
                b    = (n & 0xf) << 4;
                high = false;
            } else {
                b         += (n & 0xf);
                high      = true;
                data[i++] = (byte) b;
            }
        }

        if (!high) {
            throw new IOException(
                "hexadecimal string with odd number of characters");    //NOI18N
        }

        if (i < data.length) {
            data = (byte[]) ArrayUtil.resizeArray(data, i);
        }

        return data;
    }
