    public static int findByte(byte bytes[], int start, int end, byte b) {
        int offset = start;
        while (offset < end) {
            if (bytes[offset] == b) {
                return offset;
            }
            offset++;
        }
        return -1;
    }
