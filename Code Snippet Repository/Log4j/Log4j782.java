    private int purgeDescending(final int lowIndex, final int highIndex, final RollingFileManager manager) {
        // Retrieve the files in descending order, so the highest key will be first.
        final SortedMap<Integer, Path> eligibleFiles = getEligibleFiles(manager, false);
        final int maxFiles = highIndex - lowIndex + 1;

        while (eligibleFiles.size() >= maxFiles) {
            try {
                final Integer key = eligibleFiles.firstKey();
                Files.delete(eligibleFiles.get(key));
                eligibleFiles.remove(key);
            } catch (final IOException ioe) {
                LOGGER.error("Unable to delete {}, {}", eligibleFiles.firstKey(), ioe.getMessage(), ioe);
                break;
            }
        }
        final StringBuilder buf = new StringBuilder();
        for (final Map.Entry<Integer, Path> entry : eligibleFiles.entrySet()) {
            buf.setLength(0);
            // LOG4J2-531: directory scan & rollover must use same format
            manager.getPatternProcessor().formatFileName(strSubstitutor, buf, entry.getKey() + 1);
            final String currentName = entry.getValue().toFile().getName();
            String renameTo = buf.toString();
            final int suffixLength = suffixLength(renameTo);
            if (suffixLength > 0 && suffixLength(currentName) == 0) {
                renameTo = renameTo.substring(0, renameTo.length() - suffixLength);
            }
            final Action action = new FileRenameAction(entry.getValue().toFile(), new File(renameTo), true);
            try {
                LOGGER.debug("DefaultRolloverStrategy.purgeDescending executing {}", action);
                if (!action.execute()) {
                    return -1;
                }
            } catch (final Exception ex) {
                LOGGER.warn("Exception during purge in RollingFileAppender", ex);
                return -1;
            }
        }

        return lowIndex;
    }
