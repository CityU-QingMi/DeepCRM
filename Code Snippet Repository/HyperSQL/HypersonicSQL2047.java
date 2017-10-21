    public int read(byte[] bytes, int offset, int length) throws IOException {

        if (fetchedSize == limitSize) {
            return -1;
        }

        if (limitSize >= 0 && limitSize - fetchedSize < length) {
            length = (int) (limitSize - fetchedSize);
        }

        int count = is.read(bytes, offset, length);

        if (count < 0) {
            if (limitSize == -1) {
                return -1;
            } else {
                throw new IOException("stream not reached the end"
                                      + fetchedSize + " " + limitSize);
            }
        }

        fetchedSize += count;

        return count;
    }
