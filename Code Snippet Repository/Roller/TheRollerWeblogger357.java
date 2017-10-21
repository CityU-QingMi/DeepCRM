    public MediaFile getMediaFile(String name) {
        Set<MediaFile> fileSet = this.getMediaFiles();
        if (fileSet == null) {
            return null;
        }
        for (MediaFile mediaFile : fileSet) {
            if (mediaFile.getName().equals(name)) {
                return mediaFile;
            }
        }
        return null;
    }
