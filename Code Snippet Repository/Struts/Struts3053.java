    public void write(int c) throws IOException {
        ensureOpen();
        if (bufferSize == 0) {
            initOut();
            out.write(c);
        }
        else {
            if (nextChar >= bufferSize)
                if (autoFlush)
                    flushBuffer();
                else
                    bufferOverflow();
            cb[nextChar++] = (char) c;
        }
    }
