    public void updateMediaFile(Weblog weblog, MediaFile mediaFile)
            throws WebloggerException {
        mediaFile.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        strategy.store(mediaFile);

        roller.flush();
        // Refresh associated parent for changes
        strategy.refresh(mediaFile.getDirectory());

        // update weblog last modified date. date updated by saveWeblog()
        roller.getWeblogManager().saveWeblog(weblog);
    }
