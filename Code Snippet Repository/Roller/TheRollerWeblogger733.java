    protected void doDeleteSelected() {
        String[] fileIds = getSelectedMediaFiles();
        String[] dirIds = getSelectedMediaFileDirectories();
        try {
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();

            if (fileIds != null && fileIds.length > 0) {
                log.debug("Processing delete of " + fileIds.length
                        + " media files.");
                for (String fileId : fileIds) {
                    log.debug("Deleting media file - " + fileId);
                    MediaFile mediaFile = manager.getMediaFile(fileId);
                    if (mediaFile != null) {
                        manager.removeMediaFile(getActionWeblog(), mediaFile);
                    }
                }
            }

            WebloggerFactory.getWeblogger().getWeblogManager()
                    .saveWeblog(this.getActionWeblog());

            // flush changes
            WebloggerFactory.getWeblogger().flush();
            WebloggerFactory.getWeblogger().release();
            addMessage("mediaFile.delete.success");

        } catch (WebloggerException e) {
            log.error("Error deleting selected media files", e);
            addError("mediaFile.delete.error");
        }
    }
