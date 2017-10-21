    public void put(final String fqcn, final int b) throws IOException {
        if (b >= 0) {
            synchronized (this.msg) {
                this.buf.put((byte) (b & 0xFF));
                extractMessages(fqcn);
            }
        } else {
            logEnd(fqcn);
        }
    }
