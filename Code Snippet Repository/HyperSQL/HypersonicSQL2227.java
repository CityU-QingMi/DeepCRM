    public void close() throws IOException {

        if (readStream == null) {
            return;
        }

        try {
            readStream.close();
        } finally {
            readStream = null;    // Encourage buffer GC
        }
    }
