    public synchronized void write(int b) throws IOException {

        checkClosed();

        int newcount = count + 1;

        if (newcount > buf.length) {
            buf = copyOf(buf, Math.max(buf.length << 1, newcount));
        }

        buf[count] = (byte) b;
        count      = newcount;
    }
