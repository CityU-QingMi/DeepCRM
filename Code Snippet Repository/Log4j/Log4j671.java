    @Deprecated
    protected OutputStreamManager(final OutputStream os, final String streamName, final Layout<?> layout,
            final boolean writeHeader, final ByteBuffer byteBuffer) {
        super(null, streamName);
        this.outputStream = os;
        this.layout = layout;
        if (writeHeader && layout != null) {
            final byte[] header = layout.getHeader();
            if (header != null) {
                try {
                    getOutputStream().write(header, 0, header.length);
                } catch (final IOException e) {
                    logError("Unable to write header", e);
                }
            }
        }
        this.byteBuffer = Objects.requireNonNull(byteBuffer, "byteBuffer");
    }
