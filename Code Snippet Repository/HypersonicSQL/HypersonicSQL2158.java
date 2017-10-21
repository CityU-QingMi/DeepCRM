    public int read() throws IOException {

        if (lastChar >= 0) {
            int val = lastChar & 0xff;

            lastChar = -1;

            pos++;
            return val;
        }

        lastChar = reader.read();

        if (lastChar < 0) {
            return lastChar;
        }

        pos++;
        return lastChar >> 8;
    }
