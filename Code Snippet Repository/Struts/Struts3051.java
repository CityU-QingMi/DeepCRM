    public final void clear() throws IOException {
        if ((bufferSize == 0) && (out != null))
            // clear() is illegal after any unbuffered output (JSP.5.5)
            throw new IllegalStateException(
                    getLocalizeMessage("jsp.error.ise_on_clear"));
        if (flushed)
            throw new IOException(
                    getLocalizeMessage("jsp.error.attempt_to_clear_flushed_buffer"));
        ensureOpen();
        nextChar = 0;
    }
