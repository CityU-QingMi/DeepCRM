    public void put(final String fqcn, final CharSequence str, final int off, final int len) {
        if (len >= 0) {
            synchronized (this.msg) {
                if (this.closed) {
                    return;
                }
                int start = off;
                final int end = off + len;
                for (int pos = off; pos < end; pos++) {
                    final char c = str.charAt(pos);
                    switch (c) {
                    case '\r':
                    case '\n':
                        this.msg.append(str, start, pos);
                        start = pos + 1;
                        if (c == '\n') {
                            log(fqcn);
                        }
                        break;
                    }
                }
                this.msg.append(str, start, end);
            }
        } else {
            logEnd(fqcn);
        }
    }
