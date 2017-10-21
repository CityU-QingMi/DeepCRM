    @Override
    public void initialize(final RollingFileManager manager) {
        if (manager.getFileTime() < JVM_START_TIME && manager.getFileSize() >= minSize) {
            if (minSize == 0) {
                manager.setRenameEmptyFiles(true);
            }
            manager.skipFooter(true);
            manager.rollover();
            manager.skipFooter(false);
        }
    }
