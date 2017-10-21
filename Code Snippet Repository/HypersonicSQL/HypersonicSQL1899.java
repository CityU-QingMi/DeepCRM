    public synchronized void write(byte[] b, int off,
                                   int len) throws IOException {

        checkClosed();

        if ((off < 0) || (off > b.length) || (len < 0)
                || ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        int newcount = count + len;

        if (newcount > buf.length) {
            buf = copyOf(buf, Math.max(buf.length << 1, newcount));
        }

        System.arraycopy(b, off, buf, count, len);

        count = newcount;
    }
