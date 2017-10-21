    public HsqlByteArrayOutputStream(InputStream input) throws IOException {

        buffer = new byte[128];

        for (;;) {
            int read = input.read(buffer, count, buffer.length - count);

            if (read == -1) {
                break;
            }

            count += read;

            if (count == buffer.length) {
                ensureRoom(128);
            }
        }
    }
