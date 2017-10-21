    protected void setWriter(final Writer writer) {
        final byte[] header = layout.getHeader();
        if (header != null) {
            try {
                writer.write(new String(header, layout.getCharset()));
                this.writer = writer; // only update field if writer.write() succeeded
            } catch (final IOException ioe) {
                logError("Unable to write header", ioe);
            }
        } else {
            this.writer = writer;
        }
    }
