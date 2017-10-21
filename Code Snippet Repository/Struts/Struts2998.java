    public void write(int c) throws IOException {
        if (writer != null) {
            writer.write(c);
        } else {
            ensureOpen();
            if (nextChar >= bufferSize) {
                reAllocBuff (1);
            }
            cb[nextChar++] = (char) c;
        }
    }
