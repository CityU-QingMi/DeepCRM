    protected MemoryMappedFileManager(final RandomAccessFile file, final String fileName, final OutputStream os,
            final boolean immediateFlush, final long position, final int regionLength, final String advertiseURI,
            final Layout<? extends Serializable> layout, final boolean writeHeader) throws IOException {
        super(os, fileName, layout, writeHeader, ByteBuffer.wrap(new byte[0]));
        this.immediateFlush = immediateFlush;
        this.randomAccessFile = Objects.requireNonNull(file, "RandomAccessFile");
        this.regionLength = regionLength;
        this.advertiseURI = advertiseURI;
        this.isEndOfBatch.set(Boolean.FALSE);
        this.mappedBuffer = mmap(randomAccessFile.getChannel(), getFileName(), position, regionLength);
        this.byteBuffer = mappedBuffer;
        this.mappingOffset = position;
    }
