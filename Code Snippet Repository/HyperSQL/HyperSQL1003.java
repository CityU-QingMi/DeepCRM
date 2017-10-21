    public synchronized ByteArrayInputStream toByteArrayInputStream()
    throws IOException {

        checkFreed();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(buf, 0,
            count);

        free();

        return inputStream;
    }
