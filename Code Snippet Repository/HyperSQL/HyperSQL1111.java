    public final int readUnsignedShort() throws IOException {

        int ch1 = read();
        int ch2 = read();

        if ((ch1 | ch2) < 0) {
            throw new EOFException();
        }

        return (ch1 << 8) + ch2;
    }
