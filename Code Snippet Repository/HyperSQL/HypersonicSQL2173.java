    public static int stringToUTFBytes(String str,
                                       HsqlByteArrayOutputStream out) {

        int strlen = str.length();
        int c,
            count  = 0;

        if (out.count + strlen + 8 > out.buffer.length) {
            out.ensureRoom(strlen + 8);
        }

        char[] arr = str.toCharArray();

        for (int i = 0; i < strlen; i++) {
            c = arr[i];

            if (c >= 0x0001 && c <= 0x007F) {
                out.buffer[out.count++] = (byte) c;

                count++;
            } else if (c > 0x07FF) {
                out.buffer[out.count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                out.buffer[out.count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
                out.buffer[out.count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
                count                   += 3;
            } else {
                out.buffer[out.count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
                out.buffer[out.count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
                count                   += 2;
            }

            if (out.count + 8 > out.buffer.length) {
                out.ensureRoom(strlen - i + 8);
            }
        }

        return count;
    }
