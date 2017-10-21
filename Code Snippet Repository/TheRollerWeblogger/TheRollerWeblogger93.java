    public FileContentManagerImpl() {

        String inStorageDir = WebloggerConfig
                .getProperty("mediafiles.storage.dir");

        // Note: System property expansion is now handled by WebloggerConfig.

        if (inStorageDir == null || inStorageDir.trim().length() < 1) {
            inStorageDir = System.getProperty("user.home") + File.separator
                    + "roller_data" + File.separator + "mediafiles";
        }

        if (!inStorageDir.endsWith(File.separator)) {
            inStorageDir += File.separator;
        }

        this.storageDir = inStorageDir.replace('/', File.separatorChar);

    }
