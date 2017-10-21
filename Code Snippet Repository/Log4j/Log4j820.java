    private void deleteSelectedFiles(final List<PathWithAttributes> selectedForDeletion) throws IOException {
        trace("Paths the script selected for deletion:", selectedForDeletion);
        for (final PathWithAttributes pathWithAttributes : selectedForDeletion) {
            final Path path = pathWithAttributes == null ? null : pathWithAttributes.getPath();
            if (isTestMode()) {
                LOGGER.info("Deleting {} (TEST MODE: file not actually deleted)", path);
            } else {
                delete(path);
            }
        }
    }
