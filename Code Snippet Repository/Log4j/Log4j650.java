    protected FileManager(final LoggerContext loggerContext, final String fileName, final OutputStream os, final boolean append, final boolean locking,
            final boolean createOnDemand, final String advertiseURI, final Layout<? extends Serializable> layout,
            final String filePermissions, final String fileOwner, final String fileGroup, final boolean writeHeader,
            final ByteBuffer buffer) {
        super(loggerContext, os, fileName, createOnDemand, layout, writeHeader, buffer);
        this.isAppend = append;
        this.createOnDemand = createOnDemand;
        this.isLocking = locking;
        this.advertiseURI = advertiseURI;
        this.bufferSize = buffer.capacity();

        final Set<String> views = FileSystems.getDefault().supportedFileAttributeViews();
        if (views.contains("posix")) {
            this.filePermissions = filePermissions != null ? PosixFilePermissions.fromString(filePermissions) : null;
            this.fileGroup = fileGroup;
        } else {
            this.filePermissions = null;
            this.fileGroup = null;
            if (filePermissions != null) {
                LOGGER.warn("Posix file attribute permissions defined but it is not supported by this files system.");
            }
            if (fileGroup != null) {
                LOGGER.warn("Posix file attribute group defined but it is not supported by this files system.");
            }
        }

        if (views.contains("owner")) {
            this.fileOwner = fileOwner;
        } else {
            this.fileOwner = null;
            if (fileOwner != null) {
                LOGGER.warn("Owner file attribute defined but it is not supported by this files system.");
            }
        }

        // Supported and defined
        this.attributeViewEnabled = this.filePermissions != null || this.fileOwner != null || this.fileGroup != null;
    }
