    @Deprecated
    protected RollingFileManager(final LoggerContext loggerContext, final String fileName, final String pattern, final OutputStream os,
            final boolean append, final boolean createOnDemand, final long size, final long time,
            final TriggeringPolicy triggeringPolicy, final RolloverStrategy rolloverStrategy,
            final String advertiseURI, final Layout<? extends Serializable> layout, final boolean writeHeader, final ByteBuffer buffer) {
        super(loggerContext, fileName, os, append, false, createOnDemand, advertiseURI, layout, writeHeader, buffer);
        this.size = size;
        this.initialTime = time;
        this.triggeringPolicy = triggeringPolicy;
        this.rolloverStrategy = rolloverStrategy;
        this.patternProcessor = new PatternProcessor(pattern);
        this.patternProcessor.setPrevFileTime(time);
        this.fileName = fileName;
        this.fileExtension = FileExtension.lookupForFile(pattern);
    }
