    public int read(byte[] b, int off, int len) {

        if (pos >= count) {
            return -1;
        }

        if (pos + len > count) {
            len = count - pos;
        }

        if (len <= 0) {
            return 0;
        }

        System.arraycopy(buffer, pos, b, off, len);

        pos += len;

        return len;
    }
