    public void put(final String fqcn, final int c) {
        if (c >= 0) {
            synchronized (this.msg) {
                if (this.closed) {
                    return;
                }
                switch (c) {
                case '\n':
                    log(fqcn);
                    break;
                case '\r':
                    break;
                default:
                    this.msg.append((char) c);
                }
            }
        } else {
            logEnd(fqcn);
        }
    }
