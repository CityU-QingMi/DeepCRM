    public MediaFile getMediaFile(String id, boolean includeContent)
            throws WebloggerException {
        MediaFile mediaFile = (MediaFile) this.strategy.load(MediaFile.class,
                id);
        if (includeContent) {
            FileContentManager cmgr = WebloggerFactory.getWeblogger()
                    .getFileContentManager();

            FileContent content = cmgr.getFileContent(mediaFile.getDirectory()
                    .getWeblog(), id);
            mediaFile.setContent(content);

            try {
                FileContent thumbnail = cmgr.getFileContent(mediaFile
                        .getDirectory().getWeblog(), id + "_sm");
                mediaFile.setThumbnailContent(thumbnail);

            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug("Cannot load thumbnail for image " + id, e);
                } else {
                    log.warn("Cannot load thumbnail for image " + id);
                }
            }
        }
        return mediaFile;
    }
