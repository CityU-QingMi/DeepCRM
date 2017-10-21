    private SqlFile(Reader reader, String inputStreamLabel, File baseDir) {
        logger.privlog(Level.FINER, "<init>ting SqlFile instance",
                null, 2, FrameworkLogger.class);
        if (reader == null)
            throw new IllegalArgumentException("'reader' may not be null");
        if (inputStreamLabel == null)
            throw new IllegalArgumentException(
                    "'inputStreamLabel' may not be null");

        // Don't try to verify reader.ready() here, since we require it to be
        // reayd to read only in execute(), plus in many caess it's useful for
        // execute() to block.
        this.reader = reader;
        this.inputStreamLabel = inputStreamLabel;
        this.baseDir = (baseDir == null) ? new File(".") : baseDir;
    }
