    public char readChar() throws IOException {

        int ch1 = read();
        int ch2 = read();

        if ((ch1 | ch2) < 0) {
            throw new EOFException();
        }

        return (char) ((ch1 << 8) + ch2);
    }
