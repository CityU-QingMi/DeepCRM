    public static BitMap sqlBitStringToBitMap(String s) throws IOException {

        int    l = s.length();
        int    n;
        int    bitIndex = 0;
        BitMap map      = new BitMap(0, true);

        for (int j = 0; j < l; j++) {
            char c = s.charAt(j);

            if (c == ' ') {
                continue;
            }

            n = getNibble(c);

            if (n != 0 && n != 1) {
                throw new IOException(
                    "hexadecimal string contains non hex character");    //NOI18N
            }

            if (n == 1) {
                map.set(bitIndex);
            }

            bitIndex++;
        }

        map.setSize(bitIndex);

        return map;
    }
