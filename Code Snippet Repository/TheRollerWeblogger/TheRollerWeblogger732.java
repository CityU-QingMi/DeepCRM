    protected void doIncludeMediaFileInGallery() {

        try {
            log.debug("Processing include-in-gallery of file id - "
                    + this.mediaFileId);
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            MediaFile mediaFile = manager.getMediaFile(this.mediaFileId);
            mediaFile.setSharedForGallery(true);
            manager.updateMediaFile(getActionWeblog(), mediaFile);
            // flush changes
            WebloggerFactory.getWeblogger().flush();
            addMessage("mediaFile.includeInGallery.success");
        } catch (WebloggerException e) {
            log.error("Error including media file in gallery", e);
            addError("mediaFile.includeInGallery.error", this.mediaFileId);
        }
    }
