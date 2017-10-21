    public void myPrepare() {
        log.debug("Into myprepare");
        refreshAllDirectories();
        try {
            MediaFileManager mgr = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            if (!StringUtils.isEmpty(bean.getDirectoryId())) {
                setDirectory(mgr.getMediaFileDirectory(bean.getDirectoryId()));

            } else if (StringUtils.isNotEmpty(directoryName)) {
                setDirectory(mgr.getMediaFileDirectoryByName(getActionWeblog(),
                        directoryName));

            } else {
                MediaFileDirectory root = mgr
                        .getDefaultMediaFileDirectory(getActionWeblog());
                if (root == null) {
                    root = mgr.createDefaultMediaFileDirectory(getActionWeblog());
                }
                setDirectory(root);
            }
            directoryName = getDirectory().getName();
            bean.setDirectoryId(getDirectory().getId());

        } catch (WebloggerException ex) {
            log.error("Error looking up media file directory", ex);
        } finally {
            // flush
            try {
                WebloggerFactory.getWeblogger().flush();
            } catch (WebloggerException e) {
                // ignored
            }
        }
    }
