    @Deprecated
    public static <B extends Builder<B>> MemoryMappedFileAppender createAppender(
            // @formatter:off
            final String fileName, //
            final String append, //
            final String name, //
            final String immediateFlush, //
            final String regionLengthStr, //
            final String ignore, //
            final Layout<? extends Serializable> layout, //
            final Filter filter, //
            final String advertise, //
            final String advertiseURI, //
            final Configuration config) {
            // @formatter:on

        final boolean isAppend = Booleans.parseBoolean(append, true);
        final boolean isImmediateFlush = Booleans.parseBoolean(immediateFlush, false);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
        final boolean isAdvertise = Boolean.parseBoolean(advertise);
        final int regionLength = Integers.parseInt(regionLengthStr, MemoryMappedFileManager.DEFAULT_REGION_LENGTH);

        // @formatter:off
        return MemoryMappedFileAppender.<B>newBuilder()
            .setAdvertise(isAdvertise)
            .setAdvertiseURI(advertiseURI)
            .setAppend(isAppend)
            .setConfiguration(config)
            .setFileName(fileName)
            .withFilter(filter)
            .withIgnoreExceptions(ignoreExceptions)
            .withImmediateFlush(isImmediateFlush)
            .withLayout(layout)
            .withName(name)
            .setRegionLength(regionLength)
            .build();
        // @formatter:on
    }
