    protected void doMoveSelected() {
        String[] fileIds = getSelectedMediaFiles();
        String[] dirIds = getSelectedMediaFileDirectories();
        try {
            int movedFiles = 0;
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();

            if (fileIds != null && fileIds.length > 0) {
                log.debug("Processing move of " + fileIds.length
                        + " media files.");
                MediaFileDirectory targetDirectory = manager
                        .getMediaFileDirectory(this.selectedDirectory);
                for (String fileId : fileIds) {
                    log.debug("Moving media file - " + fileId
                            + " to directory - " + this.selectedDirectory);
                    MediaFile mediaFile = manager.getMediaFile(fileId);
                    if (mediaFile != null && !mediaFile.getDirectory().getId().equals(targetDirectory.getId())) {
                        manager.moveMediaFile(mediaFile, targetDirectory);
                        movedFiles++;
                    }
                }
            }

            // flush changes
            WebloggerFactory.getWeblogger().flush();
            WebloggerFactory.getWeblogger().release();
            if (movedFiles > 0) {
                addMessage("mediaFile.move.success");
            }

        } catch (WebloggerException e) {
            log.error("Error moving selected media files", e);
            addError("mediaFile.move.errors");
        }
    }
