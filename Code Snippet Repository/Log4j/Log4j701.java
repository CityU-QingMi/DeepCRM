    public WriterManager(final Writer writer, final String streamName, final StringLayout layout,
            final boolean writeHeader) {
        super(null, streamName);
        this.writer = writer;
        this.layout = layout;
        if (writeHeader && layout != null) {
            final byte[] header = layout.getHeader();
            if (header != null) {
                try {
                    this.writer.write(new String(header, layout.getCharset()));
                } catch (final IOException e) {
                    logError("Unable to write header", e);
                }
            }
        }
    }
