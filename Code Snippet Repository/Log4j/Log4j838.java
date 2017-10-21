    @Override
    protected FileVisitor<Path> createFileVisitor(final Path basePath,
            final List<PathCondition> conditions) {
        return new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                for (final PathCondition pathFilter : conditions) {
                    final Path relative = basePath.relativize(file);
                    if (!pathFilter.accept(basePath, relative, attrs)) {
                        LOGGER.trace("Not defining posix attribute base={}, relative={}", basePath, relative);
                        return FileVisitResult.CONTINUE;
                    }
                }
                FileUtils.defineFilePosixAttributeView(file, filePermissions, fileOwner, fileGroup);
                return FileVisitResult.CONTINUE;
            }
        };
    }
