    public int readInt() throws IOException {

        if (count - pos < 4) {
            pos = count;

            throw new EOFException();
        }

        int ch1 = buffer[pos++] & 0xff;
        int ch2 = buffer[pos++] & 0xff;
        int ch3 = buffer[pos++] & 0xff;
        int ch4 = buffer[pos++] & 0xff;

        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + ch4);
    }
