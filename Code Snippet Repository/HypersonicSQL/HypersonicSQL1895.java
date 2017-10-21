    public int write(Reader reader, int length) throws IOException {

        int left = length;

        while (left > 0) {
            int read = reader.read(buffer, count, left);

            if (read == -1) {
                break;
            }

            left  -= read;
            count += read;
        }

        return length - left;
    }
