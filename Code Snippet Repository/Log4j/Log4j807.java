    public RollingRandomAccessFileManager(final LoggerContext loggerContext, final RandomAccessFile raf,
            final String fileName, final String pattern, final OutputStream os, final boolean append,
            final boolean immediateFlush, final int bufferSize, final long size, final long time,
            final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI,
            final Layout<? extends Serializable> layout,
            final String filePermissions, final String fileOwner, final String fileGroup,
            final boolean writeHeader) {
        super(loggerContext, fileName, pattern, os, append, false, size, time, policy, strategy, advertiseURI, layout,
                filePermissions, fileOwner, fileGroup,
                writeHeader, ByteBuffer.wrap(new byte[bufferSize]));
        this.randomAccessFile = raf;
        isEndOfBatch.set(Boolean.FALSE);
        writeHeader();
    }
