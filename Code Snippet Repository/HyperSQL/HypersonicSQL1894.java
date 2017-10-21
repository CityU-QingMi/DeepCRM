    public CharArrayWriter(Reader reader) throws IOException {

        buffer = new char[128];

        for (;;) {
            int read = reader.read(buffer, count, buffer.length - count);

            if (read == -1) {
                break;
            }

            count += read;

            if (count == buffer.length) {
                ensureRoom(count * 2);
            }
        }
    }
