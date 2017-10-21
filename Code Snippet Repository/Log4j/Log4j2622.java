        @Override
        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
            if (logger != null) {
                logger.debug(CLEANER_MARKER, "Deleting file {} with {}", file, attrs);
            }
            final boolean deleted = Files.deleteIfExists(file);
            if (logger != null) {
                logger.debug(CLEANER_MARKER, "Deleted file {}: {}", file, deleted);
            }
            return FileVisitResult.CONTINUE;
        }
