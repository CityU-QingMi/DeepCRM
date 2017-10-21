    public void createMediaFileDirectory(MediaFileDirectory directory)
            throws WebloggerException {
        this.strategy.store(directory);

        // update weblog last modified date. date updated by saveWebsite()
        roller.getWeblogManager().saveWeblog(directory.getWeblog());

        // Refresh associated parent for changes
        // strategy.refresh(directory.getParent());
    }
