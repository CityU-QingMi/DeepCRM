    public int write(InputStream input, int countLimit) throws IOException {

        int left = countLimit;

        ensureRoom(countLimit);

        while (left > 0) {
            int read = input.read(buffer, count, left);

            if (read == -1) {
                break;
            }

            left  -= read;
            count += read;
        }

        return countLimit - left;
    }
