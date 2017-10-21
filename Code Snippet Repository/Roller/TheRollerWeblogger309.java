    public ThemeResource getResource(String path) {
        
        if (path == null) {
            return null;
        }
        
        ThemeResource resource;
        
        // first check in our shared theme
        resource = this.theme.getResource(path);
        
        // if we didn't find it in our theme then look in weblog uploads
        if(resource == null) {
            try {
                MediaFileManager mmgr =
                    WebloggerFactory.getWeblogger().getMediaFileManager();
                MediaFile mf = mmgr.getMediaFileByOriginalPath(
                    this.weblog, path);

            } catch (WebloggerException ex) {
                // ignored, resource considered not found
            }
        }
        
        return resource;
    }
