    protected void setOutputStream(final OutputStream os) {
        final byte[] header = layout.getHeader();
        if (header != null) {
            try {
                os.write(header, 0, header.length);
                this.outputStream = os; // only update field if os.write() succeeded
            } catch (final IOException ioe) {
                logError("Unable to write header", ioe);
            }
        } else {
            this.outputStream = os;
        }
    }
