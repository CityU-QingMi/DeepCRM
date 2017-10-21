    public TarFileInputStream(File sourceFile, int compressionType,
                              int readBufferBlocks) throws IOException {

        if (!sourceFile.isFile()) {
            throw new FileNotFoundException(sourceFile.getAbsolutePath());
        }

        if (!sourceFile.canRead()) {
            throw new IOException(
                RB.read_denied.getString(sourceFile.getAbsolutePath()));
        }

        this.readBufferBlocks = readBufferBlocks;
        this.compressionType  = compressionType;
        readBuffer            = new byte[readBufferBlocks * 512];

        switch (compressionType) {

            case TarFileOutputStream.Compression.NO_COMPRESSION :
                readStream = new FileInputStream(sourceFile);
                break;

            case TarFileOutputStream.Compression.GZIP_COMPRESSION :
                readStream =
                    new GZIPInputStream(new FileInputStream(sourceFile),
                                        readBuffer.length);
                break;

            default :
                throw new IllegalArgumentException(
                    RB.compression_unknown.getString(compressionType));
        }
    }
