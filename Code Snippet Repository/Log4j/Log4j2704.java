    private void extractMessages(final String fqcn) throws IOException {
        if (this.closed) {
            return;
        }
        int read = this.reader.read(this.msgBuf);
        while (read > 0) {
            int off = 0;
            for (int pos = 0; pos < read; pos++) {
                switch (this.msgBuf[pos]) {
                case '\r':
                    this.msg.append(this.msgBuf, off, pos - off);
                    off = pos + 1;
                    break;
                case '\n':
                    this.msg.append(this.msgBuf, off, pos - off);
                    off = pos + 1;
                    log(fqcn);
                    break;
                }
            }
            this.msg.append(this.msgBuf, off, read - off);
            read = this.reader.read(this.msgBuf);
        }
    }
