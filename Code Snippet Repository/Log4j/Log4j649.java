    @Deprecated
    protected FileManager(final LoggerContext loggerContext, final String fileName, final OutputStream os, final boolean append, final boolean locking,
            final boolean createOnDemand, final String advertiseURI, final Layout<? extends Serializable> layout,
            final boolean writeHeader, final ByteBuffer buffer) {
        super(loggerContext, os, fileName, createOnDemand, layout, writeHeader, buffer);
        this.isAppend = append;
        this.createOnDemand = createOnDemand;
        this.isLocking = locking;
        this.advertiseURI = advertiseURI;
        this.bufferSize = buffer.capacity();
        this.filePermissions = null;
        this.fileOwner = null;
        this.fileGroup = null;
        this.attributeViewEnabled = false;
    }
