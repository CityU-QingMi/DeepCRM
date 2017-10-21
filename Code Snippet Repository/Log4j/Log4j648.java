    @Deprecated
    protected FileManager(final String fileName, final OutputStream os, final boolean append, final boolean locking,
            final String advertiseURI, final Layout<? extends Serializable> layout, final boolean writeHeader,
            final ByteBuffer buffer) {
        super(os, fileName, layout, writeHeader, buffer);
        this.isAppend = append;
        this.createOnDemand = false;
        this.isLocking = locking;
        this.advertiseURI = advertiseURI;
        this.bufferSize = buffer.capacity();
        this.filePermissions = null;
        this.fileOwner = null;
        this.fileGroup = null;
        this.attributeViewEnabled = false;
    }
