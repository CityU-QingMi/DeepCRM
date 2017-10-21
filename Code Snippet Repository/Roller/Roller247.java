    public void release() {
        try {
            autoPingManager.release();
            bookmarkManager.release();
            mediaFileManager.release();
            fileContentManager.release();
            pingTargetManager.release();
            pingQueueManager.release();
            pluginManager.release();
            threadManager.release();
            userManager.release();
            weblogManager.release();
        } catch(Exception e) {
            log.error("Error calling Roller.release()", e);
        }
    }
