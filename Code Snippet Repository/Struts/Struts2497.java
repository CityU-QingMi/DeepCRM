    public boolean needsReload() {
        if (devMode && reload) {
            for (String url : loadedFileUrls) {
                if (fileManager.fileNeedsReloading(url)) {
                    LOG.debug("File [{}] changed, configuration will be reloaded", url);
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }
