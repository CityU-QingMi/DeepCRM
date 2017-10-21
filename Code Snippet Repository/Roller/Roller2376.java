    public void myPrepare() {
        refreshAllDirectories();
        try {
            MediaFileManager mgr = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            if (!StringUtils.isEmpty(bean.getDirectoryId())) {
                setDirectory(mgr.getMediaFileDirectory(bean.getDirectoryId()));
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up media file directory", ex);
        }

    }
