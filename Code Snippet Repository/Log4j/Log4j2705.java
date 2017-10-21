    public void put(final String fqcn, final byte[] b, final int off, final int len) throws IOException {
        int curOff = off;
        int curLen = len;
        if (curLen >= 0) {
            synchronized (this.msg) {
                while (curLen > this.buf.remaining()) {
                    final int remaining = this.buf.remaining();
                    this.buf.put(b, curOff, remaining);
                    curLen -= remaining;
                    curOff += remaining;
                    extractMessages(fqcn);
                }
                this.buf.put(b, curOff, curLen);
                extractMessages(fqcn);
            }
        } else {
            logEnd(fqcn);
        }
    }
