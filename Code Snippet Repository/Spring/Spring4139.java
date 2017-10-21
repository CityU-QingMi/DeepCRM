    ByteVector encodeUTF8(final String s, int i, int maxByteLength) {
        int charLength = s.length();
        int byteLength = i;
        char c;
        for (int j = i; j < charLength; ++j) {
            c = s.charAt(j);
            if (c >= '\001' && c <= '\177') {
                byteLength++;
            } else if (c > '\u07FF') {
                byteLength += 3;
            } else {
                byteLength += 2;
            }
        }
        if (byteLength > maxByteLength) {
            throw new IllegalArgumentException();
        }
        int start = length - i - 2;
        if (start >= 0) {
          data[start] = (byte) (byteLength >>> 8);
          data[start + 1] = (byte) byteLength;
        }
        if (length + byteLength - i > data.length) {
            enlarge(byteLength - i);
        }
        int len = length;
        for (int j = i; j < charLength; ++j) {
            c = s.charAt(j);
            if (c >= '\001' && c <= '\177') {
                data[len++] = (byte) c;
            } else if (c > '\u07FF') {
                data[len++] = (byte) (0xE0 | c >> 12 & 0xF);
                data[len++] = (byte) (0x80 | c >> 6 & 0x3F);
                data[len++] = (byte) (0x80 | c & 0x3F);
            } else {
                data[len++] = (byte) (0xC0 | c >> 6 & 0x1F);
                data[len++] = (byte) (0x80 | c & 0x3F);
            }
        }
        length = len;
        return this;
    }
