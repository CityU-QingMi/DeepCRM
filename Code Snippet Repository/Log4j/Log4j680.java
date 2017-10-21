    @Deprecated
    public static <B extends Builder<B>> RandomAccessFileAppender createAppender(
            final String fileName,
            final String append,
            final String name,
            final String immediateFlush,
            final String bufferSizeStr,
            final String ignore,
            final Layout<? extends Serializable> layout,
            final Filter filter,
            final String advertise,
            final String advertiseURI,
            final Configuration configuration) {

        final boolean isAppend = Booleans.parseBoolean(append, true);
        final boolean isFlush = Booleans.parseBoolean(immediateFlush, true);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
        final boolean isAdvertise = Boolean.parseBoolean(advertise);
        final int bufferSize = Integers.parseInt(bufferSizeStr, RandomAccessFileManager.DEFAULT_BUFFER_SIZE);

        return RandomAccessFileAppender.<B>newBuilder()
            .setAdvertise(isAdvertise)
            .setAdvertiseURI(advertiseURI)
            .setAppend(isAppend)
            .withBufferSize(bufferSize)
            .setConfiguration(configuration)
            .setFileName(fileName)
            .withFilter(filter)
            .withIgnoreExceptions(ignoreExceptions)
            .withImmediateFlush(isFlush)
            .withLayout(layout)
            .withName(name)
            .build();
    }
