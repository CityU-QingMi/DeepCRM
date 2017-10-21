    public HsqlByteArrayOutputStream(InputStream input,
                                     int length) throws IOException {

        buffer = new byte[length];

        int used = write(input, length);

        if (used != length) {
            throw new EOFException();
        }
    }
