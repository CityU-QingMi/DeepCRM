    private int purgeAscending(final int lowIndex, final int highIndex, final RollingFileManager manager) {
        final SortedMap<Integer, Path> eligibleFiles = getEligibleFiles(manager);
        final int maxFiles = highIndex - lowIndex + 1;

        boolean renameFiles = false;
        while (eligibleFiles.size() >= maxFiles) {
            try {
                LOGGER.debug("Eligible files: {}", eligibleFiles);
                final Integer key = eligibleFiles.firstKey();
                LOGGER.debug("Deleting {}", eligibleFiles.get(key).toFile().getAbsolutePath());
                Files.delete(eligibleFiles.get(key));
                eligibleFiles.remove(key);
                renameFiles = true;
            } catch (final IOException ioe) {
                LOGGER.error("Unable to delete {}, {}", eligibleFiles.firstKey(), ioe.getMessage(), ioe);
                break;
            }
        }
        final StringBuilder buf = new StringBuilder();
        if (renameFiles) {
            for (final Map.Entry<Integer, Path> entry : eligibleFiles.entrySet()) {
                buf.setLength(0);
                // LOG4J2-531: directory scan & rollover must use same format
                manager.getPatternProcessor().formatFileName(strSubstitutor, buf, entry.getKey() - 1);
                final String currentName = entry.getValue().toFile().getName();
                String renameTo = buf.toString();
                final int suffixLength = suffixLength(renameTo);
                if (suffixLength > 0 && suffixLength(currentName) == 0) {
                   renameTo = renameTo.substring(0, renameTo.length() - suffixLength);
                }
                final Action action = new FileRenameAction(entry.getValue().toFile(), new File(renameTo), true);
                try {
                    LOGGER.debug("DefaultRolloverStrategy.purgeAscending executing {}", action);
                    if (!action.execute()) {
                        return -1;
                    }
                } catch (final Exception ex) {
                    LOGGER.warn("Exception during purge in RollingFileAppender", ex);
                    return -1;
                }
            }
        }

        return eligibleFiles.size() > 0 ?
                (eligibleFiles.lastKey() < highIndex ? eligibleFiles.lastKey() + 1 : highIndex) : lowIndex;
    }
