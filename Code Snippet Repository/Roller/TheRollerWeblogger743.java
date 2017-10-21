    public String createNewDirectory() {
        boolean dirCreated = false;
        if (StringUtils.isEmpty(this.newDirectoryName)) {
            addError("mediaFile.error.view.dirNameEmpty");
        } else if (this.newDirectoryName.contains("/")) {
            addError("mediaFile.error.view.dirNameInvalid");
        } else {
            try {

                log.debug("Creating new directory - " + this.newDirectoryName);
                MediaFileManager manager = WebloggerFactory.getWeblogger()
                        .getMediaFileManager();

                if (!getActionWeblog().hasMediaFileDirectory(
                        this.newDirectoryName)) {
                    // Create
                    MediaFileDirectory dir = manager.createMediaFileDirectory(
                            getActionWeblog(), this.newDirectoryName);
                    // flush changes
                    WebloggerFactory.getWeblogger().flush();
                    addMessage("mediaFile.directoryCreate.success",
                            this.newDirectoryName);

                    // Switch to folder
                    setDirectoryId(dir.getId());

                    dirCreated = true;
                } else {
                    // already exists
                    addMessage("mediaFile.directoryCreate.error.exists",
                            this.newDirectoryName);
                }

            } catch (WebloggerException e) {
                log.error("Error creating new directory", e);
                addError("Error creating new directory");
            }
        }

        if (dirCreated) {
            // Refresh list of directories so the newly created directory is
            // included.
            refreshAllDirectories();
        }
        return execute();

    }
