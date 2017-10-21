    public short readShort() throws IOException {

        if (count - pos < 2) {
            pos = count;

            throw new EOFException();
        }

        int ch1 = buffer[pos++] & 0xff;
        int ch2 = buffer[pos++] & 0xff;

        return (short) ((ch1 << 8) + ch2);
    }
