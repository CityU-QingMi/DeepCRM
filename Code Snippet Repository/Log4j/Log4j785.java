    protected DirectWriteRolloverStrategy(final int maxFiles, final int compressionLevel,
                                          final StrSubstitutor strSubstitutor, final Action[] customActions,
                                          final boolean stopCustomActionsOnError, final String tempCompressedFilePatternString) {
        super(strSubstitutor);
        this.maxFiles = maxFiles;
        this.compressionLevel = compressionLevel;
        this.stopCustomActionsOnError = stopCustomActionsOnError;
        this.customActions = customActions == null ? Collections.<Action> emptyList() : Arrays.asList(customActions);
        this.tempCompressedFilePattern =
                tempCompressedFilePatternString != null ? new PatternProcessor(tempCompressedFilePatternString) : null;
    }
