    protected void doDeleteMediaFile() {

        try {
            log.debug("Processing delete of file id - " + this.mediaFileId);
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            MediaFile mediaFile = manager.getMediaFile(this.mediaFileId);
            manager.removeMediaFile(getActionWeblog(), mediaFile);
            // flush changes
            WebloggerFactory.getWeblogger().flush();
            WebloggerFactory.getWeblogger().release();
            addMessage("mediaFile.delete.success");
        } catch (WebloggerException e) {
            log.error("Error deleting media file", e);
            addError("mediaFile.delete.error", this.mediaFileId);
        }
    }
