    public List<MediaFile> getItems() {
        
        if (this.mediaFiles == null) {
            // calculate offset
            //int offset = getPage() * length;
            
            List<MediaFile> results = new ArrayList<MediaFile>();
            
            try {
                MediaFileManager mgr = WebloggerFactory.getWeblogger().getMediaFileManager();
                results = mgr.fetchRecentPublicMediaFiles(length);
            } catch (Exception e) {
                log.error("ERROR: fetching comment list", e);
            }
            this.mediaFiles = results;
        }
        
        return this.mediaFiles;
    }
