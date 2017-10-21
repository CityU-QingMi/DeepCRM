    public int read(char[] cbuf, int off, int len) throws IOException {

        checkClosed();

        if (currentPosition >= availableLength) {
            return -1;
        }

        if (currentPosition + len > availableLength) {
            len = (int) (availableLength - currentPosition);
        }

        for (int i = off; i < off + len && i < cbuf.length; i++) {
            cbuf[i] = (char) read();
        }

        return len;
    }
