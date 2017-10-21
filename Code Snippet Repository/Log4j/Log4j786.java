    private int purge(final RollingFileManager manager) {
        final SortedMap<Integer, Path> eligibleFiles = getEligibleFiles(manager);
        LOGGER.debug("Found {} eligible files, max is  {}", eligibleFiles.size(), maxFiles);
        while (eligibleFiles.size() >= maxFiles) {
            try {
                final Integer key = eligibleFiles.firstKey();
                Files.delete(eligibleFiles.get(key));
                eligibleFiles.remove(key);
            } catch (final IOException ioe) {
                LOGGER.error("Unable to delete {}", eligibleFiles.firstKey(), ioe);
                break;
            }
        }
        return eligibleFiles.size() > 0 ? eligibleFiles.lastKey() : 1;
    }
