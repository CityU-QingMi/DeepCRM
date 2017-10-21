    public final int readUnsignedByte() throws IOException {

        int ch = read();

        if (ch < 0) {
            throw new EOFException();
        }

        return ch;
    }
