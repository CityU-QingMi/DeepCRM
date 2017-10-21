    public boolean isFileStorageUpgradeRequired() {
        String uploadsDirName = WebloggerConfig.getProperty("uploads.dir");
        if (uploadsDirName != null) {
            File uploadsDir = new File(uploadsDirName);
            if (uploadsDir.exists() && uploadsDir.isDirectory()) {
                Properties props = new Properties();
                try {
                    props.load(new FileInputStream(uploadsDirName
                            + File.separator + MIGRATION_STATUS_FILENAME));

                } catch (IOException ex) {
                    return true;
                }
                if (props.getProperty("complete") != null) {
                    return false;
                }
            }
        }
        return true;
    }
