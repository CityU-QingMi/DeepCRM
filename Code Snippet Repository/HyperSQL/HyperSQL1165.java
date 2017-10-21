    public int read() throws IOException {

        if (fetchedSize == limitSize) {
            return -1;
        }

        int byteread = is.read();

        if (byteread < 0) {
            if (limitSize == -1) {
                return -1;
            } else {
                throw new IOException("stream not reached the end"
                                      + fetchedSize + " " + limitSize);
            }
        }

        fetchedSize++;

        return byteread;
    }
