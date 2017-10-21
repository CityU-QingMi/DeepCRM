    public void write(String s, int off, int len) throws IOException {
        ensureOpen();
        if (bufferSize == 0) {
            initOut();
            out.write(s, off, len);
            return;
        }
        int b = off, t = off + len;
        while (b < t) {
            int d = min(bufferSize - nextChar, t - b);
            s.getChars(b, b + d, cb, nextChar);
            b += d;
            nextChar += d;
            if (nextChar >= bufferSize) 
                if (autoFlush)
                    flushBuffer();
                else
                    bufferOverflow();
        }
    }
