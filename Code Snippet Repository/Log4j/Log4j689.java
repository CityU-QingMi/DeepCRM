    @Deprecated
    public static <B extends Builder<B>> RollingRandomAccessFileAppender createAppender(
            final String fileName,
            final String filePattern,
            final String append,
            final String name,
            final String immediateFlush,
            final String bufferSizeStr,
            final TriggeringPolicy policy,
            final RolloverStrategy strategy,
            final Layout<? extends Serializable> layout,
            final Filter filter,
            final String ignoreExceptions,
            final String advertise,
            final String advertiseURI,
            final Configuration configuration) {

        final boolean isAppend = Booleans.parseBoolean(append, true);
        final boolean isIgnoreExceptions = Booleans.parseBoolean(ignoreExceptions, true);
        final boolean isImmediateFlush = Booleans.parseBoolean(immediateFlush, true);
        final boolean isAdvertise = Boolean.parseBoolean(advertise);
        final int bufferSize = Integers.parseInt(bufferSizeStr, RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE);

        return RollingRandomAccessFileAppender.<B>newBuilder()
           .withAdvertise(isAdvertise)
           .withAdvertiseURI(advertiseURI)
           .withAppend(isAppend)
           .withBufferSize(bufferSize)
           .setConfiguration(configuration)
           .withFileName(fileName)
           .withFilePattern(filePattern)
           .withFilter(filter)
           .withIgnoreExceptions(isIgnoreExceptions)
           .withImmediateFlush(isImmediateFlush)
           .withLayout(layout)
           .withName(name)
           .withPolicy(policy)
           .withStrategy(strategy)
           .build();
    }
