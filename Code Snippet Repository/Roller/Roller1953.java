    public boolean hasMediaFile(String name) {
        Set<MediaFile> fileSet = this.getMediaFiles();
        if (fileSet == null) {
            return false;
        }
        for (MediaFile mediaFile : fileSet) {
            if (mediaFile.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
