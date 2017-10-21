    public void shutdown() {
        try {
            HitCountQueue.getInstance().shutdown();
            if (indexManager != null) {
                indexManager.shutdown();
            }
            if (threadManager != null) {
                threadManager.shutdown();
            }
        } catch(Exception e) {
            log.error("Error calling Roller.shutdown()", e);
        }
    }
