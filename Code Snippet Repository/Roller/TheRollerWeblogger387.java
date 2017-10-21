    public void addBookmarkFolder(WeblogBookmarkFolder folder) {

        // make sure folder is not null
        if(folder == null || folder.getName() == null) {
            throw new IllegalArgumentException("Folder cannot be null and must have a valid name");
        }

        // make sure we don't already have a folder with that name
        if(this.hasBookmarkFolder(folder.getName())) {
            throw new IllegalArgumentException("Duplicate folder name '" + folder.getName() + "'");
        }

        // add it to our list of child folder
        getBookmarkFolders().add(folder);
    }
