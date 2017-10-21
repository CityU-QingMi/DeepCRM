    public int write(Reader input, int countLimit) throws IOException {

        int left = countLimit;

        ensureRoom(countLimit * 2);

        while (left > 0) {
            int c = input.read();

            if (c == -1) {
                break;
            }

            writeChar(c);

            left--;
        }

        return countLimit - left;
    }
