        @Override
        public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
            if (logger != null) {
                logger.debug(CLEANER_MARKER, "Deleting directory {}", dir);
            }
            final boolean deleted = Files.deleteIfExists(dir);
            if (logger != null) {
                logger.debug(CLEANER_MARKER, "Deleted directory {}: {}", dir, deleted);
            }
            return FileVisitResult.CONTINUE;
        }
