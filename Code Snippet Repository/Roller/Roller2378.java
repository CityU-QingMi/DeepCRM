    public String save() {
        myValidate();
        if (!hasActionErrors()) {
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            try {
                MediaFile mediaFile = manager.getMediaFile(getMediaFileId());
                bean.copyTo(mediaFile);

                if (uploadedFile != null) {
                    mediaFile.setLength(this.uploadedFile.length());
                    mediaFile.setContentType(this.uploadedFileContentType);
                    manager.updateMediaFile(getActionWeblog(), mediaFile,
                            new FileInputStream(this.uploadedFile));
                } else {
                    manager.updateMediaFile(getActionWeblog(), mediaFile);
                }

                // Move file
                if (!getBean().getDirectoryId().equals(
                        mediaFile.getDirectory().getId())) {
                    log.debug("Processing move of " + mediaFile.getId());
                    setSelectedMediaFiles(new String[] { mediaFile.getId() });
                    setSelectedDirectory(getBean().getDirectoryId());
                    doMoveSelected();
                }

                WebloggerFactory.getWeblogger().flush();

                addMessage("mediaFile.update.success");
                return SUCCESS;

            } catch (FileIOException ex) {
                addError("uploadFiles.error.upload", bean.getName());

            } catch (Exception e) {
                log.error("Error uploading file " + bean.getName(), e);
                addError("uploadFiles.error.upload", bean.getName());
            }

        }
        return INPUT;
    }
