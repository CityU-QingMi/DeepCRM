    public static int indexOf(byte bytes[], int start, int end, char c) {
        int offset = start;

        while (offset < end) {
            byte b=bytes[offset];
            if (b == c) {
                return offset;
            }
            offset++;
        }
        return -1;
    }
