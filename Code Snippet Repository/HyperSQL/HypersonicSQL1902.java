    public synchronized void write(char[] c, int off,
                                   int len) throws IOException {

        checkClosed();

        if ((off < 0) || (off > c.length) || (len < 0)
                || ((off + len) > c.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        int newcount = count + len;

        if (newcount > buf.length) {
            buf = copyOf(buf, Math.max(buf.length << 1, newcount));
        }

        System.arraycopy(c, off, buf, count, len);

        count = newcount;
    }
