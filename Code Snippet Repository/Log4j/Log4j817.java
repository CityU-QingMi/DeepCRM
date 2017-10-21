    public boolean execute(final FileVisitor<Path> visitor) throws IOException {
        final long start = System.nanoTime();
        LOGGER.debug("Starting {}", this);

        Files.walkFileTree(getBasePath(), options, maxDepth, visitor);

        final double duration = System.nanoTime() - start;
        LOGGER.debug("{} complete in {} seconds", getClass().getSimpleName(), duration / TimeUnit.SECONDS.toNanos(1));

        // TODO return (visitor.success || ignoreProcessingFailure)
        return true; // do not abort rollover even if processing failed
    }
