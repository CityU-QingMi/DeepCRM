    @Deprecated
    public static <B extends Builder<B>> FileAppender createAppender(
            // @formatter:off
            final String fileName,
            final String append,
            final String locking,
            final String name,
            final String immediateFlush,
            final String ignoreExceptions,
            final String bufferedIo,
            final String bufferSizeStr,
            final Layout<? extends Serializable> layout,
            final Filter filter,
            final String advertise,
            final String advertiseUri,
            final Configuration config) {
        return FileAppender.<B>newBuilder()
            .withAdvertise(Boolean.parseBoolean(advertise))
            .withAdvertiseUri(advertiseUri)
            .withAppend(Booleans.parseBoolean(append, true))
            .withBufferedIo(Booleans.parseBoolean(bufferedIo, true))
            .withBufferSize(Integers.parseInt(bufferSizeStr, DEFAULT_BUFFER_SIZE))
            .setConfiguration(config)
            .withFileName(fileName)
            .withFilter(filter)
            .withIgnoreExceptions(Booleans.parseBoolean(ignoreExceptions, true))
            .withImmediateFlush(Booleans.parseBoolean(immediateFlush, true))
            .withLayout(layout)
            .withLocking(Boolean.parseBoolean(locking))
            .withName(name)
            .build();
        // @formatter:on
    }
