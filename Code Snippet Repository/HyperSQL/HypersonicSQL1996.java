    public byte readByte() throws IOException {

        int ch = read();

        if (ch < 0) {
            throw new EOFException();
        }

        return (byte) ch;
    }
