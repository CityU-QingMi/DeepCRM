    public AbstractExternalFileCleaner(final boolean before, final boolean after, final int maxTries, Logger logger,
            final String... fileNames) {
        this.cleanBefore = before;
        this.cleanAfter = after;
        this.maxTries = maxTries;
        this.logger = logger;
        this.files = new HashSet<>(fileNames.length);
        for (final String fileName : fileNames) {
            this.files.add(Paths.get(fileName));
        }
    }
