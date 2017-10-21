    public boolean readBoolean() throws IOException {

        int ch = read();

        if (ch < 0) {
            throw new EOFException();
        }

        return ch != 0;
    }
