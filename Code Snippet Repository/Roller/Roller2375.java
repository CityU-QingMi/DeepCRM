    public void copyFrom(MediaFile dataHolder) {
        this.setId(dataHolder.getId());
        this.setName(dataHolder.getName());
        this.setDescription(dataHolder.getDescription());
        this.setCopyrightText(dataHolder.getCopyrightText());
        this.setTagsAsString(dataHolder.getTagsAsString());
        this.setSharedForGallery(dataHolder.getSharedForGallery());
        this.setDirectoryId(dataHolder.getDirectory().getId());
        this.setPermalink(dataHolder.getPermalink());
        this.setThumbnailURL(dataHolder.getThumbnailURL());
        this.setIsImage(dataHolder.isImageFile());
        this.setWidth(dataHolder.getWidth());
        this.setHeight(dataHolder.getHeight());
        this.setLength(dataHolder.getLength());
        this.setContentType(dataHolder.getContentType());
        this.setOriginalPath(dataHolder.getOriginalPath());
    }
