    public String deleteFolder() {

        try {
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            if (directoryId != null) {
                log.debug("Deleting media file folder - " + directoryId + " ("
                        + directoryName + ")");
                MediaFileDirectory mediaFileDir = manager
                        .getMediaFileDirectory(directoryId);
                manager.removeMediaFileDirectory(mediaFileDir);
                refreshAllDirectories();
                WebloggerFactory.getWeblogger().getWeblogManager()
                        .saveWeblog(this.getActionWeblog());

                // flush changes
                WebloggerFactory.getWeblogger().flush();
                WebloggerFactory.getWeblogger().release();
                addMessage("mediaFile.deleteFolder.success");

                // notify caches
                CacheManager.invalidate(getActionWeblog());

                // re-route to default folder
                mediaFileDir = manager
                        .getDefaultMediaFileDirectory(getActionWeblog());
                setDirectoryId(mediaFileDir.getId());
                setDirectoryName(mediaFileDir.getName());
            } else {
                log.error("(System error) No directory ID provided for media file folder delete.");
            }
        } catch (WebloggerException ex) {
            log.error("Error deleting folder", ex);
        }
        return execute();
    }
