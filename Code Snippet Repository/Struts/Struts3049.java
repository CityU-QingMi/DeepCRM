    protected final void flushBuffer() throws IOException {
        if (bufferSize == 0)
            return;
        flushed = true;
        ensureOpen();
        if (nextChar == 0)
            return;
        initOut();
        out.write(cb, 0, nextChar);
        nextChar = 0;
    }
