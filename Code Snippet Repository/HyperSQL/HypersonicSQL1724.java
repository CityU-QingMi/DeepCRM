    protected synchronized void close() {

        this.closed = true;

        setReadable(false);
        setWritable(false);
        freeOutputStream();
        freeInputStream();
        freeDomResult();

        this.gzdata = null;
    }
