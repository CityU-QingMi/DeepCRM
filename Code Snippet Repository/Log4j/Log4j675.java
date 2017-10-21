    protected synchronized void flushDestination() {
        final OutputStream stream = outputStream; // access volatile field only once per method
        if (stream != null) {
            try {
                stream.flush();
            } catch (final IOException ex) {
                throw new AppenderLoggingException("Error flushing stream " + getName(), ex);
            }
        }
    }
