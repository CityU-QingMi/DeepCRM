    protected OutputStreamManager(final LoggerContext loggerContext, final OutputStream os, final String streamName,
            final boolean createOnDemand, final Layout<? extends Serializable> layout, final boolean writeHeader,
            final ByteBuffer byteBuffer) {
        super(loggerContext, streamName);
        if (createOnDemand && os != null) {
            LOGGER.error(
                    "Invalid OutputStreamManager configuration for '{}': You cannot both set the OutputStream and request on-demand.",
                    streamName);
        }
        this.layout = layout;
        this.byteBuffer = Objects.requireNonNull(byteBuffer, "byteBuffer");
        this.outputStream = os;
        if (writeHeader && layout != null) {
            final byte[] header = layout.getHeader();
            if (header != null) {
                try {
                    getOutputStream().write(header, 0, header.length);
                } catch (final IOException e) {
                    logError("Unable to write header for " + streamName, e);
                }
            }
        }
    }
