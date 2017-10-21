    public void clear() throws IOException {
        if (writer != null) {
            throw new IOException();
        } else {
            nextChar = 0;
            if (LIMIT_BUFFER && (cb.length > Constants.DEFAULT_TAG_BUFFER_SIZE)) {
                bufferSize = Constants.DEFAULT_TAG_BUFFER_SIZE;
                cb = new char[bufferSize];
            }
        }
    }
