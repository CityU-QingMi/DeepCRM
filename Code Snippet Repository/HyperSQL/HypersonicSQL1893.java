    public CharArrayWriter(Reader reader, int length) throws IOException {

        buffer = new char[length];

        for (int left = length; left > 0; ) {
            int read = reader.read(buffer, count, left);

            if (read == -1) {
                if (left > 0) {
                    reader.close();

                    throw new EOFException();
                }

                break;
            }

            left  -= read;
            count += read;
        }
    }
