    public FileManager getFileManager() {
        if (fileManagerHolder != null) {
            return fileManagerHolder.getFileManager();
        }

        FileManager fileManager = lookupFileManager();
        if (fileManager != null) {
            LOG.debug("Using FileManager implementation [{}]", fileManager.getClass().getSimpleName());
            fileManager.setReloadingConfigs(reloadingConfigs);
            fileManagerHolder = new FileManagerHolder(fileManager);
            return fileManager;
        }

        LOG.debug("Using default implementation of FileManager provided under name [system]: {}", systemFileManager.getClass().getSimpleName());
        systemFileManager.setReloadingConfigs(reloadingConfigs);
        fileManagerHolder = new FileManagerHolder(systemFileManager);
        return systemFileManager;
    }
