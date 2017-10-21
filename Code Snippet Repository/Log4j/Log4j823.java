    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        for (final PathCondition pathFilter : pathConditions) {
            final Path relative = basePath.relativize(file);
            if (!pathFilter.accept(basePath, relative, attrs)) {
                LOGGER.trace("Not deleting base={}, relative={}", basePath, relative);
                return FileVisitResult.CONTINUE;
            }
        }
        if (isTestMode()) {
            LOGGER.info("Deleting {} (TEST MODE: file not actually deleted)", file);
        } else {
            delete(file);
        }
        return FileVisitResult.CONTINUE;
    }
