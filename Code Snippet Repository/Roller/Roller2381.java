    @SkipValidation
    public String execute() {
        try {
            MediaFileManager mgr = WebloggerFactory.getWeblogger().getMediaFileManager();
            MediaFile mediaFile = mgr.getMediaFile(getMediaFileId());
            bean.copyFrom(mediaFile);
        } catch (WebloggerException ex) {
            log.error("Error looking up media file directory", ex);
        }
        return SUCCESS;
    }
