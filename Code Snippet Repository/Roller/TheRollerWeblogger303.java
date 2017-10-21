    public ThemeResource getResource(String path) {
        ThemeResource resource = null;
        try {
            MediaFileManager mmgr =
                WebloggerFactory.getWeblogger().getMediaFileManager();
            MediaFile mf = mmgr.getMediaFileByOriginalPath(
                this.weblog, path);
        } catch (WebloggerException ex) {
            // ignored, resource considered not found
        }
        return resource;
    }
