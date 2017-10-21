    @SkipValidation
    public String execute() {
        MediaFileManager manager = WebloggerFactory.getWeblogger()
                .getMediaFileManager();
        try {
            MediaFile mediaFile = manager.getMediaFile(getMediaFileId());
            this.bean.copyFrom(mediaFile);

        } catch (FileIOException ex) {
            addError("uploadFiles.error.upload", bean.getName());

        } catch (Exception e) {
            log.error("Error uploading file " + bean.getName(), e);
            addError("uploadFiles.error.upload", bean.getName());
        }

        return INPUT;
    }
