    public synchronized void write(String str, int off,
                                   int len) throws IOException {

        checkClosed();

        int strlen = str.length();

        if ((off < 0) || (off > strlen) || (len < 0) || ((off + len) > strlen)
                || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        int newcount = count + len;

        if (newcount > buf.length) {
            buf = copyOf(buf, Math.max(buf.length << 1, newcount));
        }

        str.getChars(off, off + len, buf, count);

        count = newcount;
    }
