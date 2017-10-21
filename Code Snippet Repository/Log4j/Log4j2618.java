    public AbstractExternalFileCleaner(final boolean before, final boolean after, final int maxTries, Logger logger,
            final File... files) {
        this.cleanBefore = before;
        this.cleanAfter = after;
        this.maxTries = maxTries;
        this.files = new HashSet<>(files.length);
        this.logger = logger;
        for (final File file : files) {
            this.files.add(file.toPath());
        }
    }
