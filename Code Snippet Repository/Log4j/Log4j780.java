    protected DefaultRolloverStrategy(final int minIndex, final int maxIndex, final boolean useMax,
            final int compressionLevel, final StrSubstitutor strSubstitutor, final Action[] customActions,
            final boolean stopCustomActionsOnError, final String tempCompressedFilePatternString) {
        super(strSubstitutor);
        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
        this.useMax = useMax;
        this.compressionLevel = compressionLevel;
        this.stopCustomActionsOnError = stopCustomActionsOnError;
        this.customActions = customActions == null ? Collections.<Action> emptyList() : Arrays.asList(customActions);
        this.tempCompressedFilePattern =
                tempCompressedFilePatternString != null ? new PatternProcessor(tempCompressedFilePatternString) : null;
    }
