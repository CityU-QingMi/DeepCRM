    @Deprecated
    public static int findNotBytes(byte bytes[], int start, int end, byte b[]) {
        int blen = b.length;
        int offset = start;
        boolean found;

        while (offset < end) {
            found = true;
            for (int i = 0; i < blen; i++) {
                if (bytes[offset] == b[i]) {
                    found=false;
                    break;
                }
            }
            if (found) {
                return offset;
            }
            offset++;
        }
        return -1;
    }
