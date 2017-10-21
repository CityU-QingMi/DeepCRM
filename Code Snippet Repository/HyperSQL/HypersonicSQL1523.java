    private static void closeSafely(final RandomAccessFile target) {
        if (target != null) {
            try {
                target.close();
            } catch (IOException ignoredIoe) {
                LOG.info(ignoredIoe.getMessage(), ignoredIoe);
            } catch (RuntimeException ignoredRex) {
                LOG.info(ignoredRex.getMessage(), ignoredRex);
            }
        }
    }
