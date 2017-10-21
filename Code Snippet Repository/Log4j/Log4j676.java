    protected synchronized boolean closeOutputStream() {
        flush();
        final OutputStream stream = outputStream; // access volatile field only once per method
        if (stream == null || stream == System.out || stream == System.err) {
            return true;
        }
        try {
            stream.close();
        } catch (final IOException ex) {
            logError("Unable to close stream", ex);
            return false;
        }
        return true;
    }
